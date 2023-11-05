package flow.entities;


import flow.entities.hostile.HostileCreature;

public interface Edible {
    public int getFoodValue();
    public void isEaten();
}