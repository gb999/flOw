package entities;

import java.awt.Graphics2D;

public abstract class Entity {
    abstract void update();
    abstract void draw(Graphics2D g2);
}
