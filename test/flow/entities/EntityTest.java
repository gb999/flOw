package flow.entities;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import flow.entities.bodysegments.Mouth;
import util.Vec2;

/**
 * This test uses Mouth instances as Entities. 
 * Any children of Entity should be suitable for these tests.  
 * */
public class EntityTest {


    @Test 
    public void constructorTest() {
        Entity e = new Mouth(null, new Vec2(100,30));
        Assert.assertTrue(e.getPos().x == 100 && e.getPos().y == 30 );
        
    }

    @Test
    public void intersectsSelfTest() {
        Mouth m = new Mouth(null, new Vec2(0,0));
        // Entities should always intersect themselves
        Assert.assertTrue(Entity.intersects(m, m));
    }
    
    @Test
    public void noIntersectionTest() {
        Mouth m1 = new Mouth(null, new Vec2(0,0));
        Mouth m2 = new Mouth(null, new Vec2(m1.getR() * 2 +1, 0));
        // Entities offset by 2 * their radius + 1 value should not intersect 
        Assert.assertFalse(Entity.intersects(m1, m2));
    }
    @Test
    public void edgeIntersectionTest() {
        Mouth m1 = new Mouth(null, new Vec2(0,0));
        Mouth m2 = new Mouth(null, new Vec2(m1.getR() * 2 , 0));
        // Entities offset by 2 * their radius value should intersect with their edge. 
        Assert.assertTrue(Entity.intersects(m1, m2));
    }


    @Test 
    public void getBoundingBoxTest() {
        Mouth m = new Mouth(null, new Vec2(0,0));
        // Entities bounding box should be modelled 
        // by a rectangle, 2 * the radius of the Entity
        assertTrue(m.getBoundingBox().getWidth() == 2 * m.getR() && 
        m.getBoundingBox().getHeight() == 2 * m.getR());
    }
}
