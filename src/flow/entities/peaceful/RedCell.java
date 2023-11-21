package flow.entities.peaceful;

import flow.Game;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

import java.awt.Color;

public class RedCell extends ChangeLevelCell {
    public RedCell(Vec2 pos) {
        super(pos, new Color(255, 0 ,0));
    }   

    /**
     * Changes level to next level
     */
    @Override
    public void isEatenBy(HostileCreature creature) {
        Game.changeLevel(true);
    }
}
