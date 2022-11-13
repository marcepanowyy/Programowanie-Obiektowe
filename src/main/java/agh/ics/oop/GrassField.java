package agh.ics.oop;


public class GrassField extends AbstractWorldMap {

    private final int fieldsCount;
    private final int maxFieldIndex;
    private static final Vector2d lowerLeftBound = new Vector2d(0, 0);
    private static final Vector2d upperRightBound = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public GrassField(int fieldsCount) {
        if (fieldsCount <= 0) throw new Error("grass fields error");
        this.fieldsCount = fieldsCount;
        this.maxFieldIndex = Math.min((int) Math.sqrt(fieldsCount * 10), Integer.MAX_VALUE);
        spawnGrass();
    }

    @Override
    public boolean place(IMapElement element) {
        Vector2d position = element.getPosition();
        boolean overrodeGrass = objectAt(position) instanceof Grass;
        boolean isPlaced = super.place(element);
        if (isPlaced) updateMapBounds(element.getPosition());
        if (overrodeGrass) spawnSingleGrass();
        return isPlaced;
    }

    @Override
    protected boolean isOnMap(Vector2d position) {
        return position.follows(lowerLeftBound) && position.precedes(upperRightBound);
    }

    private void updateMapBounds(Vector2d position) {
        if      (position.x < lowerLeft.x)  lowerLeft  = new Vector2d(position.x, lowerLeft.y);
        else if (position.x > upperRight.x) upperRight = new Vector2d(position.x, upperRight.y);
        if      (position.y < lowerLeft.y)  lowerLeft  = new Vector2d(lowerLeft.x, position.y);
        else if (position.y > upperRight.y) upperRight = new Vector2d(upperRight.x, position.y);
    }

    private Vector2d getNextEmptyField(int maxX, int maxY) {
        Vector2d position;
        do position = Vector2d.randomVector(maxX, maxY); while (isOccupied(position));
        return position;
    }

    private void spawnGrass() {
        IMapElement initialGrass = new Grass(getNextEmptyField(maxFieldIndex, maxFieldIndex));
        lowerLeft = upperRight = initialGrass.getPosition();
        place(initialGrass);
        for (int i = 1; i < fieldsCount; i++) spawnSingleGrass();
    }

    private void spawnSingleGrass() {
        IMapElement grass = new Grass(getNextEmptyField(maxFieldIndex, maxFieldIndex));
        place(grass);
    }
}


