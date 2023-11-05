package flow;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import flow.entities.bodysegments.BodySegment;
import flow.entities.hostile.ChainCreature;
import util.Vec2;

public class Player extends ChainCreature implements MouseListener {
    boolean mouseDown = false;
    Player(Vec2 pos) {
        super(pos);
        GameCanvas.canvas.addMouseListener(this);
    }
    public void update() {
        Point mouse =  MouseInfo.getPointerInfo().getLocation();
        Point canvasPos = GameCanvas.canvas.getLocationOnScreen();
        super.update();
        vel.set(mouse.x - canvasPos.x, mouse.y - canvasPos.y);
        vel.sub(pos);
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
    @Override 
    protected void die() {
        for(BodySegment b : body) {
            b.desaturate();
        } 
        eat(1);
        Game.changeLevel(false);
    }
}
