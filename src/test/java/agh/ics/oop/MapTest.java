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

        this.map = new AbstractWorldMap(20, 30, 40, 40, 40, 40);


        Animal animal1 = new Animal(this.map, new Vector2d(5,5), 10);
        Animal animal2 = new Animal(this.map, new Vector2d(2,2), 10);
        Animal animal3 = new Animal(this.map, new Vector2d(3,3), 10);
        this.map.place(animal1);
        this.map.place(animal2);
        this.map.place(animal3);
        System.out.println(map.animalsList);
        System.out.println(map.animals);
        animal1.move();
        animal2.move();
        animal3.move();
        System.out.println(map.animalsList);
        System.out.println(map.animals);
    }


}
