package flow.entities.bodysegments;

import java.awt.Graphics2D;

import flow.entities.Edible;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class Mouth extends BodySegment {
    {
        maxSaturation = 0;
        saturation = 0;
    }
    public Mouth(HostileCreature owner, Vec2 pos) {
        super(pos);
    }

    public void draw(Graphics2D g2) {
        int dir = vel.getAngleDegrees();
        g2.drawArc((int)(pos.x - r * 1.5), (int)(pos.y - r * 1.5), (int) (3 * r), (int) (3 * r), -dir+90, 180);   // Face in velocity's direction

    }

    public boolean checkCollision(Edible e) {
        return true;
    }

    public int getMaxSaturation() {
        return 0;
    }

    public double getSize() {
        return r;
    }
    /**
     * Sets size to r* size
     * @param size
     */
    public void setSize(double size) {
        r *= size;
    }
    
}
