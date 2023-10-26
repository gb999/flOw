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

    public Level() {
        this.color = new Color(0, 255, 0, 150);
        edibleCells = new ArrayList<>();
        hostileCreatures = new ArrayList<>();

        hostileCreatures.add(new ChainCreature(new Vec2(170, 100)));
        hostileCreatures.add(new Player(new Vec2(100, 100)));
        edibleCells.add(new PeacefulCell(new Vec2(500, 500), 0));
    }
    public void update() {
        
        // Check collisions between hostile creatures
        

        // Check collisions between hostile and peaceful cells

        for(HostileCreature creature: hostileCreatures) {
            Mouth mouth = creature.getMouth();
            for(HostileCreature h : hostileCreatures) {
                if(h == creature) continue;
                Edible edibleSegment = h.checkCollisionsWithMouth(mouth);
                if(edibleSegment != null) {
                    edibleSegment.isEatenBy(creature);
                    break; // No need to check more collisions if creature was fed
                }
            }

            for(PeacefulCell c : edibleCells) {
                if(Entity.intersects(c, mouth)) {
                    c.isEatenBy(creature);
                    break;
                }

            }

            creature.update();
        }
        for(Entity e: edibleCells) {
            e.update();
        }
    }

    public void drawEntities(Graphics2D g2) {
        for(Entity e: edibleCells) {
            e.draw(g2);
        }
        for(Entity e: hostileCreatures) {
            e.draw(g2);
        }
        
    }
}