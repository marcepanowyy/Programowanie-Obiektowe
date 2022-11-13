package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {
    public RectangularMap(int width, int height) {
        this.lowerLeft  = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    protected boolean isOnMap(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight);
    }
}