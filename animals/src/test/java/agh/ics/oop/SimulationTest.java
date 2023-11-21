package agh.ics.oop;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void DoesArgumentsOnInputAreCorrectlyInterpreted() { //czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane?
        //given
        String[] args = {"r","b","b","l","x","21","?","b","b"};
        List<Vector2d> positions = List.of(new Vector2d(1,2), new Vector2d(5,4),new Vector2d(3,2));
        List<Vector2d> animalEndPositions = List.of(new Vector2d(0,0));
        RectangularMap map = new RectangularMap(10,10);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        Simulation simulation = new Simulation(positions, directions,map);

        //then
        simulation.run();
        List<Animal> solutionAnimals = simulation.getAnimalsList();
        System.out.println(solutionAnimals.get(0).getPosition());
        for(int i = 0; i < animalEndPositions.size(); i++){
            assertEquals(solutionAnimals.get(i).getPosition(), animalEndPositions.get(i));
        }
    }

    @Test
    void DoesTheAnimalHaveTheRightOrientation() { //czy zwierzę ma właściwą orientację?
        //given
        String[] args = {"r", "r", "f"};
        Animal animal = new Animal(new Vector2d(3,2),);
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        for(MoveDirection direction : directions){
            animal.move(direction,map);
        }

        //then
        assertEquals(MapDirection.SOUTH, animal.getOrientation() );
    }
    @Test
    void IsTheAnimalInTheRightPosition() { //czy zwierzę przemieszcza się na właściwe pozycje?
        //given
        String[] args = {"r", "r", "f"};
        Animal animal = new Animal(new Vector2d(3,2));
        Vector2d animalEndPosition = new Vector2d(3,1);
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        for(MoveDirection direction : directions){
            animal.move(direction,map);
        }

        //then
        assertEquals(animalEndPosition, animal.getPosition());
    }

    @Test
    void DoesAnimalNotGoOffTheMapRightSight() { //czy zwierzę nie wychodzi poza mapę?
        //given
        String[] args = {"f","f","f","f","f","r","f","f"};
        Animal animal = new Animal(new Vector2d(3,1));
        Vector2d animalEndPosition = new Vector2d(4,4);
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        for(MoveDirection direction : directions){
            animal.move(direction,map);
        }

        //then
        assertEquals(animalEndPosition, animal.getPosition());
    }

    @Test
    void DoesAnimalNotGoOffTheMapLeftSight() { //czy zwierzę nie wychodzi poza mapę?
        //given
        String[] args = {"r","r","f","f","f","r","f","f","f","f"};
        Animal animal = new Animal(new Vector2d(3,1));
        Vector2d animalEndPosition = new Vector2d(0,0);
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        for(MoveDirection direction : directions){
            animal.move(direction,map);
        }

        //then
        assertEquals(animalEndPosition, animal.getPosition());
    }

}