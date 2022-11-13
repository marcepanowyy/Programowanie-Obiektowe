package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    int width = 10;
    int height = 5;
    IWorldMap map = new RectangularMap(width, height);

    @Test
    public void testPlaceAndObjectAt() {
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4), new Vector2d(2,2) };
        ArrayList<Animal> animals = new ArrayList<>();

        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            map.place(animal);
            animals.add(animal);
            assertTrue(map.isOccupied(position));
        }

        assertEquals(animals.get(0), map.objectAt(positions[0]));
        assertEquals(animals.get(1), map.objectAt(positions[1]));
        assertEquals(animals.get(0), map.objectAt(positions[2]));
    }

    @Test
    public void testCanMoveTo() {
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,9), new Vector2d(0,1) };
        HashSet<String> positionsSet = new HashSet<>();

        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            positionsSet.add(position.toString());
            map.place(animal);
        }

        Vector2d position;
        for (int i = -1; i <= width; i++) {
            for (int j = -1; j <= height; j++) {
                position = new Vector2d(i, j);
                if (i == -1 || i == width || j == -1 || j == height) {
                    assertFalse(map.canMoveTo(position));
                } else {
                    if (positionsSet.contains(position.toString())) assertFalse(map.canMoveTo(position));
                    else assertTrue(map.canMoveTo(position));
                }
            }
        }
    }

    @Test
    public void testIsOccupied() {
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,9), new Vector2d(0,1) };
        HashSet<String> positionsSet = new HashSet<>();

        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            positionsSet.add(position.toString());
            map.place(animal);
        }

        Vector2d position;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                position = new Vector2d(i, j);
                if (positionsSet.contains(position.toString())) assertTrue(map.isOccupied(position));
                else assertFalse(map.isOccupied(position));
            }
        }
    }
    @Test
    public void testMoveAnimal() {
        Animal animal1 = new Animal(map, new Vector2d(1, 1));
        Animal animal2 = new Animal(map, new Vector2d(1, 2));

        map.place(animal1);
        map.place(animal2);

        animal1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 1), animal1.getPosition());
        animal2.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(1, 2), animal2.getPosition());

        animal1.move(MoveDirection.RIGHT);
        animal1.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 1), animal1.getPosition());

        animal2.move(MoveDirection.LEFT);
        animal2.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(2, 2), animal2.getPosition());

        for (int i = 0; i < 100; i++) {
            animal1.move(MoveDirection.BACKWARD);
            animal2.move(MoveDirection.BACKWARD);
        }

        assertEquals(new Vector2d(0, 1), animal1.getPosition());
        assertEquals(new Vector2d(9, 2), animal2.getPosition());
    }
}