package flow.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import util.Vec2;

public abstract class Entity implements IEntity {
    public Vec2 pos;
    public Vec2 vel;
    protected Vec2 acc;
    protected double maxSpeed;
    
    public Vec2 getPos() {
        return pos;
    }
    /**
     * Radius of the entity. 
     * Most entities are represented with a circle.
     */
    protected double r = 12; 
    

    public Entity(Vec2 pos) {
        this.pos = new Vec2(pos);
        vel = new Vec2();
        acc = new Vec2();
        maxSpeed = 5;

    }

    public void update() {
        pos.add(vel);

        vel.add(acc);

        // ~Friction 
        acc.mult(0.7);
        
        // Limit  speed
        if(vel.getLength() > maxSpeed) {
            //vel.normalize();
            vel.mult(0.7);
            // vel.mult(30);
        }
        
        
    };
    public void applyForce(Vec2 force) {
        acc.add(force);
    }
    public abstract void draw(Graphics2D g2);

    public Rectangle getBoundingBox() {
        return new Rectangle(
            (int)(this.pos.x - this.r),
            (int)(this.pos.y - this.r),
            (int)(2 * r),
            (int)(2 * r));
    }

    public static boolean intersects(Entity e1, Entity e2) {
        Rectangle r1 = e1.getBoundingBox();
        double x1 = r1.getX();
        double y1 = r1.getY();
        double w1 = r1.getWidth();
        double h1 = r1.getHeight();

        Rectangle r2 = e2.getBoundingBox();
        double x2 = r2.getX();
        double y2 = r2.getY();
        double w2 = r2.getWidth();
        double h2 = r2.getHeight();

        return !(x1 + w1 < x2 
        || x2 + w2 < x1 
        || y1 + h1 < y2 
        || y2 + h2 < y1);   
    }

}
