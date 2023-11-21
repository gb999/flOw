package flow.entities.peaceful;

import java.awt.Font;
import java.awt.Graphics2D;

import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class ExtraVital extends PeacefulCell {

    public ExtraVital(Vec2 pos) {
        super(pos, 1);
    }
    /**
     * Grows a new vital segment for the hostile creature
     */
    @Override
    public void isEatenBy(HostileCreature creature) {
        creature.growVital();
        
    }
    @Override 
    public void draw(Graphics2D g2) {
        super.draw(g2);
        Font font = g2.getFont();
        g2.setFont(new Font(font.getFamily(), font.getStyle(), (int)(2 * r)));
        g2.drawString(String.valueOf("+"), (int) (pos.x - 4), (int)(pos.y + 10));
    }

    
}
