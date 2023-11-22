package util;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Optional;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import flow.Game;
import flow.entities.hostile.ChainCreature;
import flow.entities.peaceful.BlueCell;
import flow.entities.peaceful.RedCell;

/**
 * Reads an XML file containing all game information. 
 */
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
                        Vec2 randomPos = Vec2.getRandomVec2InRadius(1000);
                        int length = Integer.parseInt(attributes.getValue("length"));
                        int nVitalSegments = Integer.parseInt(attributes.getValue("vitalSegments"));
                        
                        boolean agressive = attributes.getValue("agressive") == null ? false : true;
                        Optional<String> attackDistance = Optional.ofNullable(attributes.getValue("attack-distance"));
                        Optional<String> viewDistance = Optional.ofNullable(attributes.getValue("view-distance"));
                        Optional<String> restTime = Optional.ofNullable(attributes.getValue("rest-time"));
                        Optional<String> speed = Optional.ofNullable(attributes.getValue("speed"));
                        
                        ChainCreature creature = new ChainCreature(randomPos, length, nVitalSegments);
                                                
                        creature.setAgressive(agressive);
                        attackDistance.ifPresent(val-> creature.setAttackDistance(Integer.parseInt(val)));
                        viewDistance.ifPresent(val -> creature.setViewDistance(Integer.parseInt(val)));
                        restTime.ifPresent(val -> creature.setRestTime(Integer.parseInt(val)));
                        speed.ifPresent(val -> creature.setSpeed(Double.parseDouble(val)));


                        levelLoader.addCommand(level->level.addHostileCreature(creature.clone()));
                        break;
                    default:
                        throw new Error("No such creature as " + creatureType);
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
                    level.setRedCell(new RedCell(Vec2.getRandomVec2InRadius(1500), game));
                });
            }
            for(int i = 1 ; i < levelLoaders.size(); i++) {
                levelLoaders.get(i).addCommand(level-> {
                    level.setBlueCell(new BlueCell(Vec2.getRandomVec2InRadius(1500), game));
                });
            }
            game.setLevelLoaders(levelLoaders);
        }
    }
}