package entities.bodysegments;

import entities.Entity;
import physics.Vec2;

public abstract class BodySegment extends Entity {
    protected Entity owner;
    public BodySegment(Entity owner, Vec2 pos) {
        this.owner = owner;
        this.pos = new Vec2(pos);
    }
    /**
     * BodySegemnts should be updated by the owner. They should not update themselves
     */
    final public void update() {
        super.update();
    }
}
