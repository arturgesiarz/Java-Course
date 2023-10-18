package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args) {
        int all_correct_moves=0;
        int pointer_to_act_move=0;
        for(String arg : args) { //licze ile
            switch (arg) {
                case "f" -> all_correct_moves += 1;
                case "b" -> all_correct_moves += 1;
                case "l" -> all_correct_moves += 1;
                case "r" -> all_correct_moves += 1;
            }
        }

        MoveDirection[] moves = new MoveDirection[all_correct_moves];

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
        return moves;
    }
}
