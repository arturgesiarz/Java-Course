package agh.ics.oop;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class World {
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.giveDirections(args);
            List<Vector2d> positions = List.of(new Vector2d(0,2), new Vector2d(0,0),new Vector2d(1,0), new Vector2d(0,0));

            GrassField mapGrassField = new GrassField(5);
            RectangularMap mapRectangular = new RectangularMap(5,5);

            Simulation simulationGrassField = new Simulation(positions, directions, mapGrassField );
            Simulation simulationRectangularMap = new Simulation(positions,directions,mapRectangular);


            ConsoleMapDisplay observerConsoleMapDisplay = new ConsoleMapDisplay();
            mapGrassField.addObserver(observerConsoleMapDisplay); //dodaje obserwatorów, dla konkretnych map
            mapRectangular.addObserver(observerConsoleMapDisplay);

            SimulationEngine simulationEngine = new SimulationEngine(List.of(simulationGrassField, simulationRectangularMap));
            simulationEngine.runAsync();

        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }

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

