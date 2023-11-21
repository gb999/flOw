package flow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.Vec2;


public class GameCanvas extends JPanel {
    static GameCanvas canvas;
    GameCanvas() {
        canvas = this;
    }
    
    /**
     * Draws the whole game 
     */
    public void paint(Graphics g) {
        // Avoid Exception thrown by swing when drawing component 
        if(Game.currentLevel == null) return;

        Player player = Game.player;
        Vec2 cameraPos = new Vec2(player.pos.x - getWidth() / 2, player.pos.y - getHeight() / 2);
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // Background
        g2.setColor(Game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());
        
        
        g2.translate(-cameraPos.x, -cameraPos.y);
        g2.setColor(Color.WHITE);

        Game.currentLevel.drawEntities(g2, true);

        if(Game.nextLevel != null) {
            g2.setColor(new Color(255,255,255,70));
            Game.nextLevel.drawEntities(g2, false);
        }

        g2.dispose();
    }


}
