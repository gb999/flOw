package flow.entities.bodysegments;

import flow.entities.Entity;
import util.Vec2;

public abstract class BodySegment extends Entity  {
    
    
    public BodySegment(Vec2 pos) {
        super(pos);
        this.pos = new Vec2(pos);
    }

    /**
     * Maximum saturation of this segment. 
     * Children of this class should define their own maxSaturation.  
     */
    protected int maxSaturation;
    
    /**
     * Stores current value of this segment's saturation.
     */
    protected int saturation;
    
    /**
     * Sets this segment's current saturation to 0.
     */
    public final void desaturate() {
        saturation = 0;
    }

    /**
     * Saturates this segment with given amount of food. 
     * If amount is enough sets saturation to max value.
     * If amount is not enough uses the remaining food.
     * @param amount
     * @return remaining amount of food
     */
    public final int saturate(int amount) {
            int digestableAmount = maxSaturation - saturation;
        int digestedAmount = Math.min(amount, digestableAmount);
        saturation += digestedAmount;
        return amount - digestableAmount;
    }
    


    /**
     * BodySegments are updated by the creature owning this segment. 
     * This method should not be overridden.
     */
    final public void update() {
        super.update();
    }

}
