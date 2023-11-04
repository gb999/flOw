package flow.entities.bodysegments;

import flow.entities.Entity;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public abstract class BodySegment extends Entity  {
    protected HostileCreature owner;
    protected int saturation;

    public abstract int getMaxSaturation();
    public int getSaturation() {
        return saturation;
    }
    public void desaturate() {
        saturation = 0;
    }

    // Saturates segment, if fully saturated returns remaining food amount
    public abstract int saturate(int amount);
    
    
    public BodySegment(HostileCreature owner, Vec2 pos) {
        super(pos);
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
