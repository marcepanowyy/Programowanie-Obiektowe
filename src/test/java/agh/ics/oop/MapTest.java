package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

    private AbstractWorldMap map;

    @Test
    void testAnimal(){

        this.map = new AbstractWorldMap(5, 5, 40, 40, 40, 40);

        Animal animal1 = new Animal(this.map, new Vector2d(3,5), 10, 10, 10, 0, 0, 0);
        animal1.orientation = MapDirection.N;
        this.map.place(animal1);
        System.out.println(animal1.getGenomeList());
        System.out.println(map.animals);
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();
        System.out.println(animal1.actualGenomeIndex);
        animal1.move();

    }


}
