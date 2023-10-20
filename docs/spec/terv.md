# A játék struktúrája
A játék a megjelenítéshez egy Canvas objektumot használok. Egy végtelen ciklus folyamatosan frissíti a játék állapotát, valamint újrarajzolja a Canvas-t.

```plantuml
@startuml
class Level {
    - ArrayList Entities 
} 
static class Game {
    - Canvas 
    - Level currentLevel   
    - Level nextLevel

    + loadLevel()
    + void update()
    + void repaint()
}


abstract class Entity {
    Point position

    + void update()
    + void draw()

}
class HostileEntity extends Entity {
    eat(int foodValue)
}
class ChainCreature extends HostileEntity {
    LinkedList<BodySegment> body
}
class Player extends ChainCreature {

}

class PeacefulCell extends Entity implements Edible {
    + void update()
}

class BlueCell extends PeacefulCell 
class RedCell extends PeacefulCell 
class ExtraVital extends PeacefulCell
class BiggerMouth extends PeacefulCell


interface Edible {
    void isEatenBy(HostileEntity, foodValue)
}

abstract BodySegment extends Entity {

}

class Mouth extends BodySegment
class VitalSegment extends BodySegment implements Edible
class SimpleSegment extends BodySegment


@enduml
```
A játék osztálydiagramja

