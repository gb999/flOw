package util;

import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Ring {
    /**
     * Creates a ring shape centered around x, y coordinates
     * @param x Center x coordinate
     * @param y Center y coordinate
     * @param r1 Inner radious
     * @param r2 Outer radious
     * @return
     */
    public static Shape create(double x, double y, double r1, double r2) {
        Ellipse2D innerCircle = new Ellipse2D.Double(x - r1, y - r1, 2 * r1, 2 * r1);
        Ellipse2D outerCircle = new Ellipse2D.Double(x - r2, y - r2, 2 * r2, 2 * r2);
        Area ring = new Area(outerCircle);
        ring.subtract(new Area(innerCircle));
        return ring;
    }
}
