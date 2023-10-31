package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void giveDirectionsTest() {
        String[] tab1 = new String[3]; // array with not all correct arguments
        tab1[0] = "f";
        tab1[1] = "l";
        tab1[2] = "o";

        MoveDirection[] tab1Solution = new MoveDirection[2];
        tab1Solution[0] = MoveDirection.FORWARD;
        tab1Solution[1] = MoveDirection.LEFT;

        String[] tab2 = new String[3]; // array with all correct arguments
        tab2[0] = "f";
        tab2[1] = "r";
        tab2[2] = "l";

        MoveDirection[] tab2Solution = new MoveDirection[3];
        tab2Solution[0] = MoveDirection.FORWARD;
        tab2Solution[1] = MoveDirection.RIGHT;
        tab2Solution[2] = MoveDirection.LEFT;

        String[] tab3 = new String[3]; // array with all uncorrected arguments
        tab3[0] = "x";
        tab3[1] = "y";
        tab3[2] = "z";

        MoveDirection[] tab3Solution = new MoveDirection[0];

        assertArrayEquals(tab1Solution, OptionsParser.giveDirections(tab1));
        assertArrayEquals(tab2Solution, OptionsParser.giveDirections(tab2));
        assertArrayEquals(tab3Solution, OptionsParser.giveDirections(tab3));
    }
}