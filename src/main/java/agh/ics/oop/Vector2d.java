package agh.ics.oop;

import java.util.Objects;

public class Vector2d {

    int x;
    int y;

    public Vector2d(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }
    @Override
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }

    public boolean precedes(Vector2d other){
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other){
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    public Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    public Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.x), Math.max(this.y, other.y));
    }

    public Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.x), Math.min(this.y, other.y));
    }

    public Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Vector2d) {
            Vector2d otherN = (Vector2d)other;
            return this.x == otherN.x && this.y == otherN.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }

    public static Vector2d randomVector(int maxX, int maxY) {
        return new Vector2d(randomInt(maxX), randomInt(maxY));
    }

    private static int randomInt(int n) {
        return (int) (Math.random() * (n + 1));
    }
}
