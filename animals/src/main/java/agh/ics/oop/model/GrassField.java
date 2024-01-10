package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrassField extends AbstractWorldMap  {
    private final Map<Vector2d, Grass> grassMap;
    private final int grassNumber;
    private final Vector2d upperRangeBladeOfGrass;

    public GrassField(int grassNumber){
        super((int)(Math.sqrt(grassNumber * 10)) + 1,(int)(Math.sqrt(grassNumber * 10)) + 1);
        this.grassNumber = grassNumber;
        this.upperRangeBladeOfGrass = new Vector2d((int)(Math.sqrt(grassNumber * 10)), (int)(Math.sqrt(grassNumber * 10)));
        this.grassMap = generateGrass();
    }
    public Map<Vector2d, Grass> getGrassMap() {
        return Collections.unmodifiableMap(grassMap);
    }
    private Map<Vector2d, Grass> generateGrass(){ //funkcja generuje cala trawe
        Map<Vector2d, Grass> grassMap = new HashMap<>();
        RandomPositionGenerator grassGenerator = new RandomPositionGenerator(grassNumber, upperRangeBladeOfGrass);
        for(Vector2d grassPosition : grassGenerator) {
            grassMap.put(grassPosition, new Grass(grassPosition));
        }
        return grassMap;
    }
    @Override
    public Optional<WorldElement> objectAt(Vector2d position){
        Optional<WorldElement> objectAnimal = super.objectAt(position);
        return objectAnimal.isPresent() ? objectAnimal : Optional.ofNullable(grassMap.get(position));
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }
    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> mapOfElements = super.getElements();

        return Stream.concat(
                mapOfElements
                        .entrySet()
                        .stream(),
                grassMap
                        .entrySet()
                        .stream()
                        .filter(grass -> !mapOfElements.containsKey(grass.getKey()))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }
    @Override
    public Boundary getCurrentBounds(){
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);
        Map<Vector2d, WorldElement> allObjects = getElements();

        for(Vector2d positionObject : allObjects.keySet()){
            lowerLeft = new Vector2d(Math.min(lowerLeft.getX(), positionObject.getX()),Math.min(lowerLeft.getY(), positionObject.getY()));
            upperRight = new Vector2d(Math.max(upperRight.getX(), positionObject.getX()),Math.max(upperRight.getY(), positionObject.getY()));
        }

        return new Boundary(lowerLeft,upperRight);
    }
}
