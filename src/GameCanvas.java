import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;

public class GameCanvas extends Canvas implements Runnable {
    double FPS = 30;
    Thread gameThread;
    GameCanvas() {
        run();
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double dt = 0;
        double drawInterval = 1000000000 / FPS;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            dt += (currentTime-lastTime) / drawInterval;
            lastTime = currentTime;

            if(dt >= 1) {
                update();
                repaint();
                dt--;
            }



        }
    }

    public void update() {
        Point mouse =  MouseInfo.getPointerInfo().getLocation();
        Point canvasCoords = this.getLocationOnScreen();
        System.out.println(mouse.getX()-canvasCoords.getX());
        // System.out.println(mouse.getY()-canvasCoords.getY());


    }
    public void paint(Graphics g) {
        super.paint(g);


        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.blue);
        g2.fillRect(0, 0, 100, 100);
        g2.dispose();
    }



}
