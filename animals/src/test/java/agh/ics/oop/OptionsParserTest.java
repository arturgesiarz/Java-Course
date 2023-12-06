package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void giveDirectionsNotAllCorrectArguments() {
        //given
        String[] tab1 = {"f", "l", "o"};

        //when, then
        try {
            OptionsParser.giveDirections(tab1);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
            assertEquals("o is not legal move specification", e.getMessage());
        }
    }

    @Test
    void giveDirectionsAllCorrectArguments() {
        //given
        String[] tab2 = {"f", "r", "l"};

        //then
        List<MoveDirection> tab2Solution = new ArrayList<>();
        tab2Solution.add(MoveDirection.FORWARD);
        tab2Solution.add(MoveDirection.RIGHT);
        tab2Solution.add(MoveDirection.LEFT);

        //then
        try{
            List<MoveDirection> tab2Act = OptionsParser.giveDirections(tab2);
            assertEquals(tab2Solution, tab2Act);
        } catch (IllegalArgumentException e){
            e.getStackTrace();
        }

    }

    @Test
    void giveDirectionsAnyCorrectArguments() {
        String[] tab3 = {"x","y","z"};

        //when, then
        try {
            OptionsParser.giveDirections(tab3);
        } catch (IllegalArgumentException e) {
            e.getStackTrace();
            assertEquals("x is not legal move specification", e.getMessage());
        }
    }


}