package flow.entities.hostile;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.Mouth;
import util.Vec2;

public abstract class HostileCreature extends Entity {
    public HostileCreature(Vec2 pos) {
        super(pos);
    }
    public abstract void eat(int foodValue);
    public abstract Mouth getMouth();
    
    public abstract Edible checkCollisionsWithMouth(Mouth m);

    public abstract void segmentEaten(Edible b);
}
