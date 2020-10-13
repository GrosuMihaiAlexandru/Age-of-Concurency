package com.upt;

import java.util.ArrayList;

public class Grid
{
    private Tile[][] tiles;

    private int gridSizeX, gridSizeY;

    public Grid(int width, int height)
    {
        gridSizeX = width;
        gridSizeY = height;

        tiles = new Tile[width][height];
        for (int y = 0; y < gridSizeY; y++)
        {
            for (int x = 0; x < gridSizeX; x++)
            {
                tiles[x][y] = new Tile(x, y);
            }
        }
    }

    public ArrayList<Tile> getNeighbours(Tile tile)
    {
        ArrayList<Tile> neighbours = new ArrayList<>();

        int x = tile.getPosX();
        int y = tile.getPosY();

        if (x - 1 >= 0)
            neighbours.add(tiles[x - 1][y]);
        if (x + 1 < gridSizeX)
            neighbours.add(tiles[x + 1][y]);
        if (y - 1 >= 0)
            neighbours.add(tiles[x][y - 1]);
        if (y + 1 < gridSizeY)
            neighbours.add(tiles[x][y + 1]);

        return neighbours;
    }

    public Tile tileFromPosition(int x, int y)
    {
        return tiles[x][y];
    }
}
