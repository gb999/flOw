package flow.entities.hostile;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.BodySegment;
import flow.entities.bodysegments.Mouth;
import flow.entities.bodysegments.SimpleSegment;
import flow.entities.bodysegments.VitalSegment;
import flow.entities.peaceful.PeacefulCell;
import util.Vec2;


public class ChainCreature extends HostileCreature {
    protected LinkedList<BodySegment> body;
    Mouth mouth;
    public ChainCreature(Vec2 pos, int length, int nVitalSegments) {
        super(pos);
        body = new LinkedList<>();
        mouth = new Mouth(this, pos);
        body.add(mouth);
        int i = 0;
        for(; i < nVitalSegments; i++) {
            body.add(new VitalSegment(new Vec2(pos.x, pos.y + i * 20), true));
        }
        for(; i < length; i++) {
            BodySegment s = new SimpleSegment(new Vec2(pos.x, pos.y + i * 20));
            body.add(s);
        }
    } 

    @Override
    public PeacefulCell eat(Edible food) {
        // Digest
        int foodValue = food.getFoodValue();
        for(BodySegment segment : body) {
            // If foodValue reaches 0 before iteration finishes, 
            // then digesting is done. 
            if(foodValue == 0) { 
                food.isEatenBy(this);
                return null;
            }
            foodValue = segment.saturate(foodValue);
        }
        food.isEatenBy(this);

        // Body has been filled, desaturate all non-edible segments, grow new segment
        ArrayList<Edible> edibleSegments = getEdibleSegments();
        for(BodySegment segment : body) {
            if(edibleSegments.contains(segment)) continue;
            segment.desaturate();
        }
        body.add(new SimpleSegment(body.getLast().pos));

        // If there is remaining food spawn simple cell
        if(foodValue != 0) {
           return new PeacefulCell(body.getLast().pos, foodValue); 
        }

        return null;
    }

    private void updateBody() {
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
    private boolean edibleInDistance(Edible edible, double distance) {
        return pos.distanceFrom(edible.getPos()) <= distance;
    }
    private boolean closestEdibleInAttackDistance() {
        return edibleInDistance(closestEdible, attackDistance);
    }
    private boolean  targetInAttackDistance() {
        return edibleInDistance(target, attackDistance);
        
    }
    private boolean closestEdibleInViewDistance() {
        return edibleInDistance(closestEdible, viewDistance);
    }
    private boolean  targetInViewDistance() {
        return edibleInDistance(target, viewDistance);
        
    }
    private void attack() {
        applyForce(vel);
        vel.mult(1.2);
        
    }
    private void followTarget() {
        Vec2 dir = target.getPos().clone();
        dir.sub(pos);
        vel.setDir(dir);
    }
    protected void updateBehaviour() {
        if(agressive && attackCooldown == 0) {
            // set target
            if(target != null) {
                if(!targetInViewDistance()) target = null;
            }
            if(target == null && closestEdible != null) {
                if(closestEdibleInViewDistance()) target = closestEdible;
                             
            }  
            
            if(target != null) {
                
                if(targetInAttackDistance()) {
                    attack();
                    target = null;  
                    attackCooldown = restTime;
                    

                } else {
                    followTarget();
                }
            }
            

        }

    } 

    @Override
    public void update() {
        super.update();

        updateBehaviour();
        
        updateBody();
    }

    @Override
    public void draw(Graphics2D g2) {
        for(BodySegment b: body) {
            b.draw(g2);
        } 
    }

    protected ArrayList<Edible> getEdibleSegments() {
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

    public boolean isAlive() {
        for(Edible e : getEdibleSegments()) {
            if (e.getFoodValue() > 0) return true;
        }
        return false;
    }
    

    /**
     * Spawn cells on death
     */
    public List<PeacefulCell> getRemains() {
        return new ArrayList<>();
    }
}
