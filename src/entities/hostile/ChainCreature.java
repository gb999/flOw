package entities.hostile;

import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.ListIterator;


import entities.bodysegments.BodySegment;
import entities.bodysegments.Mouth;
import entities.bodysegments.SimpleSegment;
import entities.bodysegments.VitalSegment;
import physics.Vec2;

public class ChainCreature extends HostileEntity {
    LinkedList<BodySegment> body;

    public ChainCreature() {
        body = new LinkedList<BodySegment>();
        pos = new Vec2(200,100);
        body.add(new Mouth(this, pos));
        // body.getFirst().applyForce(new Vec2(1,1)); // Move Mouth

        body.add(new VitalSegment(this, new Vec2(pos.x, pos.y )));
        for(int i = 0; i < 8; i++) {
            BodySegment s = new SimpleSegment(this, new Vec2(pos.x, pos.y + i * 20));
            body.add(s);
        }

    } 

    @Override
    void eat(int foodValue) {
        throw new UnsupportedOperationException("Unimplemented method 'eat'");
    }

    @Override
    public void update() {
        super.update();

        BodySegment first = body.getFirst();
        first.vel = this.vel;   
        first.update();
        this.pos = first.pos;
        // Add springs between body segments
        ListIterator<BodySegment> it = body.listIterator(0);
        BodySegment bcurrent = it.next();
        while(it.hasNext()) {
            bcurrent.update();
            BodySegment bnext = it.next();

            
            double distance = bcurrent.pos.distanceFrom(bnext.pos); 
            double desiredDistance = 30;
            if(distance > desiredDistance) { // fixed joint
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
    
}
