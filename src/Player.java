import java.awt.MouseInfo;
import java.awt.Point;

import entities.hostile.ChainCreature;

public class Player extends ChainCreature {
    public void update() {
        Point mouse =  MouseInfo.getPointerInfo().getLocation();
        super.update();
        vel.set(mouse.x, mouse.y);
        vel.sub(pos);
        vel.normalize();
        vel.mult(3);
    }
    
}
