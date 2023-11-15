package agh.ics.oop;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animalsList;
    private final List<MoveDirection> movesList;

    public Simulation(List<Vector2d> positionsList, List<MoveDirection> movesList){
        List<Animal> animalsList = new ArrayList<>();

        for(Vector2d position : positionsList){
            animalsList.add(new Animal(position));
        }

        this.animalsList = animalsList;
        this.movesList = movesList;
    }

    public void run(){
        int pointerToAnimal = 0;

        for(MoveDirection moveAnimal : movesList){

            Animal animalActual = animalsList.get(pointerToAnimal % animalsList.size());
            animalActual.move(moveAnimal);
            System.out.println("Zwierzę " + ( pointerToAnimal % animalsList.size() ) + " : " + animalActual.getPosition());

            pointerToAnimal += 1;
        }
    }


}