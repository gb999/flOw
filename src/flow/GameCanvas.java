package flow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GameCanvas extends JPanel {
    static GameCanvas canvas;
    GameCanvas() {
        canvas = this;
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Background
        g2.setColor(Game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());
        
        g2.setColor(Color.WHITE);
        Game.currentLevel.drawEntities(g2);

        g2.setColor(new Color(0255,255,255,100));
        Game.nextLevel.drawEntities(g2);

        g2.dispose();
    }


}
