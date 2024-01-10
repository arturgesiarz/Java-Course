package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AbstractWorldMapTest {

    @Test
    void doesGetOrderedAnimalsWorkCorrectly() {
        // given

        // tworze nowa mape
        GrassField map = new GrassField(5);

        // tworze nowe zwierzeta
        Animal animalA = new Animal(new Vector2d(3,20));
        Animal animalB = new Animal(new Vector2d(5,7));
        Animal animalC = new Animal(new Vector2d(5,8));
        Animal animalD = new Animal(new Vector2d(1,-2));

        // when

        // klade na mapie zwierzeta
        try {
            map.place(animalA);
            map.place(animalB);
            map.place(animalC);
            map.place(animalD);
        } catch (PositionAlreadyOccupiedException e){
            e.printStackTrace();
        }

        // tworze liste posortowanych zwierzat zgodnie z wczesniej zaimplementowana metoda
        List<Animal> orderedAnimal = map.getOrderedAnimals();

        // then

        // tworze liste wynika - czyli takie posortwanie jakie powininem wczesniej uzyskac
        List<Animal> orderedAnimalSolution = new ArrayList<>(List.of(animalD, animalA, animalB, animalC));

        // porownujemy czy obie listy sa sobie rowne
        assertEquals(orderedAnimalSolution, orderedAnimal);


    }
}