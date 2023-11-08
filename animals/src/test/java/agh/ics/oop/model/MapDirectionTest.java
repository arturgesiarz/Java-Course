package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapDirectionTest {

    @Test
    void nextTest() {
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        MapDirection east = MapDirection.EAST;

        assertEquals(MapDirection.EAST, north.next());
        assertEquals(MapDirection.WEST, south.next());
        assertEquals(MapDirection.NORTH, west.next());
        assertEquals(MapDirection.SOUTH, east.next());
    }
    @Test
    void previousTest() {
        MapDirection north = MapDirection.NORTH;
        MapDirection south = MapDirection.SOUTH;
        MapDirection west = MapDirection.WEST;
        MapDirection east = MapDirection.EAST;

        assertEquals(MapDirection.WEST, north.previous());
        assertEquals(MapDirection.EAST, south.previous());
        assertEquals(MapDirection.SOUTH, west.previous());
        assertEquals(MapDirection.NORTH, east.previous());
    }

}