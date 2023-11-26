package agh.ics.oop.model;
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
    private void updateUpperRightAndLeftVectors(Vector2d positionOfAnimal){
        super.lowerLeft = super.lowerLeft.lowerLeft(positionOfAnimal);
        super.upperRight = super.upperRight.upperRight(positionOfAnimal);
    }
    @Override
    public WorldElement objectAt(Vector2d position){
        WorldElement objectAnimal = super.objectAt(position);
        return objectAnimal != null ? objectAnimal : grassMap.get(position);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.objectAt(position) == null; //sprawdzam czy, na danym polu nie ma zwierzecia ponieaz na trawe moge wejsc a na zwierze juz nie
    }
    @Override
    public boolean place(Animal animal) {
        boolean doesAddedPositive = super.place(animal);
        if(doesAddedPositive){
            updateUpperRightAndLeftVectors(animal.getPosition());
        }
        return doesAddedPositive;
    }
    @Override
    public void move(Animal animal, MoveDirection direction) {
        super.move(animal,direction);
        updateUpperRightAndLeftVectors(animal.getPosition());
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
}
