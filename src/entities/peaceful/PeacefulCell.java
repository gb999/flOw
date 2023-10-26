package entities.peaceful;

import java.awt.Graphics2D;

import entities.Edible;
import entities.Entity;
import entities.hostile.HostileCreature;
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
    public void isEatenBy(HostileCreature entity) {
        entity.eat(foodValue);
        System.out.println("eafaf");
    };

}
