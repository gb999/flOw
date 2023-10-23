import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;
import entities.hostile.ChainCreature;

class Level {
    protected Color color;    
    public ArrayList<Entity> entities;
    public Level() {
        this.color = new Color(0, 255, 0, 150);
        entities = new ArrayList<Entity>();
        entities.add(new ChainCreature());
    }
    public void update() {
        for(Entity e: entities) {
            e.update();
        }
    }
}