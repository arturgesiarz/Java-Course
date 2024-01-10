package agh.ics.oop.model;

public class Grass implements WorldElement{
    private static final String GRASS_SYMBOL = "*";
    private final Vector2d position;
    public Grass(Vector2d position){
        this.position = position;
    }
    @Override
    public Vector2d getPosition() {
        return position;
    }
    @Override
    public String toString(){
        return GRASS_SYMBOL;
    }

    @Override
    public String getFileName() {
        return "grass.png";
    }

    @Override
    public String getDescription() {
        return "Grass";
    }
}
