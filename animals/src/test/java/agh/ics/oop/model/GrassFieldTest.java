package agh.ics.oop.model;
import agh.ics.oop.Simulation;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GrassFieldTest {
    @Test
    void doesPlaceMethodWorkCorrectly(){
        //given
        List<MoveDirection> directions = List.of(
                MoveDirection.RIGHT,MoveDirection.RIGHT,MoveDirection.LEFT,MoveDirection.RIGHT, MoveDirection.RIGHT);
        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(7,1),new Vector2d(8,4), new Vector2d(8,4));
        GrassField map = new GrassField(5);

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
                MoveDirection.FORWARD,MoveDirection.FORWARD,MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD, MoveDirection.BACKWARD);

        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(1,10),new Vector2d(0,0),new Vector2d(0,0), new Vector2d(-1,-4));

        List<Vector2d> endPositionsOfAnimals = List.of(
                new Vector2d(1,12),new Vector2d(0,2), new Vector2d(-1,-4));

        GrassField map = new GrassField(15);

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
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD,
                MoveDirection.FORWARD, MoveDirection.FORWARD
        );

        List<Vector2d> positionsOfAnimals = List.of(
                new Vector2d(-5,0),new Vector2d(-1,0),new Vector2d(-1,0),new Vector2d(-1,0));

        List<Vector2d> endPositionsOfAnimals = List.of(
                new Vector2d(-3,0),new Vector2d(-2,0));

        GrassField map = new GrassField(15);

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