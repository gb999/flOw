package flow.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.Vec2;

/** 
 * All entities should implement these methods.
 * */
public interface IEntity {
    public void update();
    public void draw(Graphics2D g2);

    public Vec2 getPos();
    public Rectangle getBoundingBox();

}
