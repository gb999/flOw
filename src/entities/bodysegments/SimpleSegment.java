package entities.bodysegments;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;

public class SimpleSegment extends BodySegment {
    int saturation = 0;
    public SimpleSegment(Entity owner) {
        super(owner);
        x=300;
        y=200;
        saturation = 1;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawOval(x-12, y-12, 24, 24);
        if(saturation == 1) {
            g2.fillOval(x-9, y-9, 19, 19);
            
        }
    }
    
}
