package agh.ics.oop;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationEngineTest {
    @Test
    void runTest(){
        String[] input = new String[]{"f", "b", "l", "r", "b", "f", "r", "f", "f"};
        MoveDirection[] directions = OptionsParser.parse(input);
        IWorldMap map = new RectangularMap(8, 10);
        Vector2d[] positions = new Vector2d[]{new Vector2d(1, 1), new Vector2d(5, 5)};
        IEngine engine = new SimulationEngine(directions, map, positions);

        engine.run();

        assertEquals(map.objectAt(new Vector2d(2, 3)).toString(), "^");
        assertEquals(map.objectAt(new Vector2d(7, 4)).toString(), ">");
    }

}

