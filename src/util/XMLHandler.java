package util;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Handler;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import flow.Game;
import flow.Level;

public class XMLHandler extends DefaultHandler {
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";
    private static final String SPAWNPEACEFULCELLS = "SpawnPeacefulCells";
    private static final String SPAWNHOSTILECREATURE = "SpawnHostileCreature";

    private Game game;
    private Level level;

    public Game getGame() {
        return game;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName) {
            case GAME:
                game = new Game();
                break;
            case LEVEL:
                String colorHex = attributes.getValue("color"); 
                level = new Level(Color.decode(colorHex));
                game.addLevel(level);
                break;
            case SPAWNPEACEFULCELLS:
                // level.spawnPeacefulCells();
                break;
            case SPAWNHOSTILECREATURE:
                // level.spawnPeacefulCells();
                break;

            default:
                break;
        }
    }

    
}

