package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    private static final IWorldMap map = new RectangularMap(5, 5);

    @Test
    void testParser() {
        String[] testInput = "f b f b l null backward s f r back int left ...".split(" ");
        MoveDirection[] expected = {
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT,
                MoveDirection.BACKWARD,
                MoveDirection.FORWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT,
        };

        MoveDirection[] parsed = OptionsParser.parse(testInput);
        assertEquals(parsed.length, expected.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], parsed[i]);
        }
    }

    @Test
    void testOrientation() {
        String[] testInput = "f b f b l null backward s f r back int left ...".split(" ");
        MapDirection[] expected = {
                MapDirection.NORTH,
                MapDirection.NORTH,
                MapDirection.NORTH,
                MapDirection.NORTH,
                MapDirection.WEST,
                MapDirection.WEST,
                MapDirection.WEST,
                MapDirection.NORTH,
                MapDirection.WEST};


        MoveDirection[] parsed = OptionsParser.parse(testInput);
        Animal animal = new Animal(map);

        for (int i = 0; i < expected.length; i++) {
            animal.move(parsed[i]);
            assertEquals(animal.getOrientation(), expected[i]);
        }
    }

    @Test
    public void testPositions() {
        String[] testInput = "f f f l f f r f f f f l f f f".split(" ");
        String[] expected = {
                "(2, 3)",
                "(2, 4)",
                "(2, 4)",
                "(2, 4)",
                "(1, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
                "(0, 4)",
        };

        MoveDirection[] parsed = OptionsParser.parse(testInput);
        Animal animal = new Animal(map, new Vector2d(2, 2));

        for (int i = 0; i < expected.length; i++) {
            animal.move(parsed[i]);
            assertEquals(animal.getPosition().toString(), expected[i]);
        }
    }

    @Test
    void testOutOfBounds() {
        IWorldMap map = new RectangularMap(4, 4);
        Animal animal = new Animal(map);
        String[] input = new String[]{"f", "f", "forward", "f", "f", "f"};

        MoveDirection[] moves = OptionsParser.parse(input);
        for (MoveDirection move : moves) {
            animal.move(move);
        }
        String result = animal.toString();

        assertEquals("^", result);
        assertEquals(new Vector2d(2, 3), animal.getPosition());
    }
}







