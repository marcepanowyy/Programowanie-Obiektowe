package agh.ics.oop;

import java.util.LinkedList;

public class RectangularMap implements IWorldMap{

        private final Vector2d lowerLeft;
        private final Vector2d upperRight;
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        private final LinkedList<Animal> animals = new LinkedList<>();

        RectangularMap(int width, int height){
            this.lowerLeft = new Vector2d(0, 0);
            this.upperRight = new Vector2d(width-1, height-1);
        }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return (position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position));
    }

    @Override
    public boolean place(Animal animal){

        if (animals.size() == 0){
            animals.add(animal);
            return true;
         }
        else if (!isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        }
        return false;
    }


    // jesli pozycja zajeta, zwracamy false

    @Override
    public boolean isOccupied(Vector2d position) {
        return this.objectAt(position) != null;
    }

    // jesli zaden ze zwierzakow nie znajduje sie na danej pozycji
    // zwracamy null
    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal: animals) {
            if (animal.isAt(position)) return animal;
        }
        return null;
    }

    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }
}
