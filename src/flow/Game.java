package flow;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import util.LevelLoader;
import util.Vec2;

public class Game implements Runnable {
    public static GameCanvas canvas;
    private JFrame window;
    private static int currentLevelIndex;
    public static Level currentLevel;
    protected static Level nextLevel;
    private static List<LevelLoader> levelLoaders;
    
    public static Player player;

    private double FPS = 30;
    private Thread gameThread;


    /**
     * Change level to next if next is true,
     * Change level to previous level if next is false.
     * @param next
     */
    public static void changeLevel(boolean next) {
        if(next) {
            currentLevelIndex += 1; 
            currentLevel = nextLevel;
            if(currentLevelIndex + 1 < levelLoaders.size())
                nextLevel = levelLoaders.get(currentLevelIndex + 1).loadLevel();
        } else {
            nextLevel = levelLoaders.get(currentLevelIndex).loadLevel();
            currentLevelIndex -= 1; 
            currentLevel = levelLoaders.get(currentLevelIndex).loadLevel();
        }        
        currentLevel.player = player;
    }

    /**
     * Initializes Swing GUI
     */
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


    public Game() {
        initUI();
        currentLevelIndex = 0;
        levelLoaders = new ArrayList<>();
        player = new Player(new Vec2(0,0));
        gameThread = new Thread(this);
    }
    
    
    public void start() {
        currentLevel = levelLoaders.get(0).loadLevel();
        nextLevel = levelLoaders.get(1).loadLevel();
        currentLevel.player = player;
        gameThread.start();
    }


    /**
     * Called FPS times per second. Updates all entities
     */
    public void update() {
        player.update();
        currentLevel.update(true);
        nextLevel.update(false);
    }
    
    /**
     * Paints everything
     */
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
            dt += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(dt >= 1) {
                update();
                repaint();
                dt--;
            }
        }
    }

    /**
     * Sets levelLoaders. LevelLoaders are used to reload levels, when player enters another level.
     * @param levelLoaders list of the loaders
     */
    public static void setLevelLoaders(ArrayList<LevelLoader> levelLoaders) {
        Game.levelLoaders = levelLoaders; 
    }
}
