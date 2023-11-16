package util;

import java.awt.Color;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import flow.Game;
import flow.Level;
import flow.entities.hostile.ChainCreature;
import flow.entities.peaceful.BlueCell;
import flow.entities.peaceful.PeacefulCell;
import flow.entities.peaceful.RedCell;

public class XMLHandler extends DefaultHandler {
    private static final String GAME = "Game";
    private static final String LEVEL = "Level";
    private static final String SPAWNPEACEFULCELLS = "SpawnPeacefulCells";
    private static final String SPAWNHOSTILECREATURE = "SpawnHostileCreature";

    private Game game;
    private LevelLoader levelLoader;

    private ArrayList<LevelLoader> levelLoaders = new ArrayList<>(); 

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
                levelLoader = new LevelLoader();
                String colorHex = attributes.getValue("color"); 
                Color color = Color.decode(colorHex);
                levelLoader.addCommand((level) -> level.setColor(color));
                levelLoaders.add(levelLoader);
                break;
            case SPAWNPEACEFULCELLS:
                int count = Integer.parseInt(attributes.getValue("count"));
                int foodValue = Integer.parseInt(attributes.getValue("foodValue"));
                levelLoader.addCommand(level -> level.spawnPeacefulCells(count, foodValue));
                break;
            
            case SPAWNHOSTILECREATURE:
                String creatureType = attributes.getValue("type");
                switch (creatureType) {
                    case "ChainCreature":
                        ChainCreature creature = new ChainCreature(Vec2.getRandomVec2InRadius(1000));
                        levelLoader.addCommand(level->level.addHostileCreature(creature));
                        break;
                    default:
                        throw new Error("No such creature as " + creatureType );
                }
                break;

            default:
                break;
        }
    }
    @Override 
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals(GAME)) {
            // Add cells for changing levels
            for(int i = 0 ; i < levelLoaders.size() - 1; i++) {
                levelLoaders.get(i).addCommand(level-> {
                    level.addEdible(new RedCell(Vec2.getRandomVec2InRadius(1500)));
                });
            }
            for(int i = 1 ; i < levelLoaders.size(); i++) {
                levelLoaders.get(i).addCommand(level-> {
                    level.addEdible(new BlueCell(Vec2.getRandomVec2InRadius(1500)));
                });
            }
            game.setLevelLoaders(levelLoaders);
        }
    }

    
}

