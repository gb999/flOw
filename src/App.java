import java.io.File;
import java.io.IOException;

import  javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import flow.Game;
import util.XMLHandler;

public class App extends JFrame {
    public static void main(String[] args){
        //Game game = new Game();
        //game.start();

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
