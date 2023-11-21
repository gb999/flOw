import java.io.File;

import  javax.swing.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import flow.Game;
import util.XMLHandler;

public class App extends JFrame {
    public static void main(String[] args){
        // Load levels and start game
        File file = new File("resources/levels.xml");
        XMLHandler handler = new XMLHandler();
        SAXParser parser;
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            parser.parse(file, handler);
            Game game = handler.getGame();
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }  
    }
}
