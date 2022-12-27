//package agh.ics.oop;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class AnimalTest {
//
//    private AbstractWorldMap map;
//
//    @Test
//    void testAnimal(){
//
//        this.map = new GrassField(20);
//
//        Animal animal1 = new Animal(this.map, new Vector2d(5,5), 10);
//        Animal animal2 = new Animal(this.map, new Vector2d(5,5), 10);
//        this.map.place(animal1);
////        this.map.place(animal2);
//        System.out.println(animal1.getPosition());
//        System.out.println(animal1.getGenomeList().toString());
//        System.out.println(animal1.getOrientation().toString());
//        System.out.println(map.canMoveTo(animal1.getPosition()));
//    }
//
//
//}
