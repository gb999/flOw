package flow.entities.peaceful;

import java.awt.Color;
import java.awt.Graphics2D;

import flow.Game;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class BlueCell extends PeacefulCell{


    public BlueCell(Vec2 pos) {
        super(pos, 0);
    }

    @Override
    public void isEatenBy(HostileCreature creature) {
        Game.changeLevel(false);
    }

    @Override
    public void draw(Graphics2D g2) {
        Color color = g2.getColor();
        g2.setColor(new Color(0,0,255));
        super.draw(g2);
        g2.setColor(color);

    }
    
}
