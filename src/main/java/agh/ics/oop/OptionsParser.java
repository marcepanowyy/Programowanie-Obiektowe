package agh.ics.oop;

import java.util.ArrayList;

public class OptionsParser {

    public static ArrayList<MoveDirection> parse(String[] directions) {

        ArrayList<MoveDirection> FilteredDirections = new ArrayList<>();

        for (String move : directions) {

            switch (move) {
                case "f", "forward":
                    FilteredDirections.add(MoveDirection.FORWARD);
                    break;

                case "b", "backward":
                    FilteredDirections.add(MoveDirection.BACKWARD);
                    break;

                case "l", "left":
                    FilteredDirections.add(MoveDirection.LEFT);
                    break;

                case "r", "right":
                    FilteredDirections.add(MoveDirection.RIGHT);
                    break;

                default:
                    ;

            }
        }
        return FilteredDirections;
    }
}

