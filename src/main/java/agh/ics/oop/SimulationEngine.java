package agh.ics.oop;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class SimulationEngine implements Runnable, IEngine {
    private final ArrayList<Animal> animals = new ArrayList<>();
    private final Vector2d[] positions;
    private final IWorldMap map;
    private final int moveDelay;
    MoveDirection[] moves;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();
    private int energyToBreed;
    private int energyToFull;
    private int minNumOfMutations;
    private int maxNumOfMutations;
    private String mutationWarrant;
    private String behaviourWarrant;
    private String plantGrowthWarrant;
    private String mapWarrant;
    private int plantsNum;
    private int plantEnergy;
    private int plantsDaily;
    private int animalsNum;
    private int genomeLength;
    private int startingEnergy;
    private int height;
    private int width;

    public SimulationEngine(IWorldMap map, Vector2d[] positions, int moveDelay, int width, int height, int plantsNum, int plantEnergy, int plantsDaily, int animalsNum, int startingEnergy, int genomeLength, int energyToFull, int energyToBreed, int minNumOfMutations, int maxNumOfMutations,
                            String mutationWarrant, String behaviourWarrant, String plantGrowthWarrant, String mapWarrant) {
        this.positions = positions;
        this.map = map;
        this.moveDelay = moveDelay;
        this.height = height;
        this.width = width;
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
        this.mapWarrant = mapWarrant;
        System.out.println(genomeLength);
        addAnimalsToMap();
    }

//    public void setMoves(MoveDirection[] moves) {
//        this.moves = moves;
//    }

    private void addAnimalsToMap() {
        for (Vector2d position : positions) {
            Animal animal = new Animal(map, position, startingEnergy, genomeLength);
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
                    animal.reduceEnergy();
                    animal.move(mapWarrant, width, height);
                    removeDead(animal, i);

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


    public void removeDead(Animal animal, int idx){
        if (animal.isDead()){
            map.removeAnimal(animal.getPosition());
            animals.remove(idx);
        }
    }
}