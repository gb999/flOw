package entities.bodysegments;
import java.awt.Color;
import java.awt.Graphics2D;

import entities.Edible;
import entities.Entity;
import util.Vec2;


public class VitalSegment extends BodySegment implements Edible {

    public VitalSegment(Entity owner, Vec2 pos) {
        super(owner, pos);
    }
    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.fillOval((int)pos.x-9, (int)pos.y-9, 19, 19);
    }
    
}
