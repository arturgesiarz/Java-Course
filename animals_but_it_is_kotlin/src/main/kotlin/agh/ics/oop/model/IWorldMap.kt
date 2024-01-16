package agh.ics.oop.model

interface IWorldMap {

    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(position: Vector2d): Boolean = objectAt(position) != null

    fun objectAt(position: Vector2d): WorldElement?
}