package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {

    @Test
    void testEquals(){
        assertTrue(new Vector2d(2, 3).equals(new Vector2d(2, 3)));
        assertFalse(new Vector2d(2, 3).equals(new Vector2d(4, 5)));
        assertFalse(new Vector2d(2, 3).equals("(2, 3)"));
    }

    @Test
    void testToString(){
        assertEquals(new Vector2d(6, 7).toString(), "(6, 7)");
    }

    @Test
    void testFollows(){
        assertTrue(new Vector2d(4, 2).follows(new Vector2d(2, 0)));
        assertTrue(new Vector2d(1, 1).follows(new Vector2d(1, 1)));
        assertFalse(new Vector2d(2, 3).follows(new Vector2d(4, 3)));
    }

    @Test
    void testPrecedes(){
        assertTrue(new Vector2d(3, 5).precedes(new Vector2d(4, 5)));
        assertTrue(new Vector2d(4, 3).precedes(new Vector2d(4, 3)));
        assertFalse(new Vector2d(2, 2).precedes(new Vector2d(1, 2)));
    }

    @Test
    void testUpperRight(){
        assertEquals(new Vector2d(3, 5).upperRight(new Vector2d(4, 3)), new Vector2d(4, 5));
    }

    @Test
    void testLowerLeft(){
        assertEquals(new Vector2d(4, 2).lowerLeft(new Vector2d(3, 6)), new Vector2d(3, 2));
    }

    @Test
    void testAdd(){
        assertEquals(new Vector2d(1, 1).add(new Vector2d(1, 2)), new Vector2d(2, 3));
    }

    @Test
    void testSubstract(){
        assertEquals(new Vector2d(2, 3).subtract(new Vector2d(1, 2)), new Vector2d(1, 1));
    }

    @Test
    void testOpposite(){
        assertEquals(new Vector2d(-1, 2).opposite(), new Vector2d(1, -2));
    }

}