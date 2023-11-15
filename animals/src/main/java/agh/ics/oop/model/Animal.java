package agh.ics.oop.model;
import java.util.Objects;

public class Animal {
    private final static Vector2d BORDER_RIGHT = new Vector2d(4,4);
    private final static Vector2d BORDER_LEFT = new Vector2d(0,0);
    private MapDirection orientation;
    private Vector2d position;
    public Animal() {
        this(new Vector2d(2,2));
    }
    public Animal(Vector2d position){
        this.orientation = MapDirection.NORTH;
        this.position = position;
    }
    public MapDirection getOrientation() {
        return orientation;
    }
    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position){
        return (position.equals(this.position));
    }

    public void move(MoveDirection direction){


        orientation = switch (direction) {
            case LEFT -> orientation.previous();
            case RIGHT -> orientation.next();
            case FORWARD,BACKWARD -> orientation;
        };

        Vector2d newPosition = switch (direction) {
            case FORWARD -> position.add(orientation.toUnitVector());
            case BACKWARD -> position.subtract(orientation.toUnitVector());
            case LEFT,RIGHT -> position;
        };

        if(newPosition.precedes(BORDER_RIGHT) && newPosition.follows(BORDER_LEFT)) {
            position = newPosition;
        }

    }
    @Override
    public String toString() {
        return  "Orientacja zwierzecia to " + this.orientation +
                ", a jego pozycja to " + this.position + '.';
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
