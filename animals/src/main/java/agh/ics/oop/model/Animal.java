package agh.ics.oop.model;
import java.util.Objects;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return position;
    }
    public Animal() {
        this(new Vector2d(2,2));
    }
    public Animal(Vector2d position){
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }
    @Override
    public String toString() {
        return  "Orientacja zwierzecia to " + this.orientation +
                ", a jego pozycja to " + this.position + '.';
    }
    public boolean isAt(Vector2d position){
        return (position.equals(this.position));
    }

    public void move(MoveDirection direction){
        switch (direction){
            case LEFT -> this.orientation = orientation.previous();
            case RIGHT -> this.orientation = orientation.next();
            case FORWARD -> {
                Vector2d newPosition = position.add(this.orientation.toUnitVector());

                if(newPosition.precedes(new Vector2d(4,4)) && newPosition.follows(new Vector2d(0,0))){
                    this.position = newPosition;
                }
            }
            case BACKWARD -> {
                Vector2d newPosition = position.subtract(this.orientation.toUnitVector());

                if(newPosition.precedes(new Vector2d(4,4)) && newPosition.follows(new Vector2d(0,0))){
                    this.position = newPosition;
                }
            }

        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return orientation == animal.orientation && Objects.equals(position, animal.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }
}
