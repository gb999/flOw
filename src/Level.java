import java.awt.Color;
import java.util.ArrayList;

import entities.Entity;
import entities.bodysegments.BodySegment;
import entities.bodysegments.Mouth;
import entities.bodysegments.SimpleSegment;
import entities.peaceful.PeacefulCell;

class Level {
    protected Color color;    
    public ArrayList<Entity> entities;
    public Level() {
        this.color = new Color(0, 255, 0, 255);
        entities = new ArrayList<Entity>();

        // entities.add(new SimpleSegment());
        entities.add(new Mouth(new PeacefulCell()));
    }
}
