package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    public AbstractWorldMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
    }
    public Map<Vector2d, Animal> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }
    public abstract boolean canMoveTo(Vector2d position);
    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException{
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            return true;
        }
        throw new PositionAlreadyOccupiedException(animal.getPosition());
    }
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(),animal);
    }
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> mapOfElements = new HashMap<>();
        for(Vector2d positionAnimal : animals.keySet()){
            mapOfElements.put(positionAnimal,animals.get(positionAnimal));
        }
        return mapOfElements;
    }
    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(this.lowerLeft,this.upperRight);
    }
}