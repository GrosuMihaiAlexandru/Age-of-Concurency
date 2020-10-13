package com.upt;

public abstract class Tile {

    private int x;
    private int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }
    int getY() {
        return y;
    }

}
