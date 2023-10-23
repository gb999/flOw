import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entities.Entity;

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

        for(Entity e : game.currentLevel.entities) {
            e.draw(g2);
        }


        g2.dispose();
    }




}
