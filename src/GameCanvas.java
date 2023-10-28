import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GameCanvas extends JPanel {
    Game game;
    static GameCanvas canvas;
    GameCanvas(Game game) {
        this.game = game;
        canvas = this;
    }


    public void update() {

    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        // Background
        g2.setColor(game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());
        
        g2.setColor(Color.WHITE);
        game.currentLevel.drawEntities(g2);

        g2.setColor(new Color(0255,255,255,100));
        game.nextLevel.drawEntities(g2);


        g2.dispose();
    }




}
