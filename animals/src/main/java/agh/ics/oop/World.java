package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import java.util.Objects;

public class World {
    public static void main(String[] args){
        System.out.println("Start");
        run(OptionsParser.giveDirections(args));
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));
    }
    private static void run(MoveDirection[] moves) {
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

