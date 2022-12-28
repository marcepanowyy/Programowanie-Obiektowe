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
    private final String worldWarrant;
    MoveDirection[] moves;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();
    private int width;
    private int height;
    private int energyToBreed;
    private int energyToFull;
    private int minNumOfMutations;
    private int maxNumOfMutations;
    private String mutationWarrant;
    private String behaviourWarrant;
    private String plantGrowthWarrant;
    private int plantsNum;
    private int plantEnergy;
    private int plantsDaily;
    private int animalsNum;
    private int genomeLength;
    private int startingEnergy;

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay, int width, int height, int plantsNum,int plantEnergy,int plantsDaily,int animalsNum, int startingEnergy, int genomeLength, int energyToFull, int energyToBreed, int minNumOfMutations, int maxNumOfMutations,
                            String mutationWarrant, String behaviourWarrant, String plantGrowthWarrant, String worldWarrant) {
        this.positions = positions;
        this.map = map;
        this.moveDelay = moveDelay;
        this.width = width;
        this.height = height;
        this.plantsNum = plantsNum;
        this.plantEnergy = plantEnergy;
        this.plantsDaily = plantsDaily;
        this.animalsNum = animalsNum;
        this.startingEnergy = startingEnergy;
        this.genomeLength = genomeLength;
        this.energyToFull = energyToFull;
        this.energyToBreed = energyToBreed;
        this.minNumOfMutations = minNumOfMutations;
        this.maxNumOfMutations = maxNumOfMutations;
        this.mutationWarrant = mutationWarrant;
        this.behaviourWarrant = behaviourWarrant;
        this.plantGrowthWarrant = plantGrowthWarrant;
        this.worldWarrant = worldWarrant;
        addAnimalsToMap();
    }

    private void addAnimalsToMap() {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position, startingEnergy);
            map.placeNewMapElement(animal);
            animals.add(animal);
            ;
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