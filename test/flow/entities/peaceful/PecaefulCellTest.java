package flow.entities.peaceful;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import util.Vec2;

public class PecaefulCellTest {
    @Test
    public void constructorTest() {
        PeacefulCell cell = new PeacefulCell(new Vec2(10,10), 3);
        assertEquals(3, cell.foodValue); 
        assertTrue(cell.pos.x == 10 && cell.pos.y == 10); 
        
    }
}
