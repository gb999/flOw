import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import entities.Edible;
import entities.Entity;
import entities.bodysegments.Mouth;
import entities.hostile.ChainCreature;
import entities.hostile.HostileCreature;
import entities.peaceful.PeacefulCell;
import util.Vec2;

class Level {
    protected Color color;    
    public ArrayList<PeacefulCell> edibleCells;
    public ArrayList<HostileCreature> hostileCreatures;
    public Player player;

    public Level() {
        this.color = new Color(0, 255, 0, 150);
        edibleCells = new ArrayList<>();
        hostileCreatures = new ArrayList<>();
        
        hostileCreatures.add(new ChainCreature(new Vec2(270, 500)));
        edibleCells.add(new PeacefulCell(new Vec2(500, 500), 0));
    }
    public void update() {
        // Check collisions between player and hostile creatures
        Mouth playerMouth = player.getMouth();  
        for(HostileCreature creature: hostileCreatures) {
            // Check if player's body is eaten.
            Mouth hostileMouth = creature.getMouth();
            Edible ediblePlayerSegment = player.checkCollisionsWithMouth(hostileMouth);
            if(ediblePlayerSegment != null) {
                ediblePlayerSegment.isEatenBy(creature);
            }
            
            // Check if player eats any hostile body segment
            Edible edibleHostileSegment = creature.checkCollisionsWithMouth(playerMouth);
            if(edibleHostileSegment != null) {
                edibleHostileSegment.isEatenBy(player);
            }
            
            creature.update();
        }
         

        // Check collisions between hostile and peaceful cells
        for(Entity e: edibleCells) {
            e.update();
        }
        player.update();
    }

    public void drawEntities(Graphics2D g2) {
        player.draw(g2);
        for(Entity e: edibleCells) {
            e.draw(g2);
        }
        for(Entity e: hostileCreatures) {
            e.draw(g2);
        }
        
    }
}