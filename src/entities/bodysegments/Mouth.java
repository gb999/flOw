package entities.bodysegments;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;

public class Mouth extends BodySegment {
    public Mouth(Entity owner) {
        super(owner);

        x = y = 100;
    }
    

    public void update() {

    }
    public void draw(Graphics2D g2) {

        g2.setColor(Color.WHITE);
        g2.drawArc(x, y, 32, 32, 0, 180);   // Face in owner's direction
    }

}
