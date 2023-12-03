package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;

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
            animalsList.add(newAnimal);
            worldMap.place(newAnimal);
        }

        this.animalsList = animalsList;
        this.movesList = movesList;
        this.worldMap = worldMap;
        System.out.println(worldMap);
    }

    public void run(){
        int pointerToAnimal = 0;
        for(MoveDirection moveAnimal : movesList){

            Animal animalActual = animalsList.get(pointerToAnimal % animalsList.size());
            worldMap.move(animalActual,moveAnimal);
            System.out.println("Zwierzę " + ( pointerToAnimal % animalsList.size() ) + " : " + animalActual.getPosition());
            System.out.println(worldMap);
            pointerToAnimal += 1;
        }
    }
}