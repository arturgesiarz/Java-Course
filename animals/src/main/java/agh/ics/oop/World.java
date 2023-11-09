package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Vector2d;
import java.util.Objects;

public class World {
    public static void main(String[] args){
        
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

