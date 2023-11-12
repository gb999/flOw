package flow;
import java.awt.Color;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.IOException;
import java.security.cert.X509CRLSelector;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

import javax.swing.JFrame;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.XMLReader;

import util.Vec2;
import util.XMLHandler;

public class Game implements Runnable {
    private GameCanvas canvas;
    private JFrame window;
    public static Level currentLevel;
    protected static Level nextLevel;
    private static List<Level> levels;
    private static Player player;

    public static void changeLevel(boolean next) {
        currentLevel = nextLevel;
        currentLevel.player = player;

        nextLevel = new Level(new Color(0)); //load next
    }

    private double FPS = 30;
    private Thread gameThread;

    private void initUI() {
        window = new JFrame("flOw");
        window.setSize(600, 400);
        window.setTitle("flOw");
        window.setLocationRelativeTo(null);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new GameCanvas();
        window.add(canvas);
        window.setVisible(true);
    }
    public void addLevel(Level level) {
        levels.add(level);
    }
    public List<Level> getLevels() {
        return levels;
    }

    public Game() {
        initUI();
        levels = new ArrayList<>();
        currentLevel = new Level(new Color(0));
        nextLevel = new Level(new Color(0));
        player = new Player(new Vec2(this.window.getWidth()/2, this.window.getHeight()/2));
        currentLevel.player = player;
        gameThread = new Thread(this);
    }

    public void start() {
        gameThread.start();
    }


    public void update() {
        player.update();
        currentLevel.update();
        //nextLevel.update();
    }
    

    public void repaint() {
        canvas.repaint();
    }

    /**
     * Game loop. 
     */
    @Override
    public void run() {
        double dt = 0;
        double drawInterval = 1000000000 / FPS; // 1 s = 1000000000 ns
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



}
