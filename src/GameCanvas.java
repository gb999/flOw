import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class GameCanvas extends Canvas {
    Game game;

    GameCanvas(Game game) {
        this.game = game;
    }

    public void update() {
        Point mouse =  MouseInfo.getPointerInfo().getLocation();
        Point canvasCoords = this.getLocationOnScreen();
        System.out.println(mouse.getX()-canvasCoords.getX());
        // System.out.println(mouse.getY()-canvasCoords.getY());


    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;

        
        g2.setColor(game.currentLevel.color);
        g2.fillRect(0, 0,  getWidth(), getHeight());

        g2.dispose();
    }




}
