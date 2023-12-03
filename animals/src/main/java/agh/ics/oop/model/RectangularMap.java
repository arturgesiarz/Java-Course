package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;

public class RectangularMap extends AbstractWorldMap {

    public RectangularMap(int width, int height) {
        super(width, height);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return upperRight.follows(position) && lowerLeft.precedes(position) && !isOccupied(position);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectangularMap that = (RectangularMap) o;
        return Objects.equals(lowerLeft, that.lowerLeft) && Objects.equals(upperRight, that.upperRight) && Objects.equals(animals, that.animals);
    }
    @Override
    public int hashCode() {
        return Objects.hash(lowerLeft, upperRight, animals);
    }
}
