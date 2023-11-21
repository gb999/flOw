package flow;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import flow.entities.bodysegments.BodySegment;
import flow.entities.hostile.ChainCreature;
import flow.entities.peaceful.PeacefulCell;
import util.Vec2;


public class Player extends ChainCreature implements MouseListener {
    boolean mouseDown = false;
    Player(Vec2 pos) {
        super(pos, 8, 3);
        GameCanvas.canvas.addMouseListener(this);
        agressive = false;
    }
    @Override
    public void update() {
        super.update();

        // cursor location on screen = mousePos - canvasPos
        Point mousePos =  MouseInfo.getPointerInfo().getLocation();
        Point canvasPos = GameCanvas.canvas.getLocationOnScreen();
        // Velocity direction = cursor location - canvas center  
        vel.set(mousePos.x - canvasPos.x - GameCanvas.canvas.getWidth() / 2, mousePos.y - canvasPos.y - GameCanvas.canvas.getHeight() / 2);
        
        vel.normalize();
        int mult = mouseDown ? 6 : 3;
        vel.mult(mult);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * Called when player dies.
     * Desaturates all body segments except one.
     */
    protected void die() {
        for(BodySegment b : body) {
            b.desaturate();
        }
        eat(new PeacefulCell(pos, 1)); 
        
    }
}
