package flow;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;

import flow.entities.Edible;
import flow.entities.Entity;
import flow.entities.IEntity;
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
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
    /**
     * Finds closest entity from list to entity.
     * @param <T> T any Child class of Entity
     */
    private <T extends IEntity> T findClosestEntity(Entity entity, List<T> list) {
        Comparator<T> distanceComparator = Comparator.comparing(e -> entity.pos.distanceFrom(e.getPos()));
        if(list.isEmpty()) return null;
        T closestEntity = Collections.min(list, distanceComparator);
        return closestEntity;
    }

    /**
     * Updates all hostile creatures, handles interactions between peaceful and hostile creatures
     * and between player and hostile creatures.  
     */
    private void updateHostileCreatures() {
        // Check collisions between player and hostile creatures
        Mouth playerMouth = player.getMouth();  
        // Using iterator to avoid concurrent modifications.
        for(Iterator<HostileCreature> it = hostileCreatures.iterator(); it.hasNext(); ) {
            HostileCreature creature = it.next();
            creature.update();

            // Find closest edible player segment 
            Vec2 creaturePos = creature.getPos();
            if(creaturePos.distanceFrom(player.pos) < creature.getViewDistance()) {
                Edible closestPlayerSegment = findClosestEntity(creature, player.getEdibleSegments());;
                creature.setClosestPlayerSegment(closestPlayerSegment);
            }
            
            // Find closest edible segment to creature
            if(!edibleCells.isEmpty())
                creature.setClosestEdible(findClosestEntity(creature, edibleCells));

            // Check if player's body is eaten.
            Edible ediblePlayerSegment = player.checkCollisionsWithMouth(creature.getMouth());
            if(ediblePlayerSegment != null) {
                addEdible(creature.eat(ediblePlayerSegment));

                if(!player.isAlive()) {
                    player.die();
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

    /**
     * creature eats food if its mouth intersects with the food
     * @param creature
     * @param food
     * @return true if food was eaten
     */
    private boolean eatIfMouthIntersects(HostileCreature creature, Edible food) {
        if(creature == null || food == null) return false;
        Mouth mouth = creature.getMouth();
        if(Entity.intersects(mouth, food)) {
            addEdible(creature.eat(food));
            return true;
        }
        return false;        
    }

    private void updateLevelChangerCells() {
        if(redCell != null) {
            redCell.update();
            eatIfMouthIntersects(player, redCell);
        } 
        if(blueCell != null) {
            blueCell.update();
            eatIfMouthIntersects(player, blueCell);
        } 
    }

    private void updatePeacefulCells(boolean isCurrent) {
        if (isCurrent) {
            updateLevelChangerCells();
        }

        // Check collisions between hostile and peaceful cells
        // Using an iterator makes it safe to remove elements while iterating   
        for(Iterator<PeacefulCell> it = edibleCells.iterator(); it.hasNext();  ) {
            PeacefulCell cell = it.next();
            cell.update();
            if(eatIfMouthIntersects(player, cell)) {
                it.remove();
                continue;
            }
            
            // Check if hostile creature eats cell 
            for(HostileCreature creature: hostileCreatures) {
                if(eatIfMouthIntersects(creature, cell)) {
                    it.remove();
                    break;
                }
            }
        }
    }

    public void update(boolean isCurrent) {
        // Spawn peaceful cells if needed.
        while(!spawnPeacefulStack.isEmpty()) {
            edibleCells.add(spawnPeacefulStack.pop());
        }
        if(isCurrent)
            updateHostileCreatures();
        else
            // Update hostile creatures on next level. They should not interact with entities until player enters the level.
            hostileCreatures.forEach(e->e.update()); 
        
        updatePeacefulCells(isCurrent);

    }

    /**
     * Draws entities on level.
     * If current is true also draws player and  blue and red cells. 
     * @param g2 graphis objet
     * @param current is true if this is the current level
     */
    public void drawEntities(Graphics2D g2, boolean current) {
        if (current) {
            if(redCell != null) redCell.draw(g2);
            if(blueCell != null) blueCell.draw(g2);
            player.draw(g2);
        }
        
        edibleCells.forEach(e->e.draw(g2));
        hostileCreatures.forEach(e->e.draw(g2));
        
    }
}