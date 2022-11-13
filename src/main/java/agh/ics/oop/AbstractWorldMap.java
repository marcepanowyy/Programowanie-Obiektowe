package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();

    public AbstractWorldMap() {
        this.mapVisualizer = new MapVisualizer(this);
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    public boolean canMoveTo(Vector2d position) {
        return (isOnMap(position) && !(objectAt(position) instanceof Animal));
    }

    public boolean place(IMapElement element) {
        Vector2d position = element.getPosition();

        if ((!isOccupied(position) || element instanceof Animal) && canMoveTo(position)) {
            mapElements.put(position, element);
            return true;
        }
        return false;
    }

    public boolean isOccupied(Vector2d position) {
        return mapElements.containsKey(position);
    }

    public IMapElement objectAt(Vector2d position) {
        return mapElements.get(position);
    }

    public IMapElement remove(Vector2d position) {
        return mapElements.remove(position);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        IMapElement movedElement = remove(oldPosition);
        place(movedElement);
    }

    protected abstract boolean isOnMap(Vector2d position);
}