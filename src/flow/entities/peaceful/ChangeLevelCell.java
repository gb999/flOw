package flow.entities.peaceful;

import java.awt.Color;
import java.awt.Graphics2D;

import flow.Game;
import flow.GameCanvas;
import flow.entities.hostile.HostileCreature;
import util.Vec2;

public abstract class ChangeLevelCell extends PeacefulCell{
    private long lastTime;
    private long signalInterval = 3000;
    private long signalLength = 1500;
    private long lastSignalStart;
    private boolean signal;
    protected Color cellColor;

    public ChangeLevelCell(Vec2 pos, Color cellColor) {
        super(pos, 0);
        signal = false;
        this.cellColor = cellColor;
    }

    @Override
    public abstract void isEatenBy(HostileCreature creature);

    @Override
    public void draw(Graphics2D g2) {
        Color color = g2.getColor();
        g2.setColor(cellColor);
        super.draw(g2);
        
        double w = Game.canvas.getWidth();
        double h = Game.canvas.getHeight();
        Vec2 playerpos = Game.player.pos;
        double px = playerpos.x;
        double py = playerpos.y;
        double cx = this.pos.x;
        double cy = this.pos.y;


        if(signal) {
            double signalProgress = (double)(System.currentTimeMillis() - lastSignalStart) / (double)signalLength ;
            signalProgress *= 3;
            signalProgress %= 1;

            // location of signal   

            // Angle of player and cell
            double m = (py - cy) / (px - cx);
            
            // Intersection of canvas edge and line between cell and player in screen coordinates
            
            // Equation of line : y - y1 = (y2 - y1) / (x2 - x1) * (x - x1)
            // Equation of line between this and player : y - py = (y2 - py) / (x2 - px) * (x - px)
            // <=> x = (y - py) / (y2 - py) * (x2 - px) + px
            
            // sides of canvas :  ex+- := x = px +- w/2 
            // sides of canvas :  ey+- := y = py +- h/2 

            // intersection bottom, top : x = ex+-   y : [py-h/2, py+h/2]
            // y = (y2 - py) / (x2 - px) * (+ w/2) + py
            // y = (y2 - py) / (x2 - px) * (- w/2) + py
            
            // intersection left, right : y = ey+-   x : [px-w/2, px+w/2]
            // x = (+ h/2) / (y2 - py) * (x2 - px) + px
            // x = (- h/2) / (y2 - py) * (x2 - px) + px

            // intersection(ix, iy)
            double ix = 0; 
            double iy = 0; 

            // If cell is not on screen, animate
            if(!(px - w/2 < cx 
            && px + w/2 > cx
            && py - h/2 < cy
            && py + h/2 > cy)) {
                // cell is on left of player
                if(cx <= px) {
                    double Iy = m * (px - w/2 - cx) + cy;
                    if(py - h/2 <= Iy && Iy <= py+ h/2) {
                        ix = px - w/2;
                        iy = Iy;
                    }
                }
                // cell is on right of player
                if(cx >= px) {
                    double Iy = m * (px + w/2 - cx) + cy;
                    if(py - h/2 <= Iy && Iy <= py+ h/2) {
                        ix = px + w/2;
                        iy = Iy;
                    }

                }
                // cell is above player
                if(cy <= py) {
                    double Ix = (py - h / 2 - cy)/m + cx;
                    if(px - w/2 <= Ix && Ix <= px + w/2) {
                        ix = Ix;
                        iy = py - h/2;
                    } 
                }
                // cell is below player
                if(cy >= py) {
                    double Ix = (py + h / 2 - cy)/m + cx;
                    if(px - w/2 <= Ix && Ix <= px + w/2) {
                        ix = Ix;
                        iy = py + h/2;
                    } 
                }
                g2.drawArc((int)ix - (int)(100* signalProgress), (int)iy - (int)(100* signalProgress), (int)(signalProgress * 200), (int)(signalProgress * 200), 0, 360);
            }

        }

        g2.setColor(color);
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        if(!signal && currentTime - lastSignalStart > signalInterval + signalLength) {
            signal = true;
            lastSignalStart = currentTime;

        }

        if(signal && currentTime - lastSignalStart > signalLength) {
            signal = false;
        }
        lastTime = currentTime;
    }
}
