package agh.ics.oop.model;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    RectangularMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
    }
    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return upperRight.follows(position) && lowerLeft.precedes(position) && !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition()) && animal.getOrientation() == MapDirection.NORTH
                && !isOccupied(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        else return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction){
        if(isOccupied(animal.getPosition())){

            Vector2d oldPositionAnimal = animal.getPosition();
            animal.move(direction);

            if(canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){
                animals.remove(oldPositionAnimal);
                animals.put(animal.getPosition(),animal);
            }
        }
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }
}
