package com.upt;

import java.util.concurrent.Semaphore;

public class Tile {

    private int posX, posY;

    private volatile ITileContent tileContent;

    public Semaphore getTileContentSemaphore() {
        return tileContentSemaphore;
    }

    private Semaphore tileContentSemaphore = new Semaphore(1); // acquire release

    public ITileContent getTileContent()
    {
        return tileContent;
    }

    public void setTileContent(ITileContent tileContent)
    {
        this.tileContent = tileContent;
    }

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

    public Tile (int x, int y, char symbol) {
        this(x, y);
        if (Unpassable.toUnpassableType(symbol) != null) {
            tileContent = new Unpassable(symbol);
        } else if (Resource.toResourceType(symbol) != null) {
            tileContent = new Resource(symbol, 10);
        }
    }

    @Override
    public String toString() {
        return tileContent.toString();
    }

    public char getSymbol() {
        if (tileContent == null)
            return '.';
        else
            return tileContent.getSymbol();
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
