package agh.ics.oop;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMapElement implements IMapElement {

    protected Vector2d position;
    protected final Set<IPositionChangeObserver> observers = new HashSet<>(){};


    public AbstractMapElement(Vector2d position){
        this.position = position;
    }

    @Override
    public Vector2d getPosition(){
        return position;
    }

    @Override
    public void remove(){
        for (IPositionChangeObserver observer: observers){
            observer.positionChanged(position, null);
        }
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer){
        observers.remove(observer);
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }




}
