package entities;

import java.awt.Graphics2D;

import physics.Vec2;

public abstract class Entity {
    public Vec2 pos;
    public Vec2 vel;
    protected Vec2 acc;
    
    public Entity() {
        pos = new Vec2();
        vel = new Vec2();
        acc = new Vec2();
    }
    public void update() {
        pos.add(vel);

        vel.add(acc);

        // ~Friction 
        acc.mult(0.7);
        
        // Limit  speed
        if(vel.getLength() > 10) {
            vel.normalize();
            vel.mult(30);
        }
        
        
    };
    public void applyForce(Vec2 force) {
        acc.add(force);
    }
    public abstract void draw(Graphics2D g2);
    
}
