package agh.ics.oop.model;

import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    @Test
    void doesPlaceMethodWorkCorrectly(){
        //given
        List<MoveDirection> directions = List.of(
                MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.RIGHT, MoveDirection.RIGHT);
        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(7,1),new Vector2d(8,4));
        RectangularMap map = new RectangularMap(9,9);

        //when
        Simulation simulation = new Simulation(positionsOfAnimals, directions, map);
        simulation.run();

        //then
        Map<Vector2d, Animal> animalSolution = map.getAnimals();
        for(Vector2d positionOfAnimal : positionsOfAnimals){
            assertTrue(animalSolution.containsKey(positionOfAnimal));
        }

    }

    @Test
    void doMoveAndObjectAtMethodsWorksCorrectly(){
        //given
        List<MoveDirection> directions = List.of(
                MoveDirection.RIGHT,MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.RIGHT,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );

        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(0,0),new Vector2d(4,4));

        List<Vector2d> endPositionsOfAnimals = List.of(
                new Vector2d(2,0),new Vector2d(4,1));

        RectangularMap map = new RectangularMap(5,5);

        //when
        Simulation simulation = new Simulation(positionsOfAnimals, directions, map);
        simulation.run();

        //then
        Map<Vector2d, Animal> animalSolution = map.getAnimals();
        for(Vector2d endPositionOfAnimal : endPositionsOfAnimals){
            assertTrue(animalSolution.containsKey(endPositionOfAnimal));
        }
    }

    @Test
    void doesIsOccupiedMethodWorkCorrectly(){
        //given
        List<MoveDirection> directions = List.of(
                MoveDirection.RIGHT,MoveDirection.LEFT,
                MoveDirection.FORWARD,MoveDirection.LEFT,
                MoveDirection.FORWARD,MoveDirection.LEFT,
                MoveDirection.FORWARD,MoveDirection.LEFT
        );

        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(0,0),new Vector2d(2,0));

        List<Vector2d> endPositionsOfAnimals = List.of(
                new Vector2d(1,0),new Vector2d(2,0));

        RectangularMap map = new RectangularMap(3,3);

        //when
        Simulation simulation = new Simulation(positionsOfAnimals, directions, map);
        simulation.run();

        //then
        Map<Vector2d, Animal> animalSolution = map.getAnimals();
        for(Vector2d endPositionOfAnimal : endPositionsOfAnimals){
            assertTrue(animalSolution.containsKey(endPositionOfAnimal));
        }
    }



}