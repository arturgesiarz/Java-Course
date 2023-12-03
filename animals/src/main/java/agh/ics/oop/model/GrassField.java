package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public WorldElement objectAt(Vector2d position){
        WorldElement objectAnimal = super.objectAt(position);
        return objectAnimal != null ? objectAnimal : grassMap.get(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }
    @Override
    public Map<Vector2d, WorldElement> getElements(){
        Map<Vector2d, WorldElement> mapOfElements = super.getElements();
        for(Vector2d positionGrass : grassMap.keySet()){
            if(!mapOfElements.containsKey(positionGrass)){
                mapOfElements.put(positionGrass,grassMap.get(positionGrass));
            }
        }
        return mapOfElements;
    }
    @Override
    public String toString() { //obliczam dynamicznie rozmiar tablicy - kiedy potrzebuje wyliczyc rozmiar tablicy
        Vector2d lowerLeft = new Vector2d(Integer.MAX_VALUE,Integer.MAX_VALUE);
        Vector2d upperRight = new Vector2d(Integer.MIN_VALUE, Integer.MIN_VALUE);

        for(Vector2d position : super.animals.keySet()){ //przegladam kolejno zwierzeta
            lowerLeft = new Vector2d(Math.min(lowerLeft.getX(), position.getX()),Math.min(lowerLeft.getY(), position.getY()));
            upperRight = new Vector2d(Math.max(upperRight.getX(), position.getX()),Math.max(upperRight.getY(), position.getY()));
        }
        for(Vector2d position : grassMap.keySet()){ //przegladam kolejno trawy
            lowerLeft = new Vector2d(Math.min(lowerLeft.getX(), position.getX()),Math.min(lowerLeft.getY(), position.getY()));
            upperRight = new Vector2d(Math.max(upperRight.getX(), position.getX()),Math.max(upperRight.getY(), position.getY()));
        }

        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(lowerLeft,upperRight);
    }
}
