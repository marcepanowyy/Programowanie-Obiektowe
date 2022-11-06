package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public class GrassField extends AbstractWorldMap {

    private final int fieldsCount;
    private final int maxFieldIndex;
    public final Map<String, IMapElement> mapElements = new HashMap<>();

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
        if ((!isOccupied(position) || element instanceof Animal) && canMoveTo(position)) {
            mapElements.put(position.toString(), element);
            updateMapBounds(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return mapElements.containsKey(position.toString());
    }

    @Override
    public Object objectAt(Vector2d position) {
        return mapElements.get(position.toString());
    }

    @Override
    public void moveAnimal(Animal animal, MoveDirection move) {
        Vector2d prevPosition = animal.getPosition();
        animal.move(move);
        Vector2d currPosition = animal.getPosition();

        boolean isGrass = false;
        if (!currPosition.equals(prevPosition)) {
            mapElements.remove(prevPosition.toString());
            isGrass = objectAt(animal.getPosition()) instanceof Grass;
            mapElements.put(currPosition.toString(), animal);
        }

        if (isGrass) {
            Vector2d position = getNextEmptyField(maxFieldIndex, maxFieldIndex);
        }

    }

    private void spawnGrass() {
        IMapElement grass = new Grass(getNextEmptyField(maxFieldIndex, maxFieldIndex));
        Vector2d grassPosition = grass.getPosition();
        lowerLeft = upperRight = grassPosition;
        mapElements.put(grassPosition.toString(), grass);
        for (int i = 1; i < fieldsCount; i++) {
            place(new Grass(getNextEmptyField(maxFieldIndex, maxFieldIndex)));
        }
    }


    private void updateMapBounds(Vector2d position) {
        if (position.x < lowerLeft.x) lowerLeft = new Vector2d(position.x, lowerLeft.y);
        else if (position.x > upperRight.x) upperRight = new Vector2d(position.x, upperRight.y);
        if (position.y < lowerLeft.y) lowerLeft = new Vector2d(lowerLeft.x, position.y);
        else if (position.y > upperRight.y) upperRight = new Vector2d(upperRight.x, position.y);
    }

    private Vector2d getNextEmptyField(int maxX, int maxY){
        Vector2d position;
        do position = Vector2d.randomVector(maxX, maxY); while (isOccupied(position));
        return position;
    }

    @Override
    protected boolean isOnMap(Vector2d position){
        return position.follows(lowerLeftBound) && position.precedes(upperRightBound);
    }

}




