package com.upt;

import java.util.ArrayList;

public class Hero extends Unit implements ITileContent
{
    private char symbol;

    public Hero(int x, int y, Player player) {
        super(x, y, player);
    }

    public Hero(int x, int y, Player player, char symbol) {
        super(x, y, player);
        this.symbol = symbol;
    }

    public ArrayList<IInteractable> getAdjacentInteractables()
    {
        var interactables = new ArrayList<IInteractable>();
        var neighbours = Grid.getInstance().getNeighbours(Grid.getInstance().tileFromPosition(posX, posY));

        for (Tile t : neighbours)
        {
            if (t.getTileContent() instanceof Resource)
                interactables.add((IInteractable)t.getTileContent());
        }

        return interactables;
    }


    public void collectResource(IInteractable interactable)
    {
        interactable.interact(this, IInteractable.ActionType.gather);
    }

    public char getSymbol() {
        return symbol;
    }

}
