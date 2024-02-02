package agh.ics.oop.model


fun Map<Vector2d, WorldElement>.randomPosition(): Vector2d? {
    return this.keys.toList().randomOrNull()
}
// zakladam ze mapSize jest prawym gornym wierzcholkiem, prostokata, ktory ograniczna mape
fun Map<Vector2d, WorldElement>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val allPositions: Set<Vector2d> = mapSize.getAllPositions()
    val occupiedPositions: Set<Vector2d> = this.keys
    val freePositions: Set<Vector2d> = allPositions - occupiedPositions  // robie roznice zbiorow

    return freePositions.randomOrNull()
}

// funkcja pomocniczna wyznaczajaca mi wszystkie zajete pozycje
private fun Vector2d.getAllPositions(): Set<Vector2d> {
    val positions = HashSet<Vector2d>()

    // dodaje kazde mozliwe przejscie
    for (x in 0 until this.x) {
        for (y in 0 until this.y) {
            positions.add(Vector2d(x, y))
        }
    }
    return positions
}

