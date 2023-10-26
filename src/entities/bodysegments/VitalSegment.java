package entities.bodysegments;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import entities.Edible;
import entities.Entity;
import entities.hostile.HostileCreature;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {
    public VitalSegment(Entity owner, Vec2 pos) {
        super(owner, pos);
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval((int)pos.x-9, (int)pos.y-9, 19, 19);
    }
    
    
    @Override
    public void isEatenBy(HostileCreature entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEatenBy'");
    }
    
}
