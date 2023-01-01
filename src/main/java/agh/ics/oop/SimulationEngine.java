package agh.ics.oop;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
    private int mostPopularGen = -1;
    private int array[] = new int[8];
    private int averageEnergy = 0;
    private int allFields;


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
        this.allFields = width * height;
        addAnimalsToMap();
        map.spawnGrass(plantsNum);
        Arrays.fill(this.array, 0);
    }
    public Vector2d getRandomVector() {
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

        WriteToCSV.saveRecord("day count", "animals alive count", " animals dead count", "grass count", "freeFields", "famousGenotype", "averageAnimalEnergy", "averageAnimalLifespan", "statistics.csv");
        WriteToCSV.saveRecord(Integer.toString(counter), Integer.toString(map.animalsAliveCount), Integer.toString(map.animalsDeadCount), Integer.toString(map.grassCount), Integer.toString(allFields - map.countFreeFields()), Integer.toString(mostPopularGenotype()),  Integer.toString(startingEnergy), Integer.toString(map.averageLifespan()), "statistics.csv");

        while (map.getAnimals().size() > 0) {
            for (int i = 0; i < map.getAnimals().size(); i++) {

                try {
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Animal animal = map.getAnimals().get(i);
                animal.move();
                array[animal.mostPopularGen] += 1;
                if (animal.energy > 0){
                    averageEnergy += animal.energy;
                }
                if (animal.followed){
                    animal.follow(counter);
                }
            }

            counter++;
            map.feedThemALL();
            map.copulateThemAll();
            map.removeDeadAnimals();
            map.spawnGrass(plantsDaily);
            WriteToCSV.saveRecord(Integer.toString(counter), Integer.toString(map.animalsAliveCount), Integer.toString(map.animalsDeadCount), Integer.toString(map.grassCount), Integer.toString(allFields - map.countFreeFields()), Integer.toString(mostPopularGenotype()), Integer.toString(map.averageEnergy(averageEnergy)), Integer.toString(map.averageLifespan()
            ),"statistics.csv");
            Arrays.fill(this.array, 0);
            averageEnergy = 0;
            mapChanged();

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
    public int mostPopularGenotype(){
        int largest = 0;
        for (int i = 0; i < this.array.length; i++){
            if (this.array[i] > this.array[largest]) {largest = i;}
        }
        return largest;
    }

}