package agh.ics.oop;

import java.util.LinkedList;
import java.util.Set;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that Vector2d and MoveDirection classes are defined.
 *
 * @author apohllo
 *
 */
public interface IWorldMap {
    /**
     * Indicate if any object can move to the given position.
     *
     * @param position
     *            The position checked for the movement possibility.
     * @return True if the object can move to that position.
     */
//    boolean canMoveTo(Vector2d position);

    /**
     * Place an animal on the map.
     *
     * @param element
     *            The animal to place on the map.
     * @return True if the animal was placed. The animal cannot be placed if the map is already occupied.
     */
    void place(IMapElement element);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position
     *            Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(Vector2d position);

    /**
     * Return an object at a given position.
     *
     * @param position
     *            The position of the object.
     * @return Object or null if the position is not occupied.
     */

    Object objectAt(Vector2d position);

    Vector2d getLowerLeft();

    /**
     * Return an object at a given position.
     *
     * @return Vector representing coordinates of the lower left map corner
     */
    Vector2d getUpperRight();

    /**
     * Return a set of newly added map elements. After returning
     * currently new elements, the set of new elements will be replaced
     * by the empty set.
     *
     * @return Set of map objects pairs
     */
    Set<IMapElement> getNewMapElements();

    /**
     * Return a list of newly added map elements. After returning
     * currently new elements, the list of new elements will be cleared.
     *              The element newly added to the ma for the firs time.
     */
    LinkedList<Animal> getAnimals();
}