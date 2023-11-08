package flow;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.Mouth;
import flow.entities.hostile.ChainCreature;
import flow.entities.hostile.HostileCreature;
import flow.entities.peaceful.PeacefulCell;
import util.Vec2;

public class Level {
    protected Color color;    
    public ArrayList<PeacefulCell> edibleCells;
    public ArrayList<HostileCreature> hostileCreatures;
    public Player player;
    private Stack<PeacefulCell> spawnEdibleStack; // Contents odf stack will be spawned on nexct iteration of update loop

    /**
     * Adds peaceful cell to spawn stack
     * @param p
     */    
    public void addEdible(PeacefulCell p) {
        spawnEdibleStack.add(p);
    }

    public Level() {
        spawnEdibleStack = new Stack<>();
        this.color = new Color(100, 100, 255);
        edibleCells = new ArrayList<>();
        hostileCreatures = new ArrayList<>();
        
        hostileCreatures.add(new ChainCreature(new Vec2(270, 500)));
        hostileCreatures.get(0).applyForce(new Vec2(0.1,0));
        edibleCells.add(new PeacefulCell(new Vec2(500, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(600, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(700, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(800, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(900, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(1000, 500), 1));
        edibleCells.add(new PeacefulCell(new Vec2(1100, 500), 1));
    }
    public void update() {
        while(!spawnEdibleStack.isEmpty()) {
            edibleCells.add(spawnEdibleStack.pop());
        }
        // Check collisions between player and hostile creatures
        Mouth playerMouth = player.getMouth();  
        for(HostileCreature creature: hostileCreatures) {
            // Check if player's body is eaten.
            Mouth hostileMouth = creature.getMouth();
            Edible ediblePlayerSegment = player.checkCollisionsWithMouth(hostileMouth);
            if(ediblePlayerSegment != null) {
                creature.eat(ediblePlayerSegment.getFoodValue());
                ediblePlayerSegment.isEaten();
            }
            
            // Check if player eats any hostile body segment
            Edible edibleHostileSegment = creature.checkCollisionsWithMouth(playerMouth);
            if(edibleHostileSegment != null) {
                player.eat(edibleHostileSegment.getFoodValue());
                edibleHostileSegment.isEaten();
            }
            creature.update();
            if(!creature.isAlive()) {
                // remove creature from entites
            }
        }

        // Check collisions between hostile and peaceful cells
        // Using an iterator makes it safe to remove elements while iterating   
        for(Iterator<PeacefulCell> it = edibleCells.iterator(); it.hasNext();  ) {
            PeacefulCell e = it.next();
            ((Entity)e).update();

            // Check if player eats cell 
            if(Entity.intersects(playerMouth, (Entity)e)) {
                it.remove();
                player.eat(e.getFoodValue());            
                e.isEaten();
                continue; // Food can only ne eaten once 
            }
            
            // Check if hostile creature eats cell 
            for(HostileCreature h: hostileCreatures) {
                if(Entity.intersects((Entity)h.getMouth(), (Entity)e)) {
                    it.remove();
                    h.eat(e.getFoodValue());
                    e.isEaten();
                    break; // Food can only ne eaten once
                }
            }
        }
        

        if(player != null) {
            player.update();
        }
    }

    public void drawEntities(Graphics2D g2) {
        if(player != null) {
            player.draw(g2);
        }
        for(Entity e: edibleCells) {
            e.draw(g2);
        }
        for(Entity e: hostileCreatures) {
            e.draw(g2);
        }
        
    }
}