package entities.bodysegments;
import java.awt.Graphics2D;

import entities.Edible;
import entities.Entity;
import entities.hostile.HostileCreature;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {
    private int saturation = 0;
    public VitalSegment(Entity owner, Vec2 pos) {
        super(owner, pos);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillOval((int)pos.x-9, (int)pos.y-9, 19, 19);

    }
    
    
    @Override
    public void isEatenBy(HostileCreature entity) {
        entity.eat(saturation + 1);
    }
    
}
