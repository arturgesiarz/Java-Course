package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class OptionsParser {
    public static List<MoveDirection> giveDirections(String[] args) {
        List<MoveDirection> moves = new ArrayList<>();

        for(String arg : args){
            switch (arg){
                case "f" -> moves.add(MoveDirection.FORWARD);
                case "b" -> moves.add(MoveDirection.BACKWARD);
                case "l" -> moves.add(MoveDirection.LEFT);
                case "r" -> moves.add(MoveDirection.RIGHT);
            }
        }
        return moves;
    }
}
