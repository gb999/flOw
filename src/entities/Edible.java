package entities;

import entities.hostile.HostileCreature;

public interface Edible {
    public void isEatenBy(HostileCreature entity);
}