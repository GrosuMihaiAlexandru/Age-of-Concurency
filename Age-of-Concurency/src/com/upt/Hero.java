package com.upt;

import java.util.ArrayList;

public class Hero extends Unit
{

    public Hero(int x, int y, Player player)
    {
        super(x, y, player);
    }

    private ArrayList<IInteractable> getAdjacentResouces()
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

    public void startCollectingResource(Resource.ResourceType resourceType, int amount, ITaskFinishedCallback callback)
    {
        int initialResource = player.getResource(resourceType);

        new Thread()
        {
            public void run()
            {
                while (player.getResource(resourceType) - initialResource < amount)
                {
                    var interactables = getAdjacentResouces();

                    if (interactables.size() > 0)
                    {
                        System.out.print(getName() + " Collect resource");
                        System.out.println();

                        interactables.get(0).interact(Hero.this, IInteractable.ActionType.gather);
                    }
                    else
                    {
                        callback.onFail();
                        return;
                    }

                    try
                    {
                        sleep(1000);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                callback.onFinish();
            }
        }.start();
    }

    @Override
    public String getSymbol()
    {
        return player.getPlayerColor() + "H" + Grid.ANSI_RESET;
    }
}
