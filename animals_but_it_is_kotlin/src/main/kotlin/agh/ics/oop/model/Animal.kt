package agh.ics.oop.model

data class Animal(var position: Vector2d = Vector2d(2, 2)) : WorldElement{
    var orientation = MapDirection.NORTH

    fun isAt(position: Vector2d): Boolean = position == this.position

    fun move(direction: MoveDirection) {
        orientation = when (direction) {
            MoveDirection.LEFT -> orientation.previous()
            MoveDirection.RIGHT -> orientation.next()
            MoveDirection.FORWARD, MoveDirection.BACKWARD -> orientation
        }
        position = when (direction) {  // pomijam logike z orginalnej czesci poruszania sie
            MoveDirection.FORWARD -> position + orientation.toUnitVector()
            MoveDirection.BACKWARD -> position - orientation.toUnitVector()
            MoveDirection.LEFT, MoveDirection.RIGHT -> position
        }
    }

    override fun toString(): String {
        return when (orientation) {
            MapDirection.NORTH -> "N"
            MapDirection.SOUTH -> "S"
            MapDirection.EAST -> "E"
            MapDirection.WEST -> "W"
        }
    }
}