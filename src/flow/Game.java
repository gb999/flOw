package flow;
import javax.swing.JFrame;

import util.Vec2;

public class Game implements Runnable {
    private GameCanvas canvas;
    private JFrame window;
    public static Level currentLevel;
    protected static Level nextLevel;
    private Level levels[];
    private Player player;

    public static void changeLevel(boolean next) {

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

    public Game() {
        initUI();
        currentLevel = new Level();
        nextLevel = new Level();
        player = new Player(new Vec2(this.window.getWidth()/2, this.window.getHeight()/2));
        currentLevel.player = player;
        gameThread = new Thread(this);
    }

    public void start() {
        gameThread.start();
    }


    public void update() {
        canvas.update();
        currentLevel.update();
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
