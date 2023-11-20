package agh.ics.oop.model;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    RectangularMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return upperRight.follows(position) && lowerLeft.precedes(position) && !animals.containsKey(position);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition()) && animal.getOrientation() == MapDirection.NORTH
                && !animals.containsKey(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        else return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        if(animals.containsKey(animal.getPosition())){

            Vector2d oldPositionAnimal = animal.getPosition();
            animal.move(direction);

            if(canMoveTo(animal.getPosition()) && !animals.containsKey(animal.getPosition())){
                animals.remove(oldPositionAnimal);
                animals.put(animal.getPosition(),animal);
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {}

    @Override
    public Animal objectAt(Vector2d position) {}
}
