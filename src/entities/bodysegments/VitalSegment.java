package entities.bodysegments;
import java.awt.Graphics2D;

import entities.Edible;
import entities.Entity;
import entities.hostile.HostileCreature;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {
    private int saturation = 0;
    public VitalSegment(HostileCreature owner, Vec2 pos) {
        super(owner, pos);
        saturation = 1;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.fillOval((int)pos.x-9, (int)pos.y-9, 19, 19);
        
        

    }
    
    @Override
    public void isEatenBy(HostileCreature entity) {
        if(saturation == 0) return;
        entity.eat(saturation);
        saturation = 0;
        owner.segmentEaten(this);
    }

    @Override
    public int getFoodValue() {
        return saturation;
    }
    
}
