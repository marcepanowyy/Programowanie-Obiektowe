package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class World {

    public static void main(String[] args){

        System.out.println("system wystartowal");

        List <Direction> FilteredDirections = new ArrayList<>();

        for(String arg: args){

            switch(arg)
                {
                    case "f": FilteredDirections.add(Direction.FORWARD);
                    break;

                    case "b": FilteredDirections.add(Direction.BACKWARD);
                    break;

                    case "l": FilteredDirections.add(Direction.LEFT);
                    break;

                    case "r": FilteredDirections.add(Direction.RIGHT);
                    break;

                    default: ;

                }
            }

        run(FilteredDirections);
        System.out.println("system zakonczyl dzialanie");
    }
    public static void run(List<Direction>directions){

        for(Direction move: directions){
            System.out.println(move.getDisplayName());
        }

    }

}
