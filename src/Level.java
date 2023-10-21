import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;

class Level {
    protected Color color;    
    public ArrayList<Entity> entities;
    public Level() {
        this.color = new Color(0, 255, 0, 255);
    }

}
