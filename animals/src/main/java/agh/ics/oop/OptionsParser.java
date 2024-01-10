package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OptionsParser {
    public static List<MoveDirection> giveDirections(String[] args){

        return Stream.of(args)
                .map(arg -> {
                            switch (arg) {
                                case "f" -> {
                                    return MoveDirection.FORWARD;
                                }
                                case "b" -> {
                                    return MoveDirection.BACKWARD;
                                }
                                case "l" -> {
                                    return MoveDirection.LEFT;
                                }
                                case "r" -> {
                                    return MoveDirection.RIGHT;
                                }
                                default -> throw new IllegalArgumentException(arg + " is not legal move specification");
                            }
                        }
                )
                .toList();
    }
}
