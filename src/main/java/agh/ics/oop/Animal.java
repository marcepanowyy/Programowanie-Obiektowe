package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {

    MapDirection orientation = MapDirection.NORTH;
    Vector2d position = new Vector2d(2, 2);


    public String toString(){
        return switch(this.orientation){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){

        Vector2d newPosition;

        switch (direction) {

            case RIGHT:
                orientation = orientation.next();
                break;

            case LEFT:
                orientation = orientation.previous();
                break;

            case FORWARD:
                newPosition = position.add(orientation.toUnitVector());
                if (onMap(newPosition)) position = newPosition;
                break;

            case BACKWARD:
                newPosition = position.subtract(orientation.toUnitVector());
                if (onMap(newPosition)) position = newPosition;
                break;
        }
    }

    public boolean onMap(Vector2d position){
        return (position.follows(World.bottomLeftVector) && position.precedes(World.topRightVector));
    }





}
