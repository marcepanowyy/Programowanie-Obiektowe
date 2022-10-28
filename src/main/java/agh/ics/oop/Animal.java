package agh.ics.oop;

public class Animal {

    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;
    private final IWorldMap map;


    public String toString(){
        return switch(this.orientation){
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public Animal(IWorldMap map){
        this.map = map;
        position = new Vector2d(2, 2);

    }
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        position = initialPosition;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){

        Vector2d newPosition;

        switch (direction) {

            case RIGHT -> orientation = orientation.next();

            case LEFT -> orientation = orientation.previous();

            case FORWARD -> {
                newPosition = position.add(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }

            case BACKWARD -> {
                newPosition = position.subtract(orientation.toUnitVector());
                if (map.canMoveTo(newPosition)){
                    position = newPosition;
                }
            }
        }
    }

}
