package flow.entities.bodysegments;
import java.awt.Graphics2D;

import flow.entities.Edible;
import flow.entities.hostile.HostileCreature;
import util.Ring;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {
    public VitalSegment(Vec2 pos) {
        super(pos);
        maxSaturation = 2;
        saturation = 0;
        r = 12;
    }
    public VitalSegment(Vec2 pos, boolean saturated ) {
        this(pos);
        saturation = saturated ? maxSaturation : 0;

    }
    
    @Override
    public void isEatenBy(HostileCreature h) {
        saturation = 0;
    }

    @Override
    public int getFoodValue() {
        return saturation;
    }
    
    @Override
    public void draw(Graphics2D g2) {
        for(int i = 0; i <= maxSaturation; i++) {
            g2.drawOval((int)(pos.x - (i) * r / 3), (int)(pos.y - (i) * r / 3), (int)(2 * (i) * r / 3) , (int)(2 * (i) * r / 3));
            
            if(saturation > 0 && saturation >= i) {
                double r1 = r / 3 * (i-1); 
                double r2 = r / 3 * i;
                g2.fill(Ring.create(pos.x, pos.y, r1, r2-1));
            }

        }
    }
}
