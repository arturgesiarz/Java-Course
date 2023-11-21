package agh.ics.oop;
import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class World {
    public static void main(String[] args){
        List<MoveDirection> directions = OptionsParser.giveDirections(args);
        List<Vector2d> positions = List.of(new Vector2d(2,2), new Vector2d(3,4));
        Simulation simulation = new Simulation(positions, directions, new RectangularMap(5,5));
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

