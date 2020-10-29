package com.upt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Grid {
    private Tile[][] tiles;

    private int width;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int height;

    private static Grid grid;

    private static String gridPath = "scenarios\\default.txt";

    public static void setGridPath(String gridPath)
    {
        Grid.gridPath = gridPath;
    }

    public static Grid getInstance() {
        if (grid == null) {
            grid = new Grid(gridPath);
        }

        return grid;
    }

    private Grid(String pathname) {

        ArrayList<String> lines = new ArrayList<String>();
        Scanner input = null;

        try {
            input = new Scanner(new File(pathname)).useDelimiter("\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            while(colReader.hasNext())
            {
                lines.add(colReader.nextLine());
            }
        }

        this.height = lines.size();
        this.width = lines.get(0).length();
        tiles = new Tile[width][height];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x][y] = new Tile(x, y, lines.get(y).charAt(x));
            }
        }
    }

    private Grid(int width, int height)
    {
        this.width = width;
        this.height = height;

        tiles = new Tile[width][height];
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                tiles[x][y] = new Tile(x, y, ' ');
            }
        }
    }

    public boolean moveTileContent(int tilePosX, int tilePosY, int destX, int destY, ITileContent content)
    {
        Tile currentTile = tileFromPosition(tilePosX, tilePosY);
        Tile nextTile = tileFromPosition(destX, destY);

        try {
            if (tilePosX < destX) {
                currentTile.getTileContentSemaphore().acquire();
                nextTile.getTileContentSemaphore().acquire();
            } else if (destX < tilePosX) {
                nextTile.getTileContentSemaphore().acquire();
                currentTile.getTileContentSemaphore().acquire();
            } else if (tilePosY < destY) {
                currentTile.getTileContentSemaphore().acquire();
                nextTile.getTileContentSemaphore().acquire();
            } else if (destY < tilePosY) {
                nextTile.getTileContentSemaphore().acquire();
                currentTile.getTileContentSemaphore().acquire();
            } else {
                System.out.println("Sum Ting Wong");
                return false;
            }
        } catch (Exception e) { }

        boolean successful = false;

        if (nextTile.getTileContent() == null)
        {
            // cell is empty, start moving
            currentTile.setTileContent(null);
            nextTile.setTileContent(content);

            successful = true;
        }

        currentTile.getTileContentSemaphore().release();
        nextTile.getTileContentSemaphore().release();

        return successful;
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
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(tiles[x][y].getSymbol());
            }
            System.out.println();
        }
    }

    public Tile tileFromPosition(int x, int y)
    {
        return tiles[x][y];
    }
}
