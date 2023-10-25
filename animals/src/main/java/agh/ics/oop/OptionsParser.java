package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] giveDirections(String[] args) {

        MoveDirection[] moves = new MoveDirection[args.length];
        int pointer_to_act_move=0;

        for(String arg : args){
            switch (arg){
                case "f" -> {
                    moves[pointer_to_act_move] = MoveDirection.FORWARD;
                    pointer_to_act_move += 1;
                }
                case "b" -> {
                        moves[pointer_to_act_move]=MoveDirection.BACKWARD;
                        pointer_to_act_move+=1;
                }
                case "l" -> {
                        moves[pointer_to_act_move]=MoveDirection.LEFT;
                        pointer_to_act_move+=1;
                }
                case "r" -> {
                    moves[pointer_to_act_move] = MoveDirection.RIGHT;
                    pointer_to_act_move += 1;
                }
            }
        }
        return Arrays.copyOf(moves,pointer_to_act_move);
    }
}
