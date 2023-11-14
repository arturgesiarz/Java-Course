package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

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
                ", pozycja zwierzecia to " + this.position + '.';
    }

    public boolean isAt(Vector2d position){
        return (position.equals(this.position));
    }

}
