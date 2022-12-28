package agh.ics.oop;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import agh.ics.oop.gui.App;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SimulationEngine implements Runnable, IEngine {
    private final AbstractWorldMap map;
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
    public int plantEnergy;
    private int plantsDaily;
    private int animalsNum;
    private int genomeLength;
    private int startingEnergy;

    public SimulationEngine(AbstractWorldMap map, int moveDelay, int width, int height, int plantsNum,int plantEnergy,int plantsDaily,int animalsNum, int startingEnergy, int genomeLength, int energyToFull, int energyToBreed, int minNumOfMutations, int maxNumOfMutations,
                            String mutationWarrant, String behaviourWarrant, String plantGrowthWarrant, String worldWarrant) {
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
        map.spawnGrass(plantsNum);
    }

    public Vector2d getRandomVector(){

        Random rand = new Random();
        int x = rand.nextInt(this.width);
        int y = rand.nextInt(this.height);
        return new Vector2d(x, y);

    }
//    private void addAnimalsToMap() {
//        for (Vector2d position : positions) {
//            Animal animal = new Animal(map, position, startingEnergy);
//            map.place(animal);
//        }
//    }

    private void addAnimalsToMap() {
        for (int i = 0; i < this.animalsNum; i++) {
            Animal animal = new Animal(map, getRandomVector(), startingEnergy);
//            animals.add(animal);
            map.place(animal);
        }
    }


    @Override
    public void run() {

        if(map.getAnimals().size() != 0) {
            while (true) {

                for (int i = 0; i < map.getAnimals().size(); i++) {

                    try {
                        Thread.sleep(this.moveDelay);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Animal animal = map.getAnimals().get(i);
                    animal.move();
                    mapChanged();
                }
                map.spawnGrass(plantsDaily);
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