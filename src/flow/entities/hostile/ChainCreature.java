package flow.entities.hostile;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.BodySegment;
import flow.entities.bodysegments.Mouth;
import flow.entities.bodysegments.SimpleSegment;
import flow.entities.bodysegments.VitalSegment;
import flow.entities.peaceful.PeacefulCell;
import util.Vec2;
import flow.Game;


public class ChainCreature extends HostileCreature {
    protected LinkedList<BodySegment> body;
    Mouth mouth;
    private boolean digesting;
    public ChainCreature(Vec2 pos) {
        super(pos);
        digesting = false;
        body = new LinkedList<BodySegment>();
        mouth = new Mouth(this, pos);
        body.add(mouth);
        body.add(new VitalSegment(this, new Vec2(pos.x, pos.y )));
        for(int i = 0; i < 8; i++) {
            BodySegment s = new SimpleSegment(this, new Vec2(pos.x, pos.y + i * 20));
            body.add(s);
        }
    } 

    @Override
    public void eat(int foodValue) {
        // Digest
        for(BodySegment segment : body) {
            foodValue = segment.saturate(foodValue);
            if(foodValue == 0) {
                return; 
            }
        }
        // Body has been filled, empty all non-edible segments, grow new segment
        ArrayList<Edible> edibleSegments = getEdibleSegments();
        for(BodySegment segment : body) {
            if(edibleSegments.contains(segment)) continue;
            segment.desaturate();
        }
        body.add(new SimpleSegment(this, body.getLast().pos));

        if(foodValue != 0) {
            Game.currentLevel.addEdible(new PeacefulCell(body.getLast().pos, foodValue)); 
        }

    }


    @Override
    public void update() {
        super.update();

        // the Mouth pulls the whole body
        mouth.vel = this.vel;
        this.pos = mouth.pos;

        // Iterate through segments
        ListIterator<BodySegment> it = body.listIterator(0);
        BodySegment bcurrent = it.next();
        while(it.hasNext()) {
            bcurrent.update();
            BodySegment bnext = it.next();

            // Keep distance fixed between segments 
            double distance = bcurrent.pos.distanceFrom(bnext.pos); 
            double desiredDistance = 30;
            if(distance > desiredDistance) { 
                Vec2 displacement = (new Vec2(bcurrent.pos, bnext.pos)); 
                displacement.normalize();
                displacement.mult(desiredDistance - distance);
                bnext.pos.add(displacement);
            }
            bcurrent = bnext;

        }

    }
    @Override
    public void draw(Graphics2D g2) {
        for(BodySegment b: body) {
            b.draw(g2);
        } 
    }

    private ArrayList<Edible> getEdibleSegments() {
        ArrayList<Edible> edibleSegments = new ArrayList<>();
        for(BodySegment b : body) {
            if(b instanceof Edible)
                edibleSegments.add((Edible)b);
        }
        return edibleSegments;
    }

    
    /**
     * Checks if any edible body segment is colliding with the given mouth.
     * Returns it. or null;
     */
    @Override
    public Edible checkCollisionsWithMouth(Mouth mouth) {
        ArrayList<Edible> edibleSegments = getEdibleSegments();
        for(Edible b: edibleSegments) {
            if(Entity.intersects(mouth, (Entity) b)) {
                return b;
            }
        }
        return null;
    } 

    public Mouth getMouth() {
        return mouth;
    }

    private boolean isAlive() {
        for(Edible e : getEdibleSegments()) {
            if (e.getFoodValue() > 0) return true;
        }
        return false;
    }
    
    @Override
    public void segmentEaten(Edible b) {
        if(!isAlive()) {
            die();
        };
    }
    protected void die() {
        System.out.println("dead");

    }
}
