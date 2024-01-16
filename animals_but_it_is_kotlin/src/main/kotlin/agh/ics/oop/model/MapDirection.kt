package agh.ics.oop.model

enum class MapDirection {
    NORTH,
    SOUTH,
    WEST,
    EAST;

    operator fun next(): MapDirection {
        return when (this) {
            NORTH -> EAST
            SOUTH -> WEST
            WEST -> NORTH
            EAST -> SOUTH
        }
    }

    fun previous(): MapDirection {
        return when (this) {
            NORTH -> WEST
            SOUTH -> EAST
            WEST -> SOUTH
            EAST -> NORTH
        }
    }

    fun toUnitVector(): Vector2d {
        return when (this) {
            NORTH -> Vector2d(0, 1)
            SOUTH -> Vector2d(0, -1)
            WEST -> Vector2d(-1, 0)
            EAST -> Vector2d(1, 0)
        }
    }

    override fun toString(): String {
        return when (this) {
            NORTH -> "Północ"
            SOUTH -> "Południe"
            WEST -> "Zachód"
            EAST -> "Wschód"
        }
    }
}