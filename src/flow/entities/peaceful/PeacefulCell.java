package flow.entities.peaceful;

import java.awt.Graphics2D;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public class PeacefulCell extends Entity implements Edible {
    int foodValue;
    public PeacefulCell(Vec2 pos, int foodValue){
        super(pos);
        this.foodValue = foodValue;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawOval((int)pos.x, (int)pos.y, 32, 32);
    }

    @Override
    public int getFoodValue() {
        return foodValue;
    }

    @Override
    public void isEaten() {
        
    };
    

}
