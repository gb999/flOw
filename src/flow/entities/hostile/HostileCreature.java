package flow.entities.hostile;


import java.util.ArrayList;
import java.util.List;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.Mouth;
import flow.entities.peaceful.PeacefulCell;
import util.Vec2;

public abstract class HostileCreature extends Entity {
    protected boolean agressive;
    protected double speed;

    protected int viewDistance;
    protected int attackDistance;
    public int getAttackDistance() {
        return attackDistance;
    }

    public void setAttackDistance(int attackDistance) {
        this.attackDistance = attackDistance;
    }

    protected long attackCooldown;
    protected long restTime;
    protected Edible closestEdible;
    protected Edible closestPlayerSegment;
    protected Edible target;

    protected HostileCreature(Vec2 pos) {
        super(pos);
        agressive = false;
        viewDistance = 400;
        attackDistance = 100;
        speed = 1;
        maxSpeed = speed;
        vel.set(speed, 0);
        restTime = 5000;
        closestEdible = null;
        target = null;
        attackCooldown = 0;
        lastAttackTime = 0;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        maxSpeed = speed;
    }


    /**
     * @return Mouth of this creature
     */
    public abstract Mouth getMouth();

    /**
     * Returns the first Edible segment of this creature colliding with mouth or null if there is no collision.
     * @param mouth mouth to check collisions with
     * @return first colliding edible segment of this creature or null if there is none. 
     */
    public abstract Edible checkCollisionsWithMouth(Mouth mouth);
    
    /**
     * Saturates this creature's body with food's foodValue amount of food. THEN calls food's isEatenBy() method. 
     * If body is fully saturated grows a new body segment. 
     * If foodValue amount of food can't be used to saturate body,
     * returns a new edible cell with the remaining food value.
     * @param food to be eaten.
     * @return a new edible cell to be spawned if there is remaining food
     */
    public abstract PeacefulCell eat(Edible food);


    /**
     * @return true if the creature is alive
     */
    public abstract boolean isAlive();


    /**
     * Called when creature dies. Override this if creature needs 
     * to spawn peaceful cells cells on death.
     * @return a list of edible cells, to be spawned. (Or an empty list if nothing has to be spawned)
     */
    public List<PeacefulCell> getRemains() {
        return new ArrayList<PeacefulCell>();
    };

    /**
     * Grows new vital segment
     */
    public abstract void growVital();


    
    public void setClosestEdible(Edible edible) {
        closestEdible = edible;
    }
    public void setClosestPlayerSegment(Edible edible) {
        closestPlayerSegment = edible;
    }

    public int getViewDistance() {
        return viewDistance;
    };

    public void setViewDistance(int viewDistance) {
        this.viewDistance = viewDistance;
    };

    public void setAgressive(boolean value) {
        this.agressive = value;
    }

    public void setRestTime(long value) {
        this.restTime = value;
    }
    

    long lastAttackTime;
    @Override 
    public void update() {
        super.update();
        if (attackCooldown > 0 ) 
            attackCooldown = Math.max(0, restTime - (System.currentTimeMillis() - lastAttackTime));
    }
}
