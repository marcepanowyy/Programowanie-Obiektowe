package agh.ics.oop;

import java.util.Random;

public enum MapDirection {

    N, NE, E, SE, S, SW, W, NW;

//    NORTH,
//    EAST,
//    SOUTH,
//    WEST;


    public String toString(){
        return switch (this) {
            case N -> "North";
            case NE -> "North East";
            case E -> "East";
            case SE -> "South East";
            case S -> "South";
            case SW -> "South West";
            case W -> "West";
            case NW -> "North West";
        };
    }

    public MapDirection next(){
        return switch(this){
            case N -> MapDirection.NE;
            case NE -> MapDirection.E;
            case E -> MapDirection.SE;
            case SE -> MapDirection.S;
            case S -> MapDirection.SW;
            case SW -> MapDirection.W;
            case W -> MapDirection.NW;
            case NW -> MapDirection.N;
        };
    }

    public MapDirection previous(){
        return switch(this) {
            case N -> MapDirection.NW;
            case NE -> MapDirection.N;
            case E -> MapDirection.NE;
            case SE -> MapDirection.E;
            case S -> MapDirection.SE;
            case SW -> MapDirection.S;
            case W -> MapDirection.SW;
            case NW -> MapDirection.W;
        };
    }

    public Vector2d toUnitVector(){
        return switch(this) {
            case N -> new Vector2d(0, 1);
            case NE -> new Vector2d(1, 1);
            case E -> new Vector2d(1, 0);
            case SE -> new Vector2d(1, -1);
            case S -> new Vector2d(0, -1);
            case SW -> new Vector2d(-1, -1);
            case W -> new Vector2d(-1, 0);
            case NW -> new Vector2d(-1, 1);
        };
    }


    private static final Random PRNG = new Random();

    public static MapDirection randomDirection()  {
        MapDirection[] orientations = values();
        return orientations[PRNG.nextInt(orientations.length)];
    }

}





