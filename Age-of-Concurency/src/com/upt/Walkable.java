package com.upt;

public class Walkable extends Tile {

    public Walkable(int x, int y) {
        super(x, y);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public String toString() {
        return "#";
    }
}
