package com.upt;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;

public abstract class Unit
{
    // The owner of the unit
    protected Player player;
    protected Grid grid;

    protected int posX;
    protected int posY;

    private ArrayDeque<Tile> queue = new ArrayDeque<Tile>();
    private PathfindingTile[][] map;

    // List of current available interactables
    protected ArrayList<IInteractable> interactables = new ArrayList<>();

    public Unit(int x, int y, Player player)
    {
        this.posX = x;
        this.posY = y;
        this.player = player;
    }

    public void Move()
    {
        // pathfinding
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Player getPlayer() {
        return player;
    }

    public void createLeeMatrix() {
        map = new PathfindingTile[grid.getWidth()][grid.getHeight()];
        map[posX][posY] = new PathfindingTile(posX, posY, 0);
        queue.clear(); // clear old stuff
        queue.add(grid.tileFromPosition(posX, posY)); // add current position of the unit

        Tile currentPos;
        ArrayList<Tile> neighborPositions;

        while (!queue.isEmpty()) {
            currentPos = queue.remove(); // get the current position from the queue

            neighborPositions = grid.getNeighbours(currentPos);

            for (Tile neighborPos : neighborPositions) {
                if (map[neighborPos.getPosX()][neighborPos.getPosY()] == null && grid.tileFromPosition(neighborPos.getPosX(), neighborPos.getPosY()).tileContent == null) // node hasn't been visited before and is walkable (has no tileContent)
                {
                    queue.add(neighborPos);
                    map[neighborPos.getPosX()][neighborPos.getPosY()] = new PathfindingTile(neighborPos.getPosX(), neighborPos.getPosY(), map[currentPos.getPosX()][currentPos.getPosY()].distance + 1);
                }
            }

            queue.remove();
        }
    }

    public ArrayList<PathfindingTile> getPathToDestination(int destX, int destY)
    {
        int currentX = destX;
        int currentY = destY;
        ArrayList<PathfindingTile> path = new ArrayList<>();

        while(currentX != posX || currentY != posY)
        {
            path.add(map[currentX][currentY]);
            currentX = map[currentX][currentY].parentX;
            currentY = map[currentX][currentY].parentY;
        }

        return path;
    }

    public void printMap() {
        for (int i = 0; i < grid.getWidth(); i++) {
            for (int j = 0; j < grid.getHeight(); j++) {
                if (map[i][j] != null)
                    System.out.print(map[i][j].distance);
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public class PathfindingTile
    {
        public int parentX;
        public int parentY;
        public int distance;

        public PathfindingTile(int parentX, int parentY, int distance)
        {
            this.parentX = parentX;
            this.parentY = parentY;
            this.distance = distance;
        }
    }
}