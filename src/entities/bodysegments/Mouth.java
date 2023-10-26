package entities.bodysegments;

import java.awt.Color;
import java.awt.Graphics2D;

import entities.Entity;
import entities.Edible;
import util.Vec2;

public class Mouth extends BodySegment {
    public Mouth(Entity owner, Vec2 pos) {
        super(owner, pos);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        int dir = vel.getAngleDegrees();
        g2.drawArc((int)pos.x-16, (int)pos.y-16, 32, 32, -dir+90, 180);   // Face in velocity's direction
    }

    public boolean checkCollision(Edible e) {
        
        return true;
    }
}
