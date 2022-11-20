package agh.ics.oop;

import java.util.Comparator;

public class YComparator implements Comparator<IMapElement> {
    @Override
    public int compare(IMapElement e1, IMapElement e2) {
        Vector2d e1Pos = e1.getPosition();
        Vector2d e2Pos = e2.getPosition();
        return e1Pos.y != e2Pos.y ? e1Pos.y - e2Pos.y : e1Pos.x - e2Pos.x;
    }
}