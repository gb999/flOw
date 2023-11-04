package entities.bodysegments;
import java.awt.Graphics2D;
import java.util.function.Function;

import entities.Edible;
import entities.hostile.HostileCreature;
import util.Ring;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {

    public VitalSegment(HostileCreature owner, Vec2 pos) {
        super(owner, pos);
        saturation = 2;
        r = 12;
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

    @Override
    public int getMaxSaturation() {
        return 2;
    }

    @Override
    public void draw(Graphics2D g2) {
        for(int i = 0; i <= getMaxSaturation(); i++) {
            g2.drawOval((int)(pos.x - (i) * r / 3), (int)(pos.y - (i) * r / 3), (int)(2 * (i) * r / 3) , (int)(2 * (i) * r / 3));
            
            if(saturation > 0) {
                g2.fill(Ring.create(pos.x, pos.y, r / 3 * (i - 1), r / 3 * i - 1));
            }

        }
    }
}
