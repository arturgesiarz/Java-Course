package agh.ics.oop;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class World {
    public static void main(String[] args){
        try{
            List<MoveDirection> directions = OptionsParser.giveDirections(args);
            List<Vector2d> positions = List.of(new Vector2d(0,2), new Vector2d(0,0),new Vector2d(1,0), new Vector2d(0,0));
            GrassField map = new GrassField(5);
            ConsoleMapDisplay observerConsolMapDisplay = new ConsoleMapDisplay();
            map.addObserver(observerConsolMapDisplay);
            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } catch (IllegalArgumentException e){
            e.getStackTrace();
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

