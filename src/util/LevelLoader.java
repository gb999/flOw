package util;

import java.util.ArrayList;
import java.util.function.Consumer;

import flow.Level;

public class LevelLoader {
    ArrayList<Consumer<Level>> commands;    

    LevelLoader() {
        commands = new ArrayList<>();
    }
    public void addCommand(Consumer<Level> command) {
        commands.add(command);
    }

    public Level loadLevel() {
        Level level = new Level();
        commands.forEach(command -> command.accept(level));

        return level;
    }
}
