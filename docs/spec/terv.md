# A játék struktúrája
A játék a megjelenítéshez egy Canvas objektumot használok. Egy végtelen ciklus folyamatosan frissíti a játék állapotát, valamint újrarajzolja a Canvas-t.

```plantuml
@startuml
class Level {
    - ArrayList Entities 
} 
class Game {
    - Canvas 
    - Level currentLevel   
    - Level nextLevel
    - Level loadLevel()

    + void update()
    + void repaint()
}
Level -- Game::currentLevel
Level -- Game::nextLevel

abstract class Entity {
    Point position

    + void update()
    + void draw()

}
abstract HostileCreature extends Entity {
    eat(int foodValue)
}
class ChainCreature extends HostileCreature {
    LinkedList<BodySegment> body
}
class Player extends ChainCreature {

}

class PeacefulCell extends Entity implements Edible {
    + void update()
}

abstract ChangeLevelCell extends PeacefulCell 

class BlueCell extends ChangeLevelCell 
class RedCell extends ChangeLevelCell 
class ExtraVital extends PeacefulCell
class BiggerMouth extends PeacefulCell


interface Edible {
    void isEatenBy(HostileCreature)
}

abstract BodySegment extends Entity {

}

class Mouth extends BodySegment
class VitalSegment extends BodySegment implements Edible
class SimpleSegment extends BodySegment


@enduml
```
A játék osztálydiagramja

