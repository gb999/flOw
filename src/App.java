import java.awt.*;
import  javax.swing.*;

public class App extends JFrame {
    private static GameCanvas canvas;
    // Constructor
    public App() {
        initUI();
    }

    private void initUI() {
        setSize(600, 400);
        setTitle("flOw");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas = new GameCanvas();
        add(canvas);
    }


    public static void main(String[] args){
        App app = new App();
        app.setVisible(true);
        canvas.startGameThread();
        
        
    }
}
