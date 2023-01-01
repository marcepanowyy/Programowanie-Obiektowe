package agh.ics.oop;

import java.util.*;

public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {

    protected final MapVisualizer mapVisualizer;
    protected final Map<Vector2d, IMapElement> mapElements = new HashMap<>();
    public final int plantGrowthMode;
    protected Set<IMapElement> newMapElements = new HashSet<>();

    //map elements
    public Map<Vector2d, Grass> grass = new HashMap<>();
    public Map<Vector2d, LinkedList<Animal>> animals = new HashMap<>();
    public Map<Vector2d, Integer> deadAnimals = new HashMap<>();
    public LinkedList<Vector2d> deadPlaces = new LinkedList<>();
    public LinkedList<Animal> animalsList;
    public LinkedList<Grass> grassList;
    public int mode = 1;

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
    private int startAnimalsEnergy;
    //copulation
    private final int energyUsedForBreeding;
    private final int minEnergyToBreed = 1;
    public int grassCount;
    public int animalsAliveCount;
    public int animalsDeadCount;


    public AbstractWorldMap(int width, int height, int grassProfit, int energyUsedForBreeding, int startAnimalsEnergy, int plantGrowthMode) {
        this.mapVisualizer = new MapVisualizer(this);
        this.startAnimalsEnergy = startAnimalsEnergy;
        this.grassList = new LinkedList<>();
        this.animalsList = new LinkedList<>();
        this.energyUsedForBreeding = energyUsedForBreeding;
        this.grassProfit = grassProfit;
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width, height);
        this.width = width;
        this.height = height;
        this.middleX = width / 2;
        this.middleY = height / 2;
        this.equatorFields = Math.round((height * width) * 0.2);
        this.plantGrowthMode = plantGrowthMode;
        this.grassCount = 0;
        this.animalsAliveCount = 0;
        this.animalsDeadCount = 0;
        getEquatorWidth();
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

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, Object a){
        removeAnimal((Animal) a, oldPosition);
        addAnimal((Animal) a, newPosition);
    }

    @Override
    public void place(IMapElement element) throws IllegalArgumentException {

        Vector2d position = element.getPosition();

//        if (element instanceof Grass) {
//            if (grass.get(position) == null)
//                grass.put(position, (Grass) element);
//            grassList.add((Grass) element);
//        }
        if (element instanceof Animal) {
            addAnimal((Animal) element, position);
            animalsList.add((Animal) element);
            element.addObserver(this);
            animalsAliveCount += 1;
        }

    }

    public void removeDeadAnimals() {
        LinkedList<Animal> l = getAnimals();
        for (int i = 0; i < l.size(); i++) {
            Animal a = animalsList.get(i);
            if (a.isDead()) {
                addDeadAnimals(a.getPosition());
                removeAnimal(a, a.getPosition());
                a.removeObserver(this);
                animalsList.remove(a);
                animalsAliveCount -= 1;
                animalsDeadCount += 1;
            }
        }
    }

    public void addDeadAnimals(Vector2d position){
        if (deadAnimals.get(position) == null){
            deadAnimals.put(position, 1);
            deadPlaces.add(position);
        }
        else{
            deadAnimals.put(position, deadAnimals.get(position) + 1);
        }
    }

    public boolean addAnimal(Animal a, Vector2d p) {
        if (a == null) return false;
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

    @Override
    public Object objectAt(Vector2d position) {
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

    public void spawnGrass(int num){
        if (plantGrowthMode == 0) {
            for (int i = 0; i < num; i++) {
                int chance = (int) (Math.random() * 10);
                int counter = 0;
                while (true) {
                    counter++;
                    int randomX;
                    int randomY;
                    if (chance <= 8) {
                        randomX = (int) ((Math.random() * (equatorEndX - equatorStartX)) + equatorStartX);
                        randomY = (int) ((Math.random() * (equatorEndY - equatorStartY)) + equatorStartY);
                    } else {
                        randomX = (int) (Math.random() * (width+1));
                        randomY = (int) (Math.random() * (height+1));
                    }
                    Vector2d randomPos = new Vector2d(randomX, randomY);
                    if (objectAt(randomPos) == null) {
                        grass.put(randomPos, new Grass(randomPos));
                        break;
                    }
                    if (counter > equatorFields) {
                        break;
                    }
                }
                grassCount += 1;
            }
        }
        else {
            for (int i = 0; i < num; i++) {
                int chance = (int) (Math.random() * 10);
                int counter = 0;
                while (true) {
                    counter++;
                    int randomX;
                    int randomY;
                    if(deadPlaces.size() > 0 && chance > 8) {
                        int randomIndex = (int) (Math.random() * deadPlaces.size());
                        Vector2d vector2d = deadPlaces.get(randomIndex);
                        randomX = vector2d.x;
                        randomY = vector2d.y;
                    }
                    else {
                        randomX = (int) (Math.random() * (width+1));
                        randomY = (int) (Math.random() * (height+1));
                    }
                    Vector2d randomPos = new Vector2d(randomX, randomY);
                    if (objectAt(randomPos) == null) {
                        grass.put(randomPos, new Grass(randomPos));
                        break;
                    }
                    if (counter > equatorFields) {
                        break;
                    }
                }
                grassCount += 1;
            }

        }
    }

    public void getEquatorWidth(){
        long equatorLength = 1;
        long equatorHeight = 1;
        for (int i = width - 1; i > 0; i--){
            if (width % i == 0){
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

    public void eatGrassOnPosition(Vector2d position){
        LinkedList<Animal> l = animals.get(position);
        if (grass.get(position) == null){
            return;
        }
        else if(l.size() == 1){
            l.getFirst().changeEnergy(grassProfit);
            grass.remove(position);
            grassCount -= 1;
        }
        else {
            Collections.sort(l, new Comparator<Animal>() {
                public int compare(Animal a1, Animal a2) {
                    return a2.kids - a1.kids;
                }
            });
            Collections.sort(l, new Comparator<Animal>() {
                public int compare(Animal a1, Animal a2) {
                    return a2.age - a1.age;
                }
            });
            Collections.sort(l, new Comparator<Animal>() {
                public int compare(Animal a1, Animal a2) {
                    return a2.energy - a1.energy;
                }
            });
            l.getFirst().changeEnergy(grassProfit);
            grass.remove(position);
            grassCount -= 1;
        }
    }

    public void feedThemALL(){
        animals.forEach((key, value) -> eatGrassOnPosition(key));
    }

    public void copulate(Vector2d position){
        LinkedList<Animal> l = animals.get(position);
        if (l != null && l.size() > 1){
            Collections.sort(l, new Comparator<Animal>() {
                public int compare(Animal a1, Animal a2) {
                    return a2.energy - a1.energy;
                }
            });
            Animal animal1 = l.getFirst();
            Animal animal2 = l.get(1);
            if (animal1.energy >= minEnergyToBreed && animal2.energy >= minEnergyToBreed){
                Animal animal = new Animal(animal1, animal2, animal1.getPosition());
                animal1.changeEnergy(-energyUsedForBreeding);
                animal2.changeEnergy(-energyUsedForBreeding);
                animal1.kids++;
                animal2.kids++;
                place(animal);
            }

        }
    }

    public void copulateThemAll(){
        animals.forEach((key, value) -> copulate(key));
    }

}