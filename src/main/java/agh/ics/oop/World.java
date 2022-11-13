package agh.ics.oop;


public class World {

//    f f f r f f f f f f f f f f f f f f f f f f f f f f f f f f f f

    public static void main(String[] args){

        MoveDirection[] directions = OptionsParser.parse(args);
        IWorldMap map = new GrassField(10);

        Vector2d[] positions = {
                new Vector2d(2,2),
                new Vector2d(3,4),
                new Vector2d(8,8),
                new Vector2d(9,9),
        };

        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.print(map);

    }


}

