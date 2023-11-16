package flow;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.bodysegments.Mouth;
import flow.entities.hostile.HostileCreature;
import flow.entities.peaceful.BlueCell;
import flow.entities.peaceful.PeacefulCell;
import flow.entities.peaceful.RedCell;
import util.Vec2;

public class Level {
    protected Color color;    
    protected List<PeacefulCell> edibleCells;
    protected List<HostileCreature> hostileCreatures;
    public Player player;
    
    
    /**
     * Contents of stack will be spawned on next iteration of update loop
     */ 
    private Stack<PeacefulCell> spawnPeacefulStack; 

    /**
     * Adds peaceful cell to spawn stack
     * @param p
     */    
    public void addEdible(PeacefulCell p) {
        if (p != null)
            spawnPeacefulStack.add(p);
    }

    private RedCell redCell;
    public void setRedCell(RedCell cell) {
        this.redCell = cell;
    }
    
    private BlueCell blueCell;
    public void setBlueCell(BlueCell cell) {
        this.blueCell = cell;
    }
    
    /**
     * Spawn count peaceful cells with foodValue randomly spread around (0,0) in 2000 radius; 
     * @param count
     * @param foodValue
     */
    public void spawnPeacefulCells(int count, int foodValue) {
        for(int i = 0; i < count;i++) {
            addEdible(new PeacefulCell(Vec2.getRandomVec2InRadius(2000), foodValue));
        }
    }

    /**
     * Adds hostile creature to the list of hostile creatures. 
     * @param creature
     */
    public void addHostileCreature(HostileCreature creature) {
        hostileCreatures.add(creature);
    }

    public Level() {
        spawnPeacefulStack = new Stack<>();
        this.color = new Color(100, 100, 255);
        edibleCells = new ArrayList<>();
        hostileCreatures = new ArrayList<>();

        // hostileCreatures.add(new ChainCreature(new Vec2(270, 500)));
        // hostileCreatures.get(0).applyForce(new Vec2(0.1,0));
    }
    public void setColor(Color color) {
        this.color = color;
    }


    private void updateHostileCreatures() {
        // Check collisions between player and hostile creatures
        Mouth playerMouth = player.getMouth();  
        // Using iterator to avoid concurrent modifications.
        for(Iterator<HostileCreature> it = hostileCreatures.iterator(); it.hasNext(); ) {
            HostileCreature creature = it.next();
            creature.update();

            // Check if player's body is eaten.
            Mouth hostileMouth = creature.getMouth();
            Edible ediblePlayerSegment = player.checkCollisionsWithMouth(hostileMouth);
            if(ediblePlayerSegment != null) {
                addEdible(creature.eat(ediblePlayerSegment));

                if(!player.isAlive()) {
                    player.die();
                    Game.changeLevel(false);
                    break;
                }
            }
            
            // Check if player eats any hostile body segment
            Edible edibleHostileSegment = creature.checkCollisionsWithMouth(playerMouth);
            if(edibleHostileSegment != null) {
                addEdible(player.eat(edibleHostileSegment));
            }
            if(!creature.isAlive()) {
                for(PeacefulCell food : creature.getRemains()) {
                    addEdible(food);
                }
                it.remove(); // Remove creature from level            
                break;
            }
        }
    }

    private void updatePeacefulCells() {
        Mouth playerMouth = player.getMouth();  
        
        // Check collisions between player and blue/red cells 
        if(redCell != null && Entity.intersects(playerMouth, redCell)) {
            redCell.update();
            redCell.isEatenBy(player);
            redCell = null;
        }
        if(blueCell != null && Entity.intersects(playerMouth, blueCell)) {
            blueCell.update();
            blueCell.isEatenBy(player);
            blueCell = null;
        }
               

        // Check collisions between hostile and peaceful cells
        // Using an iterator makes it safe to remove elements while iterating   
        for(Iterator<PeacefulCell> it = edibleCells.iterator(); it.hasNext();  ) {
            PeacefulCell cell = it.next();
            cell.update();

            // Check if player eats cell 
            if(Entity.intersects(playerMouth, cell)) {
                it.remove();
                addEdible(player.eat(cell));            
                continue; // Food can only ne eaten once
            }

            // Check if hostile creature eats cell 
            for(HostileCreature creature: hostileCreatures) {
                if(Entity.intersects(creature.getMouth(), cell)) {
                    it.remove();
                    addEdible(creature.eat(cell));
                    break; // Food can only ne eaten once 
                }
            }
        }
    }

    public void update() {
        // Spawn peaceful cells if needed.
        while(!spawnPeacefulStack.isEmpty()) {
            edibleCells.add(spawnPeacefulStack.pop());
        }

        updateHostileCreatures();
        
        updatePeacefulCells();


    }

    /**
     * 
     * @param g2
     * @param current is true if this is the current level
     */
    public void drawEntities(Graphics2D g2, boolean current) {
        if (current) {
            if(redCell != null) redCell.draw(g2);
            if(blueCell != null) blueCell.draw(g2);
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