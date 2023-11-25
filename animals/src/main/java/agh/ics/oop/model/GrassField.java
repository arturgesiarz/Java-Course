package agh.ics.oop.model;
import java.util.HashSet;
import java.util.Set;

public class GrassField extends RectangularMap implements WorldMap  {
    private final int grassNumber;
    private final Vector2d upperRangeBladeOfGrass;
    private final Set<Grass> grassSet = new HashSet<>();
    public GrassField(int grassNumber){
        super((int)(Math.sqrt(grassNumber * 10)),(int)(Math.sqrt(grassNumber * 10)));
        this.grassNumber = grassNumber;
        this.upperRangeBladeOfGrass = new Vector2d((int)(Math.sqrt(grassNumber * 10)), (int)(Math.sqrt(grassNumber * 10)));
        generateGrass();
    }
    private void generateGrass(){ //funkcja generuje cala trawe
        for(int i = 0; i < grassNumber; i++){
            generateBladeOfGrass();
        }
    }
    private void generateBladeOfGrass(){ //funkcja generuje punkt w postaci wektora
        int randomX = (int)Math.floor(Math.random() * (upperRangeBladeOfGrass.getX() + 1));
        int randomY = (int)Math.floor(Math.random() * (upperRangeBladeOfGrass.getY() + 1));
        Grass grassNewPosition = new Grass(new Vector2d(randomX,randomY));

        while(isThereGrass(grassNewPosition)){ //losuje nowe miejsca na trawe dopki nie zostanie wylosowane prawidlowe miejsce
            randomX = (int)Math.floor(Math.random() * (upperRangeBladeOfGrass.getX() + 1));
            randomY = (int)Math.floor(Math.random() * (upperRangeBladeOfGrass.getY() + 1));
            grassNewPosition = new Grass(new Vector2d(randomX,randomY));
        }
        grassSet.add(grassNewPosition);
    }
    public boolean isThereGrass(Grass grass){ //funkcja sprawdza czy w danym miejscu jest postawiona trawa
        return grassSet.contains(grass);
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }
    @Override
    public boolean place(Animal animal) {
        if(canMoveTo(animal.getPosition())){
            super.animals.put(animal.getPosition(),animal);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "GrassField{" +
                "grassNumber=" + grassNumber +
                ", upperRangeBladeOfGrass=" + upperRangeBladeOfGrass +
                ", grassSet=" + grassSet +
                '}';
    }
}
