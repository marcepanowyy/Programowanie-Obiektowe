package agh.ics.oop;

import java.util.HashSet;
import java.util.Set;
public class Animal extends AbstractMapElement {

    private MapDirection orientation = MapDirection.NORTH;
    private final IWorldMap map;

    public String toString(){
        return switch(this.orientation){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public Animal(IWorldMap map){

        super(new Vector2d(0, 0));
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        super(initialPosition);
        this.map = map;
        addObserver((IPositionChangeObserver) map);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition = position;

        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> newPosition = position.add(orientation.toUnitVector());
            case BACKWARD -> newPosition = position.subtract(orientation.toUnitVector());
        }

        if (position == newPosition || map.canMoveTo(newPosition)) {
            changePosition(position, newPosition);
        }
    }

    void changePosition(Vector2d oldPosition, Vector2d newPosition) {
        position = newPosition;
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}