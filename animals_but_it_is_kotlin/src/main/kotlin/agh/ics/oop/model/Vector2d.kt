package agh.ics.oop.model

import java.util.*

data class Vector2d(val x: Int, val y: Int) {

    fun precedes(other: Vector2d): Boolean {
        return other.x >= x && other.y >= y
    }

    fun follows(other: Vector2d): Boolean {
        return other.x <= x && other.y <= y
    }

    fun add(other: Vector2d): Vector2d {
        return Vector2d(x + other.x, y + other.y)
    }

    fun subtract(other: Vector2d): Vector2d {
        return Vector2d(x - other.x, y - other.y)
    }

    fun upperRight(other: Vector2d): Vector2d {
        return Vector2d(Math.max(other.x, x), Math.max(other.y, y))
    }

    fun lowerLeft(other: Vector2d): Vector2d {
        return Vector2d(Math.min(other.x, x), Math.min(other.y, y))
    }

    fun opposite(): Vector2d {
        return Vector2d(-x, -y)
    }

    override fun toString(): String {
        return "($x,$y)"
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val vector2d = o as Vector2d
        return x == vector2d.x && y == vector2d.y
    }

    override fun hashCode(): Int {
        return Objects.hash(x, y)
    }
}