package agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();
    String toString();

    void remove();

    void addObserver(IPositionChangeObserver observer);
    void removeObserver(IPositionChangeObserver observer);
}
