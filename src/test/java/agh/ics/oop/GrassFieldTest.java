package agh.ics.oop;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {


    // czy mozemy sie ruszyc na zajeta juz pozycje

    @Test
    public void testCanMoveTo(){

        IWorldMap map = new GrassField(10);

        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,9), new Vector2d(0,1) };
        HashSet<String> positionsSet = new HashSet<>();

        for (Vector2d position: positions){
            Animal animal = new Animal(map, position);
            positionsSet.add(position.toString());
            map.place(animal);
        }

        Vector2d position;
        for(int i = 0; i <= 11; i++){
            for(int j = 0; j <= 11; j++){
                position = new Vector2d(i, j);
                if(positionsSet.contains(position.toString())) assertFalse(map.canMoveTo(position));
                else assertTrue(map.canMoveTo(position));
            }
        }

    }

    //czy umiescilismy rzeczywiscie 10 kepek trawy

    @Test
    public void testPlace(){

        IWorldMap map = new GrassField(10);

        int grassCount = 0;
        Vector2d position;

        for (int x = 0; x <= 10; x++) {
            for (int y = 0; y <= 10; y++) {
                position = new Vector2d(x, y);
                if (map.objectAt(position) instanceof Grass) grassCount++;
            }
        }
        assertEquals(10, grassCount);


        // wypelniam cala mape zwierzakami, licze kepki trawy i zwierzeta

        ArrayList<Animal> animals = new ArrayList<>();
        Animal animal;

        for(int x = 0; x <= 10; x++){
            for(int y = 0; y <= 10; y++){
                animal = new Animal(map, new Vector2d(x, y));
                animals.add(animal);
                map.place(animal);
            }
        }

        int animalsCount = 0;
        grassCount = 0;

        for(int x = 0; x <= 10; x++){
            for(int y = 0; y <= 10; y++){
                position = new Vector2d(x, y);
                if(map.objectAt(position) instanceof Animal) animalsCount++;
                else if (map.objectAt(position) instanceof Grass) grassCount++;
                }
            }

        // powinno pozostac 0 kepek trawy, 121 zwierzat zapelniajacych mape

        assertEquals(0, grassCount);
        assertEquals(121, animalsCount);

        // sprawdzam czy zwierzeta zostaly poprawnie umieszczone

        for (Animal a: animals) assertEquals(a, map.objectAt(a.getPosition()));

    }

    @Test
    void testIsOccupied() {

        IWorldMap map = new GrassField(10);
        Animal animal = new Animal(map, new Vector2d(2, 2));
        map.place(animal);

        boolean result1 = map.isOccupied(new Vector2d(2 ,2));
        boolean result2 = map.isOccupied(new Vector2d(3, 2));
        if (result2 && !(map.objectAt(new Vector2d(3, 2)) instanceof Animal)){
            result2 = false;
        }

        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    void testObjectAt() {

        IWorldMap map = new GrassField(10);
        Animal animal = new Animal(map, new Vector2d(2, 2));
        map.place(animal);
        Object result = map.objectAt(new Vector2d(2, 2));
        assertEquals(result, animal);
    }

}


