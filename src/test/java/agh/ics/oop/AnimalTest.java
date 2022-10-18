package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

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

        ArrayList<MoveDirection> parsed = OptionsParser.parse(testInput);
        assertEquals(parsed.size(), expected.length);

        for (int i = 0; i < parsed.size(); i++) {
            assertEquals(expected[i], parsed.get(i));
        }
    }

    @Test
    void testOrientation(){
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

    ArrayList<MoveDirection> parsed = OptionsParser.parse(testInput);
    Animal animal = new Animal();

    for(int i = 0; i < parsed.size(); i++){
        animal.move(parsed.get(i));
        assertEquals(expected[i], animal.orientation);
    }
    }

    @Test
    public void testPositions(){
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

        ArrayList<MoveDirection> parsed = OptionsParser.parse(testInput);
        Animal animal = new Animal();

        for(int i = 0; i < parsed.size(); i++){
            animal.move(parsed.get(i));
            assertEquals(expected[i], animal.position.toString());
        }
    }
}





