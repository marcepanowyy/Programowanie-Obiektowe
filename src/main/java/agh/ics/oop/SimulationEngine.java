package agh.ics.oop;

import java.util.ArrayList;

public class SimulationEngine implements IEngine {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Vector2d[] positions;
    private final MoveDirection[] moves;
    private final IWorldMap map;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] positions) {
        this.positions = positions;
        this.moves = moves;
        this.map = map;
    }

    private void addAnimalsToMap() {
        for (Vector2d position: positions) {
            Animal animal = new Animal(map, position);
            boolean isPlaced = map.place(animal);
            if (isPlaced) animals.add(animal);
        }
    }

    @Override
    public void run() {
        addAnimalsToMap();
        int j;
        for (int i = 0; i < moves.length; i++) {
            j = i % animals.size();
            Animal animal = animals.get(j);

            if (j == 0) System.out.println(map);
            System.out.println("animal direction: " + animal + ", position before next move: " + animal.getPosition() + ", next move: " + moves[i]);

            animal.move(moves[i]);
        }
    }
}