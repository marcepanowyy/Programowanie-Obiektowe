package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        ArrayList<MoveDirection> filteredDirections = new ArrayList<>();
        for(String arg : args){
            switch(arg){
                case "f", "forward" -> filteredDirections.add(MoveDirection.FORWARD);
                case "b", "backward" -> filteredDirections.add(MoveDirection.BACKWARD);
                case "r", "right" -> filteredDirections.add(MoveDirection.RIGHT);
                case "l", "left" -> filteredDirections.add(MoveDirection.LEFT);
            }
        }
        MoveDirection[] result = new MoveDirection[0];
        result = filteredDirections.toArray(result);
        return result;
    }
}

