package entities.peaceful;

import java.awt.Graphics2D;

import entities.Edible;
import entities.Entity;

public class PeacefulCell extends Entity implements Edible{
    int foodValue;
    public PeacefulCell(int foodValue){
        this.foodValue = foodValue;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g2) {
        // g2.drawOval(pos.x,pos.y, 32, 32,  );
    };

}
