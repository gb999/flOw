package flow.entities.hostile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import flow.entities.peaceful.PeacefulCell;
import util.Vec2;

public class ChainCreatureTest {
    @Test
    public void lengthTest() {

        ChainCreature creature = new ChainCreature(new Vec2(10,10), 2, 1);
        assertEquals(1, creature.getEdibleSegments().size());
        assertEquals(3, creature.body.size()); // body size = segments + mouth
    }

    @Test
    public void growTest() {
        ChainCreature creature = new ChainCreature(new Vec2(10,10), 2, 1);
        creature.growVital();
        assertEquals(2, creature.getEdibleSegments().size());
        assertEquals(4, creature.body.size());
        
    }

    
    @Test
    public void eatTest() {
        // this creature can store 3 food
        ChainCreature creature = new ChainCreature(new Vec2(10,10), 2, 1);
        
        // Eating should not return anything 
        PeacefulCell result = creature.eat(new PeacefulCell(new Vec2(10,10), 1));
        assertNull(result);
        
        // Eating should return 1 undigested cell 
        PeacefulCell result2 = creature.eat(new PeacefulCell(new Vec2(10,10), 3));
        assertNotNull(result2);
    }

}
