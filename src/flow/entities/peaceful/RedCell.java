package flow.entities.peaceful;

import flow.Game;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

import java.awt.Color;
import java.awt.Graphics2D;

public class RedCell extends ChangeLevelCell {
    public RedCell(Vec2 pos) {
        super(pos, new Color(255, 0 ,0));
    }   

    @Override
    public void isEatenBy(HostileCreature creature) {
        Game.changeLevel(true);
    }


}
