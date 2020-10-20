package com.upt;

import java.util.ArrayList;

public class Grid
{
    private Tile[][] tiles;

    private int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int height;

    public Grid(int width, int height)
    {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                tiles[x][y] = new Tile(x, y, '#');
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
        if (x + 1 < width)
            neighbours.add(tiles[x + 1][y]);
        if (y - 1 >= 0)
            neighbours.add(tiles[x][y - 1]);
        if (y + 1 < height)
            neighbours.add(tiles[x][y + 1]);

        return neighbours;
    }

    public void displayGrid() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(tiles[i][j].getSymbol());
            }
            System.out.println();
        }
    }

    public Tile tileFromPosition(int x, int y)
    {
        return tiles[x][y];
    }
}
