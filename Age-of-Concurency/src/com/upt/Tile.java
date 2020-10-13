package com.upt;

public class Tile {

    private int posX, posY;

    public ITileContent tileContent;

    public Tile (int x, int y)
    {
        this.posX = x;
        this.posY = y;
    }

    public Tile (int x, int y, ITileContent tileContent)
    {
        this(x,y);
        this.tileContent = tileContent;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
