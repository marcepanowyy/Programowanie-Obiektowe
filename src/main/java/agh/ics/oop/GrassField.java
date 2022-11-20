package agh.ics.oop;


public class GrassField extends AbstractWorldMap {

//    private final int fieldsCount;
    private final int maxGrassFieldIndex;
    private final MapBoundary mapBoundary = new MapBoundary(this);
    private static final Vector2d lowerLeftBound = new Vector2d(0, 0);
    private static final Vector2d upperRightBound = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
    private int elementsCount = 0;

    public GrassField(int grassCount) {
        if (grassCount <= 0) throw new Error("grass fields error");
        this.maxGrassFieldIndex = Math.min((int) Math.sqrt(grassCount * 10), Integer.MAX_VALUE);
        spawnGrass();
    }

    @Override
    public void place(IMapElement element) throws IllegalArgumentException {
        Vector2d position = element.getPosition();
        if (objectAt(position) != element) elementsCount ++;
        boolean overrodeGrass = objectAt(position) instanceof Grass;
        IMapElement grass = objectAt(position);
        newMapElements.remove(grass);
        if (overrodeGrass) grass.remove();
        super.place(element);
        updateMapBounds(null, position);
        if (overrodeGrass) spawnSingleGrass();
    }

    @Override
    protected boolean isOnMap(Vector2d position) {
        return position.follows(lowerLeftBound) && position.precedes(upperRightBound);
    }

    private void updateMapBounds(Vector2d oldPosition, Vector2d newPosition) {
        mapBoundary.positionChanged(oldPosition, newPosition);
        lowerLeft = mapBoundary.getLowerLeftBound();
        upperRight = mapBoundary.getUpperRightBound();
    }

    private Vector2d getNextEmptyField(int maxX, int maxY) {
        Vector2d position;
        do position = Vector2d.randomVector(maxX, maxY); while (isOccupied(position));
        return position;
    }

    private void spawnGrass() {
        for (int i = 1; i < maxGrassFieldIndex; i++) spawnSingleGrass();
    }

    private void spawnSingleGrass() {

        if(elementsCount <= maxGrassFieldIndex * maxGrassFieldIndex){
            IMapElement grass = new Grass(getNextEmptyField(maxGrassFieldIndex, maxGrassFieldIndex));
            placeNewMapElement(grass);
            updateMapBounds(null, grass.getPosition());
        }
    }
}


