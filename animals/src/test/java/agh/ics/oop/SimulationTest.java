package agh.ics.oop;
import agh.ics.oop.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {
    @Test
    void doesArgumentsOnInputAreCorrectlyInterpreted() { //czy dane wejściowe podane jako tablica znaków są poprawnie interpretowane?
        //given
        String[] args = {"666","r","?","r","?","f","?","f","x","l","21","f","2137","f","f","r","f","f","r"};
        List<Vector2d> positions = List.of(new Vector2d(7,1),new Vector2d(8,4));
        List<Vector2d> animalEndPositions = List.of(new Vector2d(9,2),new Vector2d(9,4));
        RectangularMap map = new RectangularMap(10,10);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();

        //then
        List<Animal> solutionAnimals = simulation.getAnimalsList();

        for(int i = 0; i < animalEndPositions.size(); i++){
            assertEquals(solutionAnimals.get(i).getPosition(), animalEndPositions.get(i));
        }
    }

    @Test
    void doesTheAnimalHaveTheRightOrientation() { //czy zwierzę ma właściwą orientację?
        //given
        String[] args = {"r","r","f","f","l","f","f","f","r","f","f","r"};
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(7,1),new Vector2d(8,4));
        List<MapDirection> animalsEndOrientation = List.of(MapDirection.EAST,MapDirection.SOUTH);

        RectangularMap map = new RectangularMap(10,10);

        //when
        Simulation simulation = new Simulation(positions, directions,map);
        simulation.run();

        //then
        List<Animal> solutionAnimals = simulation.getAnimalsList();

        for(int i = 0; i < animalsEndOrientation.size(); i++){
            assertEquals(solutionAnimals.get(i).getOrientation(), animalsEndOrientation.get(i));
        }
    }
    @Test
    void isTheAnimalInTheRightPosition() { //czy zwierzę przemieszcza się na właściwe pozycje?
        //given
        String[] args = {"r","r","f","r","f","f","r","f","f","f"};
        List<Vector2d> positions = List.of(new Vector2d(0,0),new Vector2d(4,4));
        List<Vector2d> animalEndPositions = List.of(new Vector2d(2,0),new Vector2d(4,1));
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        Simulation simulation = new Simulation(positions, directions,map);

        //then
        simulation.run();
        List<Animal> solutionAnimals = simulation.getAnimalsList();

        for(int i = 0; i < animalEndPositions.size(); i++){
            assertEquals(solutionAnimals.get(i).getPosition(), animalEndPositions.get(i));
        }
    }

    @Test
    void doesAnimalNotGoOffTheMap() { //czy zwierzę nie wychodzi poza mapę?
        //given
        String[] args = {"r","f","r","f","f","f","f"};
        List<Vector2d> positions = List.of(new Vector2d(0,0),new Vector2d(4,4));
        List<Vector2d> animalEndPositions = List.of(new Vector2d(0,0),new Vector2d(4,4));
        RectangularMap map = new RectangularMap(5,5);

        //when
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        Simulation simulation = new Simulation(positions, directions,map);

        //then
        simulation.run();
        List<Animal> solutionAnimals = simulation.getAnimalsList();

        for(int i = 0; i < animalEndPositions.size(); i++){
            assertEquals(solutionAnimals.get(i).getPosition(), animalEndPositions.get(i));
        }
    }

}