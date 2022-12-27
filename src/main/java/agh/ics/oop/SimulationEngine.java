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
    private final IWorldMap map;
    private final int moveDelay;
    MoveDirection[] moves;
    private final ArrayList<ISimulationEngineObserver> observers = new ArrayList<ISimulationEngineObserver>();
    private int width;
    private int height;
    private int plantsNum;
    private int plantEnergy;
    private int animalsNum;
    private int startingEnergy;
    private int genomeLength;

    public SimulationEngine(IWorldMap map, int moveDelay) {
        this.map = map;
        this.moveDelay = moveDelay;
        addAnimalsToMap();
    }

    public void setMoves(MoveDirection[] moves) {
        this.moves = moves;
    }

    private void addAnimalsToMap() {

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


    public void setValues(int width, int height, int plantsNum, int plantEnergy, int animalsNum, int startingEnergy, int genomeLength) {
        this.width = width;
        this.height = height;
        this.plantsNum = plantsNum;
        this.plantEnergy = plantEnergy;
        this.animalsNum = animalsNum;
        this.startingEnergy = startingEnergy;
        this.genomeLength = genomeLength;
    }
}