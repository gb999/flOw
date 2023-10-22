package entities.bodysegments;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;
import physics.Vec2;

public class SimpleSegment extends BodySegment {
    static final int radius = 12;
    int saturation = 0;
    public SimpleSegment(Entity owner, Vec2 pos) {
        super(owner, pos);
        saturation = 0;
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawOval((int) pos.x-radius, (int)pos.y-radius, 2 * radius, 2 * radius);
        if(saturation == 1) {
            g2.fillOval((int)pos.x-9, (int)pos.y-9, 19, 19);
            
        }
    }
    
}
