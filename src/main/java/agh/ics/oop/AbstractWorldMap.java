package agh.ics.oop;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector2d lowerLeft;
    protected Vector2d upperRight;

    protected final MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        this.mapVisualizer = new MapVisualizer(this);
    }

    public boolean canMoveTo(Vector2d position) {
        return (this.isOnMap(position) && !(objectAt(position) instanceof Animal));
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    public abstract boolean place(IMapElement animal);

    public abstract boolean isOccupied(Vector2d position);

    protected abstract boolean isOnMap(Vector2d position);

    public abstract Object objectAt(Vector2d position);
}

