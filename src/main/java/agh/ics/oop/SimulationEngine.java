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
