package entities.hostile;

import entities.Entity;

abstract class HostileEntity extends Entity {
    abstract void eat(int foodValue);
}
