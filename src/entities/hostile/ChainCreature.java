package entities.hostile;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import entities.Edible;
import entities.Entity;
import entities.bodysegments.BodySegment;
import entities.bodysegments.Mouth;
import entities.bodysegments.SimpleSegment;
import entities.bodysegments.VitalSegment;
import util.Vec2;

public class ChainCreature extends HostileCreature {
    LinkedList<BodySegment> body;
    Mouth mouth;
    public ChainCreature(Vec2 pos) {
        super(pos);
        body = new LinkedList<BodySegment>();
        mouth = new Mouth(this, pos);
        body.add(mouth);
        body.add(new VitalSegment(this, new Vec2(pos.x, pos.y )));
        for(int i = 0; i < 8; i++) {
            BodySegment s = new SimpleSegment(this, new Vec2(pos.x, pos.y + i * 20));
            body.add(s);
        }
        System.out.println((body.get(1)).getMaxSaturation());
    } 

    @Override
    public void eat(int foodValue) {
        System.out.println("eaten " + foodValue);
    }

    @Override
    public void update() {
        super.update();

        // the Mouth pulls the whole body
        BodySegment first = body.getFirst();
        first.vel = this.vel;   
        first.update();
        this.pos = first.pos;

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
            // Change level, 
            System.out.println("dead");
        };
    }
}
