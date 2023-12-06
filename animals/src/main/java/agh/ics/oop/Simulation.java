package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
    private final List<Animal> animalsList;
    private final List<MoveDirection> movesList;
    private final WorldMap worldMap;

    public List<Animal> getAnimalsList() {
        return Collections.unmodifiableList(animalsList);
    }

    public Simulation(List<Vector2d> positionsList, List<MoveDirection> movesList, WorldMap worldMap){
        List<Animal> animalsList = new ArrayList<>();

        for(Vector2d position : positionsList){
            Animal newAnimal = new Animal(position);
            try{
                worldMap.place(newAnimal);
                animalsList.add(newAnimal);
            } catch (PositionAlreadyOccupiedException e){
                e.printStackTrace();
            }
        }
        this.animalsList = animalsList;
        this.movesList = movesList;
        this.worldMap = worldMap;
    }

    public void run(){
        int pointerToAnimal = 0;
        for(MoveDirection moveAnimal : movesList){
            Animal animalActual = animalsList.get(pointerToAnimal % animalsList.size());
            worldMap.move(animalActual,moveAnimal);
            pointerToAnimal += 1;
        }
    }
}