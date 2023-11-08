package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {
    @Test
    void giveDirectionsTest() {
        String[] tab1 = {"f","l","o"}; // array with not all correct arguments

        MoveDirection[] tab1Solution = new MoveDirection[2];
        tab1Solution[0] = MoveDirection.FORWARD;
        tab1Solution[1] = MoveDirection.LEFT;

        String[] tab2 = {"f","r","l"}; // array with all correct arguments

        MoveDirection[] tab2Solution = new MoveDirection[3];
        tab2Solution[0] = MoveDirection.FORWARD;
        tab2Solution[1] = MoveDirection.RIGHT;
        tab2Solution[2] = MoveDirection.LEFT;

        String[] tab3 = {"x","y","z"}; // array with all uncorrected arguments
        
        MoveDirection[] tab3Solution = new MoveDirection[0];

        assertArrayEquals(tab1Solution, OptionsParser.giveDirections(tab1));
        assertArrayEquals(tab2Solution, OptionsParser.giveDirections(tab2));
        assertArrayEquals(tab3Solution, OptionsParser.giveDirections(tab3));
    }
}