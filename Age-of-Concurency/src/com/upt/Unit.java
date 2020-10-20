package com.upt;

import java.util.AbstractQueue;
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

    public void Move()
    {
        // pathfinding
    }

    public void getAdjacentInteractables()
    {
        var neighbours = grid.getNeighbours(grid.tileFromPosition(posX, posY));

        for (Tile t : neighbours)
        {
            if (t.tileContent instanceof Resource)
                interactables.add((IInteractable)t);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void CreateLeeMatrix() {
        map = new PathfindingTile[grid.getGridSizeX()][grid.getGridSizeY()];
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

    class PathfindingTile
    {
        int parentX;
        int parentY;
        int distance;

        public PathfindingTile(int parentX, int parentY, int distance)
        {
            this.parentX = parentX;
            this.parentY = parentY;
            this.distance = distance;
        }
    }
}