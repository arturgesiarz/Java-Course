package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;

public class RectangularMap implements WorldMap {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected final Map<Vector2d, Animal> animals = new HashMap<>();

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
        return Collections.unmodifiableMap(animals);
    }
    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return upperRight.follows(position) && lowerLeft.precedes(position) && !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition()) && !isOccupied(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction,this);
        animals.put(animal.getPosition(),animal);
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
