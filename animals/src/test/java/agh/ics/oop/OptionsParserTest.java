package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void giveDirectionsNotAllCorrectArguments() {
        String[] tab1 = {"f", "l", "o"};

        List<MoveDirection> tab1Solution = new ArrayList<>();
        tab1Solution.add(MoveDirection.FORWARD);
        tab1Solution.add(MoveDirection.LEFT);

        assertEquals(tab1Solution, OptionsParser.giveDirections(tab1));
    }

    @Test
    void giveDirectionsAllCorrectArguments() {
        String[] tab2 = {"f", "r", "l"};

        List<MoveDirection> tab2Solution = new ArrayList<>();
        tab2Solution.add(MoveDirection.FORWARD);
        tab2Solution.add(MoveDirection.RIGHT);
        tab2Solution.add(MoveDirection.LEFT);

        assertEquals(tab2Solution, OptionsParser.giveDirections(tab2));
    }

    @Test
    void giveDirectionsAnyCorrectArguments() {
        String[] tab3 = {"x","y","z"};

        List<MoveDirection> tab3Solution = new ArrayList<>();

        assertEquals(tab3Solution, OptionsParser.giveDirections(tab3));
    }


}