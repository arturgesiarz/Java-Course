package agh.ics.oop.model

data class Vector2d(val x: Int, val y: Int) {

    fun precedes(other: Vector2d) = other.x >= x && other.y >= y

    fun follows(other: Vector2d) = other.x <= x && other.y <= y

    fun add(other: Vector2d)= Vector2d(x + other.x, y + other.y)

    fun subtract(other: Vector2d) = Vector2d(x - other.x, y - other.y)

    fun upperRight(other: Vector2d) = Vector2d(Math.max(other.x, x), Math.max(other.y, y))

    fun lowerLeft(other: Vector2d) = Vector2d(Math.min(other.x, x), Math.min(other.y, y))

    fun opposite() = Vector2d(-x, -y)

    override fun toString(): String {
        return "($x,$y)"
    }
}