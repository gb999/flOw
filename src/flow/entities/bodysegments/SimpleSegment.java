package flow.entities.bodysegments;

import java.awt.Graphics2D;

import util.Vec2;

public class SimpleSegment extends BodySegment {
    public SimpleSegment(Vec2 pos) {
        super(pos);
        maxSaturation = 1;
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
}
