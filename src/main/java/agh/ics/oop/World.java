package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;


public class World {

    public static void main(String[] args){

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);

        Vector2d[] positions = {
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(0,0),
                new Vector2d(10,10),
                new Vector2d(10,4),
                new Vector2d(3,4),
                new Vector2d(-3,-4),
        };

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }


}

