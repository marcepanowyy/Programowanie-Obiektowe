package agh.ics.oop;

import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.Random;

public class SimulationEngine implements Runnable, IEngine {
    private final AbstractWorldMap map;
    private final int moveDelay;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();
    private int width;
    private int height;
    private int energyUsedForBreeding;
    private int minEnergyToBreed;
    private int minNumOfMutations;
    private int maxNumOfMutations;
    private final int mapMode;
    private int mutationMode;
    private int behaviourMode;
    private int plantGrowthMode;
    private int plantsNum;
    public int plantEnergy;
    private int plantsDaily;
    private int animalsNum;
    private int genomeLength;
    private int startingEnergy;
    private int counter = 0;

    public SimulationEngine(AbstractWorldMap map, int moveDelay, int width, int height, int plantsNum, int plantEnergy, int plantsDaily, int animalsNum, int startingEnergy, int genomeLength, int minEnergyToBreed, int energyUsedForBreeding, int minNumOfMutations, int maxNumOfMutations,
                            int mutationMode, int behaviourMode, int plantGrowthMode, int mapMode) {
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
        this.minEnergyToBreed = minEnergyToBreed;
        this.energyUsedForBreeding = energyUsedForBreeding;
        this.minNumOfMutations = minNumOfMutations;
        this.maxNumOfMutations = maxNumOfMutations;
        this.mutationMode = mutationMode;
        this.behaviourMode = behaviourMode;
        this.mapMode = mapMode;
        this.plantGrowthMode = plantGrowthMode;
        addAnimalsToMap();
        map.spawnGrass(plantsNum);
    }

    public Vector2d getRandomVector(){

        Random rand = new Random();
        int x = rand.nextInt(this.width);
        int y = rand.nextInt(this.height);
        return new Vector2d(x, y);

    }
    private void addAnimalsToMap() {
        for (int i = 0; i < this.animalsNum; i++) {
            Animal animal = new Animal(map, getRandomVector(), startingEnergy, genomeLength, energyUsedForBreeding, mapMode, mutationMode, behaviourMode);
            map.place(animal);
        }
    }

    @Override
    public void run() {

        if(map.getAnimals().size() != 0) {
            while (true) {
                for (int i = 0; i < map.getAnimals().size(); i++) {

                    try {
//                        Thread.sleep(this.moveDelay);
                        Thread.sleep(300);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Animal animal = map.getAnimals().get(i);

//                    System.out.println("mutation mode: " + animal.mutationMode + ", behaviour mode: " + animal.behaviourMode + ", map mode " + animal.mapMode + ", plant growth mode: " + map.plantGrowthMode);
                    animal.move();
                    System.out.println(animal.relativeEnergy + " " + animal.energy);
                    mapChanged();
                }

                counter++;
                map.feedThemALL();
                map.copulateThemAll();
                map.spawnGrass(plantsDaily);
                map.removeDeadAnimals();
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