package flow.entities;

import java.awt.Graphics2D;

import util.Vec2;

public interface IEntity {
    public void update();
    public void draw(Graphics2D g2);

    public Vec2 getPos();
}
