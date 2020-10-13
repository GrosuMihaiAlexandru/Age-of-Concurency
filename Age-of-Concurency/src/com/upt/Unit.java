package com.upt;

import java.util.ArrayList;

public abstract class Unit
{
    // The owner of the unit
    protected Player player;
    protected Grid grid;

    protected int posX;
    protected int posY;

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
}
