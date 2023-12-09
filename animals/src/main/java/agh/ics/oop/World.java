package agh.ics.oop;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class World {
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.giveDirections(args);
            List<Vector2d> positions = List.of(new Vector2d(0,2), new Vector2d(0,0),new Vector2d(1,0), new Vector2d(0,0));

            List<Simulation> simulationList = new ArrayList<>();

            ConsoleMapDisplay observerConsoleMapDisplay = new ConsoleMapDisplay();

            for(int i = 0; i < 10; i++){
                GrassField newMapGrassField = new GrassField(5);
                simulationList.add(new Simulation(positions,directions,newMapGrassField));
                newMapGrassField.addObserver(observerConsoleMapDisplay);
            }
            SimulationEngine simulationEngine = new SimulationEngine(simulationList);
            simulationEngine.runAsync();

        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        System.out.println("System zakonczyl dzialanie!");

    }
    private static void run(List<MoveDirection> moves) {
        for (MoveDirection move : moves) {
            String check_move = switch (move) {
                case LEFT -> "Zwierzak idzie w lewo";
                case RIGHT -> "Zwierzak idzie w prawo";
                case FORWARD -> "Zwierzak idzie do przodu";
                case BACKWARD -> "Zwierzak idzie do tylu";
            };
            System.out.println(check_move);
        }
    }
}

