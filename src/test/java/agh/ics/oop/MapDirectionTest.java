package agh.ics.oop;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapDirectionTest {

    @Test
    void testIsEastNorthNext(){
        MapDirection n = MapDirection.NORTH;
        MapDirection result = n.next();
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void testIsSouthEastNext(){
        MapDirection e = MapDirection.EAST;
        MapDirection result = e.next();
        assertEquals(MapDirection.SOUTH, result);
    }

    @Test
    void testIsWestSouthNext(){
        MapDirection s = MapDirection.SOUTH;
        MapDirection result = s.next();
        assertEquals(MapDirection.WEST, result);
    }

    @Test
    void testIsNorthWestNext(){
        MapDirection w = MapDirection.WEST;
        MapDirection result = w.next();
        assertEquals(MapDirection.NORTH, result);
    }

    @Test
    void testIsWestNorthPrev(){
        MapDirection n = MapDirection.NORTH;
        MapDirection result = n.previous();
        assertEquals(MapDirection.WEST, result);
    }

    @Test
    void testIsSouthWestPrev(){
        MapDirection w = MapDirection.WEST;
        MapDirection result = w.previous();
        assertEquals(MapDirection.SOUTH, result);
    }

    @Test
    void testIsEastSouthPrev(){
        MapDirection s = MapDirection.SOUTH;
        MapDirection result = s.previous();
        assertEquals(MapDirection.EAST, result);
    }

    @Test
    void testIsNorthEastPrev(){
        MapDirection e = MapDirection.EAST;
        MapDirection result = e.previous();
        assertEquals(MapDirection.NORTH, result);
    }
}