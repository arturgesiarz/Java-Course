package agh.ics.oop.model;
import java.util.Objects;

public class Animal implements WorldElement{
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
    @Override
    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position){
        return (position.equals(this.position));
    }

    public void move(MoveDirection direction, MoveValidator validator){

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

        if(validator.canMoveTo(newPosition)) {
            position = newPosition;
        }

    }
    @Override
    public String toString() {
        return switch (this.orientation){
            case NORTH  ->  "N";
            case SOUTH  ->  "S";
            case EAST   ->  "E";
            case WEST   ->  "W";
        };
    }

    @Override
    public String getFileName() {
        return switch (this.orientation){
            case NORTH  ->  "up.png";
            case SOUTH  ->  "down.png";
            case EAST   ->  "right.png";
            case WEST   ->  "left.png";
        };
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
