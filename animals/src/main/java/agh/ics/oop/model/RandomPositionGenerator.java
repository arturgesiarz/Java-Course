package agh.ics.oop.model;
import java.util.*;
import java.util.function.Consumer;

public class RandomPositionGenerator implements Iterable<Vector2d>{ //zrobic realizacje iterable!!
    private final int grassNumber;
    private final Vector2d upperRangeBladeOfGrass;
    private final List<Vector2d> randomPoints;
    public RandomPositionGenerator(int grassNumber, Vector2d upperRangeBladeOfGrass){
        this.grassNumber = grassNumber;
        this.upperRangeBladeOfGrass = upperRangeBladeOfGrass;
        this.randomPoints = generateGrassNumberRandomPosition( createPossiblePositions() );
    }
   public List<Vector2d> createPossiblePositions(){ //O(n), bo O(sqrt(n)*sqrt(n))
       List<Vector2d> possiblePositions = new ArrayList<>();
       for(int i = 0; i <= upperRangeBladeOfGrass.getX(); i++){
            for(int j = 0; j <= upperRangeBladeOfGrass.getY(); j++){
                possiblePositions.add(new Vector2d(i,j));
            }
       }
       return possiblePositions;
   }
   public List<Vector2d> generateGrassNumberRandomPosition(List<Vector2d> possiblePositions){
        List<Vector2d> generatedPoints = new ArrayList<>();
        int lastIndex = possiblePositions.size() - 1;
        int randomSelect;

        for(int i = 0; i < grassNumber; i++){ //wykonujemy losowanie n - razy
            randomSelect = (int)Math.floor(Math.random() * (lastIndex + 1) );
            generatedPoints.add(possiblePositions.get(randomSelect));
            possiblePositions.remove(randomSelect);
            lastIndex--;
        }
        return generatedPoints;
   }
    @Override
    public Iterator<Vector2d> iterator() {
        return randomPoints.iterator();
    }
    @Override
    public void forEach(Consumer<? super Vector2d> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Vector2d> spliterator() {
        return Iterable.super.spliterator();
    }
}
