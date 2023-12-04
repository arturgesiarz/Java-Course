package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>(); //lista obserwatorow realizujacych interfejs MapChangeListener
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    public AbstractWorldMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
    }
    public void addObserver(MapChangeListener observer) { //dodaje obserwatora
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer) { //usuwam obserwatora
        observers.remove(observer);
    }
    void mapChanged(String message){
        for(MapChangeListener observer : observers){ //wywoluje na wszystkich obserwatorach metode
            observer.mapChanged(this,message);
        }
    }
    public Map<Vector2d, Animal> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }
    public abstract boolean canMoveTo(Vector2d position);
    public abstract Boundary getCurrentBounds();
    public WorldElement objectAt(Vector2d position){
        return animals.get(position);
    }
    public boolean place(Animal animal) throws PositionAlreadyOccupiedException{
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            mapChanged("Object has been placed at " + animal.getPosition());
            return true;
        }
        throw new PositionAlreadyOccupiedException(animal.getPosition());
    }
    boolean checkIfItWasTurn(MapDirection oldOrientation, MapDirection newOrientation){
        return !oldOrientation.equals(newOrientation);
    }
    public void move(Animal animal, MoveDirection direction) {
        Vector2d oldPosition = new Vector2d(animal.getPosition().getX(),animal.getPosition().getY());
        MapDirection oldOrientation = animal.getOrientation();

        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(),animal);

        if(checkIfItWasTurn(oldOrientation,animal.getOrientation())){ //rotacja
            mapChanged("Object in position " + animal.getPosition() +
                    " rotated from " + oldOrientation + " to " + animal.getOrientation());
        }
        else if(oldPosition.equals(animal.getPosition())){ //niemozliwosc poruszenia sie na dane pole
            mapChanged("Object in position " + animal.getPosition() + " stuck!");
        }
        else{ //przemieszczenie sie obiektu
            mapChanged("Object in position " + oldPosition +
                    " moved to " + animal.getPosition());
        }
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
        Boundary boundary = getCurrentBounds();
        return visualizer.draw(boundary.lowerLeftBoundary(),boundary.upperRightBoundary());
    }
}