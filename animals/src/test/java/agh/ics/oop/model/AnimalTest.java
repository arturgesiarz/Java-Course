package agh.ics.oop.model;
import agh.ics.oop.OptionsParser;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void DoesArgumentsOnInputAreCorrectlyInterpreted() { //czy dane wejściowe podane jako tablica łańcuchów znaków są poprawnie interpretowane?
        //given
        String[] args = {"r","b","b","l","x","21","?","b","b"};
        Animal animal = new Animal(new Vector2d(1,2));
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

    @Test
    void DoesTheAnimalHaveTheRightOrientation() { //czy zwierzę ma właściwą orientację?
        //given
        String[] args = {"r", "r", "f"};
        Animal animal = new Animal(new Vector2d(3,2));
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