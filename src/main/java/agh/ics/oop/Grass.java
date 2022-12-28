package agh.ics.oop;

public class Grass extends AbstractMapElement {
    private static final String sign = "*";

    public Grass(Vector2d position) {
        super(position);
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return sign;
    }

    @Override
    public String getPath(IMapElement object) {
        return "src/main/resources/grass.png";
    }
}