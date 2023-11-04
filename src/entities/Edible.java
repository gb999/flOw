package entities;


import entities.hostile.HostileCreature;

public interface Edible {
    public int getFoodValue();
    public void isEatenBy(HostileCreature entity);
}