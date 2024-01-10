package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public abstract class AbstractWorldMap implements WorldMap {
    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>(); //lista obserwatorow realizujacych interfejs MapChangeListener
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    private final UUID worldMapID;
    public AbstractWorldMap(int width, int height){
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(width - 1,height - 1);
        worldMapID = UUID.randomUUID(); // przy tworzeniu nowego objektu, tworze dla niego niepotwarzalny ID
    }
    public void addObserver(MapChangeListener observer) { //dodaje obserwatora
        observers.add(observer);
    }
    public void removeObserver(MapChangeListener observer) { //usuwam obserwatora
        observers.remove(observer);
    }
    void mapChanged(String message){
        observers.forEach((observer) -> observer.mapChanged(this,message));
    }
    public Map<Vector2d, Animal> getAnimals() {
        return Collections.unmodifiableMap(animals);
    }
    public abstract boolean canMoveTo(Vector2d position);
    public abstract Boundary getCurrentBounds();
    @Override
    public UUID getId() { //wyswietlam moje ID
        return worldMapID;
    }
    public Optional<WorldElement> objectAt(Vector2d position){
        return Optional.ofNullable(animals.get(position));
    }
    public void place(Animal animal) throws PositionAlreadyOccupiedException{
        if(canMoveTo(animal.getPosition())){
            animals.put(animal.getPosition(),animal);
            mapChanged("Object has been placed at " + animal.getPosition());
        }
        else{
            throw new PositionAlreadyOccupiedException(animal.getPosition());
        }
    }
    private boolean checkIfItWasTurn(MapDirection oldOrientation, MapDirection newOrientation){
        return oldOrientation != newOrientation; //porownujemy ze soba normalnie poniewaz sa to enumy
    }
    public void move(Animal animal, MoveDirection direction) { //nie dodaje tutaj synchronized, bo kazda symulacja dziala niezalezenie od innych symulacji i nie zmienniaja w tym samym czasie tych samych danych (jakby byla taka mozliwosc koniecznie by bylo uzycie synchronized!)
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
    @Override
    public Map<Vector2d, WorldElement> getElements(){
        return animals
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List<Animal> getOrderedAnimals(){
        return animals
                .values()  // pobieranie samych wartosci
                .stream()
                .sorted(Comparator
                        .comparing((Animal a) -> a.getPosition().getX())
                        .thenComparing((Animal a) -> a.getPosition().getY()))
                .collect(Collectors.toList());
    }
    @Override
    public String toString() {
        MapVisualizer visualizer = new MapVisualizer(this);
        Boundary boundary = getCurrentBounds();
        return visualizer.draw(boundary.lowerLeftBoundary(),boundary.upperRightBoundary());
    }
}