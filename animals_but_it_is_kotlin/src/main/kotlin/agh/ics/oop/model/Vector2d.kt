package agh.ics.oop.model

data class Vector2d(val x: Int, val y: Int) {

    operator fun compareTo(other: Vector2d): Int {
        if (other.x <= x && other.y < y || other.x < x && other.y <= y)
            return 1

        else if (this == other)
            return 0

        return -1
    }

    operator fun plus(other: Vector2d): Vector2d = Vector2d(x + other.x, y + other.y)

    operator fun minus(other: Vector2d): Vector2d = Vector2d(x - other.x, y - other.y)

    fun upperRight(other: Vector2d) = Vector2d(Math.max(other.x, x), Math.max(other.y, y))

    fun lowerLeft(other: Vector2d) = Vector2d(Math.min(other.x, x), Math.min(other.y, y))

    fun opposite() = Vector2d(-x, -y)

    override fun toString(): String = "($x,$y)"
}

fun MapDirection.toUnitVector(): Vector2d {
    return when (this) {
        MapDirection.NORTH -> Vector2d(0, 1)
        MapDirection.SOUTH -> Vector2d(0, -1)
        MapDirection.WEST -> Vector2d(-1, 0)
        MapDirection.EAST -> Vector2d(1, 0)
    }
}
