package flow.entities.peaceful;

import java.awt.Graphics2D;

import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class BiggerMouth extends PeacefulCell {

    public BiggerMouth(Vec2 pos) {
        super(pos, 1);
    }
    @Override
    public void isEatenBy(HostileCreature creature) {
        creature.getMouth().setSize(1.5);
    }
    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        g2.drawArc((int)(pos.x - r/2), (int)(pos.y - r/4)  , (int)r/2, (int)r/2, 0, 180);
    }
    
}
