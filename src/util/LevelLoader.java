package util;

import java.util.ArrayList;
import java.util.function.Consumer;

import flow.Level;

/**
 * This class is userd to build a level.
 *  
 */
public class LevelLoader {
    ArrayList<Consumer<Level>> commands;    

    LevelLoader() {
        commands = new ArrayList<>();
    }
    public void addCommand(Consumer<Level> command) {
        commands.add(command);
    }

    /**
     * Creates a level and executes all commands loaded into thios LevelLoader
     * @return the generated level
     */
    public Level loadLevel() {
        Level level = new Level();
        commands.forEach(command -> command.accept(level));

        return level;
    }
}
