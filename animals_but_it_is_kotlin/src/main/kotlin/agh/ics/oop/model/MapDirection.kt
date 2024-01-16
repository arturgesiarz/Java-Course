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

    override fun toString(): String {
        return when (this) {
            NORTH -> "Północ"
            SOUTH -> "Południe"
            WEST -> "Zachód"
            EAST -> "Wschód"
        }
    }
}