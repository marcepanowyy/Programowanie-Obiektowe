package agh.ics.oop;

import java.util.*;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected Set<IMapElement> newMapElements = new HashSet<>();

    public AbstractWorldMap() {
        this.mapVisualizer = new MapVisualizer(this);
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Set<IMapElement> getNewMapElements(){
        Set <IMapElement> returnedSet = newMapElements;
        newMapElements = new HashSet<>();
        return returnedSet;
    }

    public void placeNewMapElement(IMapElement element){
        newMapElements.add(element);
        place(element);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        IMapElement movedElement = remove(oldPosition);
        if(newPosition != null) place(movedElement);
    }

    @Override
    public void place(IMapElement element) throws IllegalArgumentException {
        Vector2d position = element.getPosition();
        if ((!isOccupied(position) || element instanceof Animal) && canMoveTo(position)) {
            mapElements.put(position, element);

        } else throw new IllegalArgumentException(element + " (" + element.getClass() + ") cannot be placed on position" + position);
    }

    @Override
    public IMapElement objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public boolean canMoveTo(Vector2d position) {
        return (isOnMap(position));
    }

    public boolean isOccupied(Vector2d position) {
        return mapElements.containsKey(position);
    }

    public IMapElement remove(Vector2d position) {
        return mapElements.remove(position);
    }

    protected abstract boolean isOnMap(Vector2d position);
}