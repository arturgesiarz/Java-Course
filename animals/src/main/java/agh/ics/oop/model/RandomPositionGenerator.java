package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class RandomPositionGenerator { //zrobic realizacje iterable!!
    private final int grassNumber;
    private final Vector2d upperRangeBladeOfGrass;
    public RandomPositionGenerator(int grassNumber, Vector2d upperRangeBladeOfGrass){
        this.grassNumber = grassNumber;
        this.upperRangeBladeOfGrass = upperRangeBladeOfGrass;
    }
   public List<Vector2d> createPossiblePositions(){
       List<Vector2d> possiblePositions = new ArrayList<>();
       for(int i = 0; i < upperRangeBladeOfGrass.getX(); i++){
            for(int j = 0; j < upperRangeBladeOfGrass.getY(); j++){
                possiblePositions.add(new Vector2d(i,j));
            }
       }
       return possiblePositions;
   }
   public void generateGrassNumberRandomPosition(){

   }

}
