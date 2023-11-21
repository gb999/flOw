package util;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class Vec2Test {
    Vec2 v1;
    Vec2 v2;

    // maximum rounding error
    double e = 0.000001;

    @Before
    public void init() {
        v1 = new Vec2(5,10);
        v2 = new Vec2(20,20);
    }


    @Test 
    public void constructorTest() {
        System.out.println(v1.x + "," + v1.y);
        assertTrue(v1.x==5 && v1.y==10);
    }
    /**
     * reference of a vector and it's must be different
     */
    @Test 
    public void cloneTest() {
        assertNotEquals(v1, v1.clone());
    }

    @Test 
    public void addTest() {
        v1.add(v2);
        assertTrue(v1.x==25&&v1.y==30);

        // V2 did not change
        assertTrue(v2.x==20&&v2.y==20);
    }

    @Test 
    public void normalizeTest() {
        v1.normalize();
        assertTrue(1 - v1.getLength() < e);
    } 
    
    @Test 
    public void setDirTest() {
        double l1 = v1.getLength();
        v1.setDir(v2);
        double l2 = v1.getLength();
        
        // length should not change when changing direction
        assertTrue(l1 == l2); 

        v1.normalize();
        v2.normalize();

        // length should be near equal to 1, because of rounding errors
        assertTrue(v1.getLength() - 1 < e );
        assertTrue(v2.getLength() - 1 < e);
        assertTrue(v1.x - v2.x < e && v1.y - v2.y < e);
        
    }
}
