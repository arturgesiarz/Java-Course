package agh.ics.oop.model

interface MoveValidator {

    fun canMoveTo(position: Vector2d): Boolean

    fun place(animal: Animal)

}