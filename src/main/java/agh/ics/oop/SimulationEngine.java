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

    private int startEnergy = 10;
    private int genomeLength = 4;

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay) {
        this.positions = positions;
        this.map = map;
        this.moveDelay = moveDelay;
        addAnimalsToMap();
    }

//    public void setMoves(MoveDirection[] moves) {
//        this.moves = moves;
//    }

    private void addAnimalsToMap() {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position, startEnergy);
            map.placeNewMapElement(animal);
            animals.add(animal);
        }
    }

    @Override
    public void run() {
        if(animals.size() != 0) {
            while (true) {

                for (int i = 0; i < animals.size(); i++) {

                    try {
                        Thread.sleep(this.moveDelay);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Animal animal = animals.get(i);
                    animal.move();
                    mapChanged();
                }
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