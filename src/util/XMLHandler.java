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
import flow.entities.hostile.ChainCreature;

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
                int count = Integer.parseInt(attributes.getValue("count"));
                int foodValue = Integer.parseInt(attributes.getValue("foodValue"));
                level.spawnPeacefulCells(count, foodValue);
                break;
            case SPAWNHOSTILECREATURE:
                String creatureType = attributes.getValue("type");
                switch (creatureType) {
                    case "ChainCreature":
                        level.addHostileCreature(new ChainCreature(Vec2.getRandomVec2InRadius(1000)));
                        
                        break;
                    default:
                        throw new Error("No such creature as " + creatureType );
                }
                break;

            default:
                break;
        }
    }

    
}

