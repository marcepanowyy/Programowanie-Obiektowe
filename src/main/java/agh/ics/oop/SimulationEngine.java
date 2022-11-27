package agh.ics.oop;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SimulationEngine implements Runnable, IEngine {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Vector2d[] positions;
    private final IWorldMap map;
    private final int moveDelay;
    MoveDirection[] moves;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay) {
        this.positions = positions;
        this.map = map;
        this.moveDelay = moveDelay;
        addAnimalsToMap();
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }

    private void addAnimalsToMap() {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position);
            map.placeNewMapElement(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        if(animals.size() != 0) {
            int j;
            for (int i = 0; i < moves.length; i++) {

                try{
                    Thread.sleep(this.moveDelay);

                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                j = i % animals.size();
                Animal animal = animals.get(j);
                if (j == 0) System.out.println(map);
                System.out.println("animal direction: " + animal + ", position before next move: " + animal.getPosition() + ", next move: " + moves[i]);
                animal.move(moves[i]);
                mapChanged();
            }
        }
    }

    public void addObserver(ISimulationEngineObserver observer) {
        this.observers.add(observer);
    }

    public void mapChanged() {
        for (ISimulationEngineObserver observer : observers) {
            try {
                observer.mapChanged();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }


}