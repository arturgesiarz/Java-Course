package agh.ics.oop.model
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : StringSpec({

    "place - add animal to an empty position" {
        // given
        val mapSize = Vector2d(5, 5)
        val bouncyMap = BouncyMap(mutableMapOf(), mapSize)
        val animal = Animal(Vector2d(2, 2))

        // when
        bouncyMap.place(animal)

        // then
        bouncyMap.objectAt(Vector2d(2, 2)) shouldBe animal
    }

    "place - add animal to occuped position" {
        // given
        val mapSize = Vector2d(5, 5)
        val animalsMap = mutableMapOf(Vector2d(2, 2) to Animal(Vector2d(2, 2)))
        val bouncyMap = BouncyMap(animalsMap, mapSize)
        val animal = Animal(Vector2d(2, 2))

        // when
        bouncyMap.place(animal)

        // then
        bouncyMap.objectAt(Vector2d(2, 2)) shouldNotBe null
        animalsMap.values.find { it === animal} shouldNotBe null
    }

    "place - do not add animal to place" {
        // given
        val mapSize = Vector2d(1, 1)

        val animalsMap = mutableMapOf(
            Vector2d(0, 0) to Animal(Vector2d(0, 0)),
            Vector2d(0, 1) to Animal(Vector2d(0, 1)),
            Vector2d(1, 0) to Animal(Vector2d(1, 0)),
            Vector2d(1, 1) to Animal(Vector2d(1, 1)))

        val bouncyMap = BouncyMap(animalsMap, mapSize)

        val animal = Animal(Vector2d(2, 2))

        // when
        bouncyMap.place(animal)

        // then
        animalsMap.values.find { it === animal} shouldBe null
    }

    "canMoveTo - return true for a valid position" {
        // given
        val mapSize = Vector2d(5, 5)
        val bouncyMap = BouncyMap(mutableMapOf(), mapSize)

        // when, then
        bouncyMap.canMoveTo(Vector2d(3, 3)) shouldBe true
    }

    "canMoveTo - return false for a position outside the map" {
        // given
        val mapSize = Vector2d(5, 5)
        val bouncyMap = BouncyMap(mutableMapOf(), mapSize)

        // when, then
        bouncyMap.canMoveTo(Vector2d(6, 6)) shouldBe false
    }

    "move - return true for the movement has taken place" {
        // given
        val mapSize = Vector2d(10, 10)

        val animal1 = Animal(Vector2d(0,0))
        val animal2 = Animal(Vector2d(3,4))

        val animalsMap = mutableMapOf(
            Vector2d(0, 0) to animal1,
            Vector2d(3, 4) to animal2)
        val bouncyMap = BouncyMap(animalsMap, mapSize)

        // when
        bouncyMap.move(animal1, MoveDirection.FORWARD)
        bouncyMap.move(animal1, MoveDirection.FORWARD)
        bouncyMap.move(animal1, MoveDirection.FORWARD)

        bouncyMap.move(animal2, MoveDirection.RIGHT)
        bouncyMap.move(animal2, MoveDirection.FORWARD)
        bouncyMap.move(animal2, MoveDirection.FORWARD)


        // then
        animal1.isAt(Vector2d(0,3)) shouldBe true
        animal2.isAt(Vector2d(5,4)) shouldBe true
    }
})
