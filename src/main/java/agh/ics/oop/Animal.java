package agh.ics.oop;

import java.util.*;

public class Animal extends AbstractMapElement {

    private MapDirection orientation = MapDirection.randomDirection();
    private final IWorldMap map;
    public ArrayList<IPositionChangeObserver> observerlist = new ArrayList<>();

    private int energy = 10;
    private int genomeLength = 4;
    private List<Integer> genomeList;

    private int actualGenomeIndex;
    public String toString(){
        return switch(this.orientation){
            case N -> "^";
            case NE -> "^>";
            case E -> ">";
            case SE -> "v>";
            case S -> "v";
            case SW -> "v<";
            case W -> "<";
            case NW -> "<^";
        };
    }
    public Animal(IWorldMap map){

        super(new Vector2d(0, 0));
        this.map = map;
//        addObserver((IPositionChangeObserver) map);
    }
    public Animal(IWorldMap map, Vector2d initialPosition, int energy){
        super(initialPosition);
        this.map = map;
        this.genomeList = generateRandomGenome();
        this.orientation = MapDirection.randomDirection();
        this.actualGenomeIndex = -1;
        this.energy = energy;
//        addObserver((IPositionChangeObserver) map);
    }

    public int getRandomNumber(){
        return (int)(Math.random() * 8);
    }

    public List<Integer> generateRandomGenome() {
        List<Integer> newGenome = new ArrayList<>();
        for (int i = 0; i < genomeLength ; i++) {
            newGenome.add(getRandomNumber());
        }
        return newGenome;
    }


    public List<Integer> getGenomeList(){
        return this.genomeList;
    }

    public MapDirection getOrientation(){
        return this.orientation;
    }

    @Override
    public String getPath(IMapElement object){
        Animal animal = (Animal) object;
        MapDirection orientation = animal.orientation;
        switch (orientation){
            case N -> {return "src/main/resources/turtleN.jpg";}
            case NW -> {return "src/main/resources/turtleNW.jpg";}
            case E -> {return "src/main/resources/turtleE.jpg";}
            case NE -> {return "src/main/resources/turtleNE.jpg";}
            case SE -> {return "src/main/resources/turtleSE.jpg";}
            case SW -> {return "src/main/resources/turtleSW.jpg";}
            case W -> {return "src/main/resources/turtleW.jpg";}
            default -> {return "src/main/resources/turtleW.jpg";}
        }
    }

    // getting genome

    public int getRotationNum(){
        actualGenomeIndex = (actualGenomeIndex+1) % this.genomeLength;
        return this.genomeList.get(actualGenomeIndex);
    }


    // rotation

    public void rotate(){

        int rotation = getRotationNum();

        for(int i = 1; i <= rotation; i++){
            this.orientation = this.orientation.next();

        }

    }

//    OBSERVERS

    public void addObserver(IPositionChangeObserver observer) {
        observerlist.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observerlist.remove(observer);
    }
    private void positionChanged(Vector2d old, Vector2d n, Object a) {
        for (IPositionChangeObserver o : observerlist) {
            o.positionChanged(old, n, a);
        }
    }

    // moving

//    public void move(String warrant, int width, int height) {
    public void move() {

        Vector2d newPosition = position;
//        if (warrant.equals("kula ziemska")){
            //TO DO
//            System.out.println("OK");
//            newPosition = position.add(orientation.toUnitVector());
//            if (newPosition.x > width) {
//                newPosition = new Vector2d(0, position.y);
//            }
//            else if (newPosition.x < 0){
//                newPosition = new Vector2d(width - 1, position.y);
//            }
//        }
//        else {
//            newPosition = position.add(orientation.toUnitVector());
//        }

        newPosition = position.add(orientation.toUnitVector());
        this.positionChanged(this.position, newPosition, this);
        position = newPosition;
        rotate();

    }

    public boolean isDead() {
        return this.energy <= 0;
    }

    public void changeEnergy(int value) {
        this.energy = this.energy + value;
        if (this.energy < 0) {
            this.energy = 0;
        }
        this.energy--;
    }




}