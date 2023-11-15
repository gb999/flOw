package flow.entities.peaceful;

import java.awt.Color;
import java.awt.Graphics2D;

import flow.Game;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class BlueCell extends ChangeLevelCell{

    public BlueCell(Vec2 pos) {
        super(pos, new Color(0,0, 255));
    }
    @Override
    public void isEatenBy(HostileCreature creature) {
        Game.changeLevel(true);
    }
}
