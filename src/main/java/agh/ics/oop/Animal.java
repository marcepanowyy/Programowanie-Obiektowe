package agh.ics.oop;

import java.util.HashSet;
import java.util.Set;
public class Animal implements IMapElement {

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private final IWorldMap map;
    private final Set<IPositionChangeObserver> observers = new HashSet<>(){};

    public String toString(){
        return switch(this.orientation){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public Animal(IWorldMap map){
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        position = new Vector2d(0, 0);
    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        addObserver((IPositionChangeObserver) map);
        this.position = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        Vector2d newPosition;

        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> {
                newPosition = position.add(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) changePosition(position, newPosition);
            }
            case BACKWARD -> {
                newPosition = position.subtract(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)) changePosition(position, newPosition);
            }
        }
    }

    void changePosition(Vector2d oldPosition, Vector2d newPosition) {
        position = newPosition;
        positionChanged(oldPosition, newPosition);
    }

    void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer: observers) {
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}