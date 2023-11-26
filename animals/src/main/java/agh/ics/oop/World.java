package agh.ics.oop;
import agh.ics.oop.model.*;
import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public class World {
    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(1,5), new Vector2d(2,5),new Vector2d(2,10));
        GrassField map = new GrassField(10);
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
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

