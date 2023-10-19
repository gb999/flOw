package entities;

import java.util.LinkedList;

abstract class Entity {
    int x, y;
    LinkedList<BodySegment> body;
    public Entity() {

    }   

    
    abstract <T extends Edible> void digest(/*Food*/);

}
