package flow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import util.Vec2;


public class GameCanvas extends JPanel {
    Game game;
    GameCanvas(Game game) {
        this.game = game;
    }   
    
    /**
     * Draws the whole game 
     */
    public void paint(Graphics g) {
        // Avoid Exception thrown by swing when drawing component 
        if(game.currentLevel == null) return;

        Player player = game.player;
        Vec2 cameraPos = new Vec2(player.pos.x - getWidth() / 2, player.pos.y - getHeight() / 2);
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // Background
        g2.setColor(game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());
        
        
        g2.translate(-cameraPos.x, -cameraPos.y);
        g2.setColor(Color.WHITE);

        game.currentLevel.drawEntities(g2, true);

        if(game.nextLevel != null) {
            g2.setColor(new Color(255,255,255,70));
            game.nextLevel.drawEntities(g2, false);
        }

        g2.dispose();
    }


}
