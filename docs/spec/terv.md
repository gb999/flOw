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
interface Drawable {
    + void draw()
}

abstract class Entity implements Drawable{
    Point position

    + void update()
}
class HostileEntity extends Entity {
    eat(int foodValue)
}
class CellChain extends HostileEntity {
    LinkedList<BodySegment> body
}
class Player extends CellChain {

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

abstract BodySegment implements Drawable {

}

class Mouth extends BodySegment
class VitalPart extends BodySegment
class SimpleCell extends BodySegment


@enduml
```
A játék osztálydiagramja

