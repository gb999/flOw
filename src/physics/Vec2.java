package physics;

public class Vec2 {
    public double x, y;
    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }   
    public Vec2() {
        this.x = 0;
        this.y = 0;
    }

    public Vec2(Vec2 v) {
        this.x = v.x;
        this.y = v.y;
    }   



    public void add(Vec2 v) {
        x+=v.x;
        y+=v.y;
    }

    public void sub(Vec2 v) {
        x-=v.x;
        y-=v.y;
    }

    public void mult(double s) {
        x*=s;
        y*=s;
    }
    public double getLength() {
        return Math.sqrt(x*x + y*y); 
    }

    public double distanceFrom(Vec2 v) {
        return Math.sqrt((x-v.x)*(x-v.x) + (y-v.y)*(y-v.y));
    }

    public void normalize() {
        double l = getLength();
        x /= l; 
        y /= l; 
    }

    public double getAngle() {
        return Math.atan2(y, x);
    }
    public int getAngleDegrees() {
        return (int) (getAngle() * 180 / Math.PI);
    }
    

    
}
