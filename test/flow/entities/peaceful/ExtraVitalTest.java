package flow.entities.peaceful;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import flow.entities.hostile.ChainCreature;
import util.Vec2;

public class ExtraVitalTest {
    @Test
    public void eatenTest() {
        ExtraVital vital = new ExtraVital(new Vec2(0,0));
        ChainCreature player = new ChainCreature(new Vec2(0,0), 4, 2);

        assertEquals(2, player.getEdibleSegments().size());
        vital.isEatenBy(player);  // Player grows a new vital segment

        assertEquals(3, player.getEdibleSegments().size());
    }
}
