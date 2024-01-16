package agh.ics.oop.model

interface IWorldMap {

    fun place(animal: Animal)

    fun isOccupied(position: Vector2d): Boolean = objectAt(position) != null

    fun objectAt(position: Vector2d): WorldElement?


}