package agh.ics.oop.model

class BouncyMap(private val animalsMap: MutableMap<Vector2d, Animal>, private val mapSize: Vector2d) : IWorldMap {

    fun move(animal: Animal, direction: MoveDirection){
        animal.move(direction, this)
    }

    override fun place(animal: Animal) {
        if( canMoveTo(animal.position) ){  // na poczatku sprawdzam czy wogole moge tam isc
            val foundAnimal: Animal? = animalsMap.values.find { it === animal}

            if( foundAnimal != null ){  // udalo sie znalezc juz obiekt
                animalsMap.entries.removeIf { it.value === animal }  // usuwam z mapy, konkretne zwierze, ktore jest rowne referencja
            }

            if( isOccupied(animal.position) ){  // znaczy, ze ktos tam jest aktualnie, i musimy nasze zwierze odbic
                val randomFreePlace: Vector2d? = animalsMap.randomFreePosition(mapSize)

                if( randomFreePlace != null ){
                    animal.position = randomFreePlace  // ustawiam nowe miejsce zwierzakowi
                    animalsMap[animal.position] = animal
                }
            }
            else{  // nie ma nikogo na tej pozycji, a wiec bezpiecznie mozmy tam isc
                animalsMap[animal.position] = animal
            }
        }
    }

    override fun objectAt(position: Vector2d): WorldElement? = animalsMap[position]

    override fun canMoveTo(position: Vector2d): Boolean = position <= mapSize && position >= Vector2d(0,0)

}