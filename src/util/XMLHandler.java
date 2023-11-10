package util;

import java.util.HashMap;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import flow.Level;

public class XMLHandler extends DefaultHandler {
    private List<Level> levels;
    private String currentElemet;
    private static HashMap<String, TagHandler> tags;
    static {
        tags.put("Game", new GameHandler());
        tags.put("Level", new LevelHandler());
        tags.put("SpawnPeacefulCells", new SpawnPeacefulCellsHandler());
        tags.put("SpawnHostileCreature", new SpawnHostileCreatureHandler());
    } 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        // System.out.println(uri +  localName + qName + attributes);
        
    }

    static abstract class TagHandler {

    }
    static class GameHandler extends TagHandler {
    }
    static class LevelHandler extends TagHandler {
    }
    static class SpawnPeacefulCellsHandler extends TagHandler {
    }
    static class SpawnHostileCreatureHandler extends TagHandler {
    }
    

}
