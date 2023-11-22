package flow.entities;


import flow.entities.hostile.HostileCreature;

public interface Edible extends IEntity {
    
    /**
     * @return food value of segment.
     */
    public int getFoodValue();

    /**
     * Called when eaten. Override this method if eating this causes side effects. (e.g change level) 
     * @param creature creature eating this
     */
    public default void isEatenBy(HostileCreature creature) {};

}