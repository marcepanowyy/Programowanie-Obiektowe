package agh.ics.oop;

public class SimulationEngine implements IEngine {

    private final MoveDirection[] moves;
    private final Animal[] animals;


    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
        this.animals = new Animal[positions.length];
        this.moves = moves;

        for(int i=0; i< animals.length; i++){
            animals[i] = new Animal(map, positions[i]);
            map.place(animals[i]);
        }
    }

    @Override
    public void run(){
        for(int i=0; i<this.moves.length; i++){
            animals[i % animals.length].move(moves[i]);
        }
    }

}


//package agh.ics.oop;
//
//import java.util.ArrayList;
//
//public class SimulationEngine implements IEngine {
//
//    private final ArrayList<Animal> animals = new ArrayList<>();
//    private final Vector2d[] positions;
//    private final MoveDirection[] moves;
//    private final IWorldMap map;
//
//    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions){
//        this.positions = positions;
//        this.moves = moves;
//        this.map = map;
//    }
//
//    private void addAnimalsToMap(){
//        for(Vector2d position: positions){
//            Animal animal = new Animal(map, position);
//            boolean isPlaced = map.place(animal);
//            if(isPlaced) animal.add(animal);
//        }
//    }
//
//    @Override
//    public void run(){
//        addAnimalsToMap();
//        int j;
//        for (int i=0; i< moves.length; i++){
//            j = i % animals.size();
//            Animal animal = animals.get(j);
//
//            if (j==0) System.out.println(map);
//            System.out.println("[j=" + j +  "] Moving animal: " + animal + " (" + animal.getPosition() + "). Move: " + moves[i]);
//            map.moveAnimal(animal, moves[i]);
//
//        }
//    }
//
//
//
//}