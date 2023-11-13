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

    public void paint(Graphics g) {
        Player player = Game.currentLevel.player;
        Vec2 cameraPos = new Vec2(player.pos.x - getWidth() / 2, player.pos.y - getHeight() / 2);
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        // Background
        g2.setColor(Game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());
        
        g2.translate(-cameraPos.x, -cameraPos.y);
        g2.setColor(Color.WHITE);
        player.draw(g2);
        Game.currentLevel.drawEntities(g2);

        if(Game.nextLevel != null) {

            g2.setColor(new Color(255,255,255,100));
            Game.nextLevel.drawEntities(g2);
        }

        g2.dispose();
    }


}
