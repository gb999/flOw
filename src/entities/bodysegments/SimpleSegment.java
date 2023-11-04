package entities.bodysegments;

import java.awt.Graphics2D;

import entities.hostile.HostileCreature;
import util.Vec2;

public class SimpleSegment extends BodySegment {
    public SimpleSegment(HostileCreature owner, Vec2 pos) {
        super(owner, pos);
        saturation = 0;
        r = 8;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawOval((int) (pos.x - r), (int)(pos.y - r), (int)(2 * r),(int)( 2 * r));
        if(saturation == 1) {
            g2.fillOval((int)(pos.x - r), (int)(pos.y-r), (int)(2 * r), (int)(2 * r));
        }

    }

    @Override
    public int getMaxSaturation() {
        return 1;
    }

}
