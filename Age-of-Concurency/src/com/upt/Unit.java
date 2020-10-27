package com.upt;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Unit
{
    // The owner of the unit
    protected Player player;

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

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

    public Player getPlayer() {
        return player;
    }

    public void createLeeMatrix() {
        map = new PathfindingTile[Grid.getInstance().getWidth()][Grid.getInstance().getHeight()];
        map[posX][posY] = new PathfindingTile(posX, posY, -1, -1, 0);
        queue.clear(); // clear old stuff
        queue.add(Grid.getInstance().tileFromPosition(posX, posY)); // add current position of the unit

        Tile currentPos;
        ArrayList<Tile> neighborPositions;

        while (!queue.isEmpty()) {
            currentPos = queue.remove(); // get the current position from the queue

            neighborPositions = Grid.getInstance().getNeighbours(currentPos);

            for (Tile neighborPos : neighborPositions) {
                // System.out.println(neighborPos.getPosX() + ":" + neighborPos.getPosY() + " -> " + (map[neighborPos.getPosX()][neighborPos.getPosY()] == null ? "null" : "non null") + " -> " + (grid.tileFromPosition(neighborPos.getPosX(), neighborPos.getPosY()).tileContent == null ? "null" : "non null"));

                if (map[neighborPos.getPosX()][neighborPos.getPosY()] == null && Grid.getInstance().tileFromPosition(neighborPos.getPosX(), neighborPos.getPosY()).getTileContent() == null) // node hasn't been visited before and is walkable (has no tileContent)
                {
                    queue.add(neighborPos);
                    map[neighborPos.getPosX()][neighborPos.getPosY()] = new PathfindingTile(neighborPos.getPosX(), neighborPos.getPosY(), currentPos.getPosX(), currentPos.getPosY(), map[currentPos.getPosX()][currentPos.getPosY()].distance + 1);
                }
            }
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

            int x = map[currentX][currentY].parentX;
            int y = map[currentX][currentY].parentY;
            currentX = x;
            currentY = y;
        }
        Collections.reverse(path);

        return path;
    }

    public void printMap() {
        for (int i = 0; i < Grid.getInstance().getHeight(); i++) {
            for (int j = 0; j < Grid.getInstance().getWidth(); j++) {
                if (map[i][j] != null)
                {
                    if (map[i][j].distance < 10)
                        System.out.print(map[i][j].distance);
                    else
                        System.out.print("x");
                }
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public class PathfindingTile
    {
        public int selfX;
        public int selfY;

        public int parentX;
        public int parentY;
        public int distance;

        public PathfindingTile(int selfX, int selfY, int parentX, int parentY, int distance)
        {
            this.selfX = selfX;
            this.selfY = selfY;

            this.parentX = parentX;
            this.parentY = parentY;
            this.distance = distance;
        }
    }
}