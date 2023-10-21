package entities.bodysegments;

import entities.Entity;

public abstract class BodySegment extends Entity {
    protected int x, y;
    protected Entity owner;
    public BodySegment(Entity owner) {
        this.owner = owner;
    }
}
