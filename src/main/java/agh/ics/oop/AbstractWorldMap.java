package agh.ics.oop;

import java.util.*;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    protected Set<IMapElement> newMapElements = new HashSet<>();

    //map elements
    public Map<Vector2d, Grass> grass = new HashMap<>();
    public Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    public LinkedList<Animal> animalsList;
    public LinkedList<Grass> grassList;

    // map size

    private final Vector2d upperRight;
    private final Vector2d lowerLeft;
    public int width;
    public int height;
    private int middleX;
    private int middleY;
    private long equatorFields;
    private long equatorStartY;
    private long equatorStartX;
    private long equatorEndX;
    private long equatorEndY;

    //eating
    private int grassProfit;
    private int dayCost;
    private int startAnimalsEnergy;
    //copulation
    private final int energyLimitToCopulation;


//    public AbstractWorldMap() {
//        this.mapVisualizer = new MapVisualizer(this);
//    }

    public AbstractWorldMap(int width, int height, int grassProfit, int dayCost, int energyLimitToCopulation, int startAnimalsEnergy) {
        this.mapVisualizer = new MapVisualizer(this);
        this.startAnimalsEnergy = startAnimalsEnergy;
        this.grassList = new LinkedList<>();
        this.animalsList = new LinkedList<>();
        this.energyLimitToCopulation = energyLimitToCopulation;
        this.grassProfit = grassProfit;
        this.dayCost = (-1) * dayCost;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
        this.width = width;
        this.height = height;
        this.middleX = width / 2;
        this.middleY = height / 2;
        this.equatorFields = Math.round((height * width) * 0.2);
        getEquatorWidth();
        System.out.println(equatorFields);
    }


    public String toString() {
        return mapVisualizer.draw(lowerLeft, upperRight);
    }

    public Vector2d getLowerLeft() {
        return lowerLeft;
    }

    public Vector2d getUpperRight() {
        return upperRight;
    }

    @Override
    public Set<IMapElement> getNewMapElements(){
        Set <IMapElement> returnedSet = newMapElements;
        newMapElements = new HashSet<>();
        return returnedSet;
    }


//    public void placeNewMapElement(IMapElement element){
//        newMapElements.add(element);
//        place(element);
//    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object a){

        removeAnimal((Animal) a, oldPosition);
        addAnimal((Animal) a, newPosition);
    }

    @Override
    public void place(IMapElement element) throws IllegalArgumentException {

        Vector2d position = element.getPosition();

        if (element instanceof Grass) {
            if (grass.get(position) == null)
                grass.put(position, (Grass) element);
            grassList.add((Grass) element);
        }
        if (element instanceof Animal) {
            addAnimal((Animal) element, position);
            animalsList.add((Animal) element);
            element.addObserver(this);
        }

    }

    public void removeDeadAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            Animal a = animalsList.get(i);
            if (a.isDead()) {
                removeAnimal(a, a.getPosition());
                a.removeObserver(this);
                animalsList.remove(a);
            }
        }
    }

    public boolean addAnimal(Animal a, Vector2d p) {
        System.out.println(animalsList);
        if (a == null) return false;
//        Vector2d pos = a.getPosition();
        LinkedList<Animal> l = animals.get(p);
        if (l == null) {
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(a);
            animals.put(p, tmp);

        } else if (l != null) {
            l.add(a);
        }
        return true;

    }

//    @Override
//    public void place(IMapElement element) throws IllegalArgumentException {
//        Vector2d position = element.getPosition();
//        if ((!isOccupied(position) || element instanceof Animal) && canMoveTo(position)) {
//            mapElements.put(position, element);
//
//        } else throw new IllegalArgumentException(element + " (" + element.getClass() + ") cannot be placed on position" + position);
//    }



//    @Override
//    public IMapElement objectAt(Vector2d position) {
//        return mapElements.get(position);
//    }

    @Override
    public Object objectAt(Vector2d position) {
//        Vector2d position = toNoBoundedPosition(position2);
        LinkedList<Animal> l = animals.get(position);
        if (l == null) return grass.get(position);
        else if (l.size() == 0) return grass.get(position);
        else return l.getFirst();
    }


    public boolean isOccupied(Vector2d position) {
        return mapElements.containsKey(position);
    }

    public IMapElement remove(Vector2d position) {
        return mapElements.remove(position);
    }

//    protected abstract boolean isOnMap(Vector2d position);

    private boolean removeAnimal(Animal a, Vector2d oldPosition) {
        LinkedList<Animal> l = animals.get(oldPosition);
        if (l == null)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " -> " + oldPosition + " already not exist in the map");
        else if (l.size() == 0)
            throw new IllegalArgumentException("Animal" + a.getPosition() + " already not exist in the map empty list");
        else {
            l.remove(a);
            if (l.size() == 0) {
                animals.remove(oldPosition);
            }
        }
        return true;
    }

    public void nextDay() {
        for (LinkedList<Animal> animalList : animals.values()) {
            if (animalList != null) {
                if (animalList.size() > 0) {
                    for (Animal a : animalList) {
                        a.changeEnergy(dayCost);
                    }
                }
            }
        }
    }

    public void spawnGrass(int num){
        for (int i = 0; i < num; i++) {
            int chance = (int) (Math.random() * 10);
            int counter = 0;
            while (true) {
                System.out.println(chance);
                counter++;
                int randomX;
                int randomY;
                if (chance <= 8){
                    randomX = (int) ((Math.random() * (equatorEndX - equatorStartX)) + equatorStartX);
                    randomY = (int) ((Math.random() * (equatorEndY - equatorStartY)) + equatorStartY);
                }
                else {
                    randomX = (int) (Math.random() * width);
                    randomY = (int) (Math.random() * height);
                }
                Vector2d randomPos = new Vector2d(randomX, randomY);
                if (objectAt(randomPos) == null) {
                    grass.put(randomPos , new Grass(randomPos));
                    break;
                }
                if (counter > equatorFields){
                    break;
                }
            }
        }
    }

    public void getEquatorWidth(){
        long equatorLength = 1;
        long equatorHeight = 1;
        for (int i = width - 1; i > 0; i--){
            if (equatorFields % i == 0){
                equatorLength = i;
                equatorHeight = equatorFields / equatorLength;
                break;
            }
        }
        this.equatorStartX = (width - equatorLength) / 2;
        this.equatorEndX = equatorStartX + equatorLength;
        this.equatorStartY = (height - equatorHeight) / 2;
        this.equatorEndY = equatorStartY + equatorHeight;
    }






    public LinkedList<Animal> getAnimals() {
        return animalsList;
    }

    public LinkedList<Grass> getGrass() {
        return grassList;
    }

    public void eatGrass(){

    }


}