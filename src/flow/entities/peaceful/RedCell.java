package flow.entities.peaceful;

import flow.Game;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;

public class RedCell extends PeacefulCell {
    public RedCell(Vec2 pos) {
        super(pos, 0);
    }   

    @Override
    public void isEatenBy(HostileCreature creature) {
        Game.changeLevel(true);
    }

    @Override
    public void draw(Graphics2D g2) {
        Color color = g2.getColor();
        g2.setColor(new Color(255,0,0));
        super.draw(g2);
        g2.setColor(color);

    }
}
