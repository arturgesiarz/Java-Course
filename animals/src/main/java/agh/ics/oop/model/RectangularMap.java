package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RectangularMap implements WorldMap {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    public Map<Vector2d, Animal> getAnimals() {
        return animals;
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
    public void move(Animal animal, MoveDirection direction) {
        if (isOccupied(animal.getPosition())) {
            if (direction == MoveDirection.LEFT || direction == MoveDirection.RIGHT) {
                Animal actAnimal = animals.get(animal.getPosition());
                actAnimal.move(direction, this); //enough with changed position not id, bc we go to left or right
                animal.move(direction,this);
            }

            else {
                final Vector2d oldPosition = animal.getPosition();
                animal.move(direction, this);


                if (canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())) {
                    Animal newAnimal = new Animal(animal.getPosition());

                    while(newAnimal.getOrientation() != animal.getOrientation()){ //O(1)
                        newAnimal.move(MoveDirection.RIGHT,this);
                    }

                    animals.remove(oldPosition); //we must delete this bc id have to be changed
                    animals.put(newAnimal.getPosition(), newAnimal);
                }
            }
        }
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(RectangularMap.this);
        return visualizer.draw(this.lowerLeft,this.upperRight);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectangularMap that = (RectangularMap) o;
        return Objects.equals(lowerLeft, that.lowerLeft) && Objects.equals(upperRight, that.upperRight) && Objects.equals(animals, that.animals);
    }
    @Override
    public int hashCode() {
        return Objects.hash(lowerLeft, upperRight, animals);
    }
}
