package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    public Animal(MapDirection orientation, Vector2d position) {
        this.orientation = orientation;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "orientation=" + this.orientation +
                ", position=" + this.position +
                '}';
    }
    boolean isAt(Vector2d position){
        return (position.equals(this.position));
    }

}
