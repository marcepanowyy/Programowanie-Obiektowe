package agh.ics.oop;

import java.util.*;

public class Animal extends AbstractMapElement {

    public MapDirection orientation = MapDirection.randomDirection();
    private final IWorldMap map;
    public ArrayList<IPositionChangeObserver> observerlist = new ArrayList<>();
    public int age;
    public int kids;
    public int energy;
    private int genomeLength;
    private List<Integer> genomeList;
    public int actualGenomeIndex;
    private int energyToBreed;
    private final int energyToFull = 10;
    private int mapMode;
    private int mutationMode;
    private int behaviourMode;



    // mode 0 - kula ziemska
    // mode 1 - piekielny portal

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
    }

    public Animal(IWorldMap map, Vector2d initialPosition, int energy, int genomeLength, int energyToBreed, int mapMode, int mutationMode, int behaviourMode){
        super(initialPosition);
        this.map = map;
        this.genomeLength = genomeLength;
        this.genomeList = generateRandomGenome();
        this.orientation = MapDirection.randomDirection();
        this.actualGenomeIndex = -1;
        this.energy = energy;
        this.energyToBreed = energyToBreed;
        this.mapMode = mapMode; // 0 - kula ziemska, 1 - piekielny portal DONE
        this.mutationMode = mutationMode; // 0 - brak mutacji, 1 - pelna losowosc, 2 - lekka korekta
        this.behaviourMode = behaviourMode; // 0 - pelna predestynacja, 1 - nieco szalenstwa DONE
        this.age = 0;
        this.kids = 0;
    }

    public Animal(Animal animal1, Animal animal2, Vector2d position){
        super(position);
        this.energy = animal1.energyToBreed + animal2.energyToBreed;
        this.map = animal1.map;
        this.genomeLength = animal1.genomeLength;
        this.orientation = MapDirection.randomDirection();
        this.actualGenomeIndex = 0; // Popraw
        this.genomeList = parentsGenome(animal1, animal2);
        this.energyToBreed = animal1.energyToBreed;
        this.mapMode = animal1.mapMode;
        this.mutationMode = animal1.mutationMode;
        this.behaviourMode = animal1.behaviourMode;
        this.age = 0;
        this.kids = 0;
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

    public List<Integer> parentsGenome(Animal animal1, Animal animal2){
        int energySum = animal1.energy + animal2.energy;
        float x = (float) energySum / animal1.energy;
        float y = (float) genomeLength / x;
        int firstAnimalLength = (int) y;
        int secondAnimalLength = genomeLength - firstAnimalLength;
        System.out.println(firstAnimalLength);
        List<Integer> newGenome = new ArrayList<>();
        int chance = (int) (Math.random() * 1); // 0 oznacza lewo 1 oznacza prawo

        if (chance == 0){
            for (int i = 0; i < firstAnimalLength ; i++) {
                newGenome.add(animal1.genomeList.get(i));
            }
            for (int i = firstAnimalLength; i < genomeLength; i++){
                newGenome.add(animal2.genomeList.get(i));
            }
        }
        else if (chance == 1){
            for (int i = 0; i < secondAnimalLength; i++){
                newGenome.add(animal2.genomeList.get(i));
            }
            for (int i = secondAnimalLength; i < genomeLength ; i++) {
                newGenome.add(animal1.genomeList.get(i));
            }
        }
        return newGenome;
    }
    public List<Integer> getGenomeList(){
        return this.genomeList;
    }
    public MapDirection getOrientation(){
        return this.orientation;
    }
    public int getAnimalEnergy(){
        return this.energy;
    }

    @Override
    public String getPath(IMapElement object){
        Animal animal = (Animal) object;
        MapDirection orientation = animal.orientation;
        switch (orientation){
            case N -> {return "src/main/resources/turtleN.jpg";}
            case NE -> {return "src/main/resources/turtleNE.jpg";}
            case E -> {return "src/main/resources/turtleE.jpg";}
            case SE -> {return "src/main/resources/turtleSE.jpg";}
            case S -> {return "src/main/resources/turtleS.jpg";}
            case SW -> {return "src/main/resources/turtleSW.jpg";}
            case W -> {return "src/main/resources/turtleW.jpg";}
            case NW -> {return "src/main/resources/turtleNW.jpg";}
            default -> {return "src/main/resources/turtleW.jpg";}
        }
    }

    // getting genome
    public int getRotationNum(){
        actualGenomeIndex = (actualGenomeIndex+1) % this.genomeLength;
        return this.genomeList.get(actualGenomeIndex);
    }

    public void randomChangeGenomeIndex(){
        Random rand = new Random();
        actualGenomeIndex = rand.nextInt(this.genomeList.size());
    }


    // rotation
    public void rotate(boolean changeDirection){

        if (behaviourMode == 1){
            Random rand = new Random();
            int randNum = rand.nextInt(100) + 1;
            if (randNum > 80) randomChangeGenomeIndex(); // change actual genome index to random (in 20% cases)
        }

        int rotation = getRotationNum();

        if (mutationMode == 1){
            rotation = getRandomNumber();
        } else if (mutationMode == 2) {
            Random rand = new Random();
            int randNum = rand.nextInt(100) + 1;
            if (randNum > 50) rotation = Math.floorMod(rotation+1,8);
            else rotation = Math.floorMod(rotation-1, 8);
        }

        if(changeDirection) rotation = 4; // change direction when animal enters pole area

        for (int i = 1; i <= rotation; i++) {
            this.orientation = this.orientation.next();
        }

        System.out.println(this.actualGenomeIndex + " " + rotation);

    }

//    observers

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

    public void move() {


        Vector2d boundaries = this.map.getUpperRight();
        int width = boundaries.x;
        int height = boundaries.y;

        Vector2d newPosition = position;
        newPosition = position.add(orientation.toUnitVector());

        // Kula ziemska

        if (this.mapMode == 0){

            // change direction when entering pole!
            if(newPosition.y < 0 || newPosition.y > height){
                newPosition = position;
                rotate(true);

            }

            else {
                newPosition.y = Math.floorMod(newPosition.y, height+1);
                newPosition.x = Math.floorMod(newPosition.x, width+1);
                rotate(false);
            }

            changeEnergy(-1);

        }

        // Piekielny portal

        else {

            if(newPosition.x > width || newPosition.x < 0 || newPosition.y > height || newPosition.y < 0) {

                Random rand = new Random();
                int x = rand.nextInt(width);
                int y = rand.nextInt(height);
                newPosition = new Vector2d(x, y);

            }

            rotate(false);
            changeEnergy(-energyToBreed);

        }

        this.age += 1;

        this.positionChanged(this.position, newPosition, this);
        position = newPosition;

    }
    public boolean isDead() {
        return this.energy <= 0;
    }

    public void changeEnergy(int value) {
        this.energy = this.energy + value;
    }


}