package com.upt;

import java.util.ArrayList;

public class Player extends Thread {

    private boolean isAlive;

    private int[] resources = new int[4];

    private Hero hero;

    private ArrayList<Mercenary> mercenaries;

    private City city;

    private String playerColor;

    private int trainMercenaryFoodCost = 50;
    private int trainMercenaryGoldCost = 25;

    public Player(int heroPosX, int heroPosY, String playerColor)
    {
        hero = new Hero(heroPosX, heroPosY, this);
        isAlive = true;

        this.playerColor = playerColor;

        mercenaries = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            resources[i] = 100;
    }

    public void run()
    {
    }

    public void addResource(Resource.ResourceType resourceType, int value)
    {
        resources[resourceType.ordinal()] += value;
        // System.out.print(this.getName() + " " + resourceType + " value: " + resources[resourceType.ordinal()] + "\n");
    }

    public boolean trainMercenary()
    {
        var tiles = Grid.getInstance().getNeighbours(Grid.getInstance().tileFromPosition(city.getPosX(), city.getPosY()));

        ArrayList<Tile> emptyTiles = new ArrayList<>();
        for (Tile t : tiles)
        {
            if (t.getTileContent() == null)
                emptyTiles.add(t);
        }

        if (emptyTiles.size() > 0)
        {
            if (getResource(Resource.ResourceType.food) >= trainMercenaryFoodCost && getResource(Resource.ResourceType.gold) >= trainMercenaryGoldCost)
            {
                addResource(Resource.ResourceType.food, -trainMercenaryFoodCost);
                addResource(Resource.ResourceType.gold, -trainMercenaryGoldCost);

                Mercenary newMercenary = new Mercenary(emptyTiles.get(0).getPosX(), emptyTiles.get(0).getPosY(), this);
                emptyTiles.get(0).setTileContent(newMercenary);
                mercenaries.add(newMercenary);

                return true;
            }
            else
            {
                return  false;
            }
        }
        else
        {
            return false;
        }
    }

    public Hero getHero() {
        return hero;
    }

    public void setCity(City city) { this.city = city; }

    public int getResource(Resource.ResourceType resourceType)
    {
        return resources[resourceType.ordinal()];
    }

    public String getPlayerColor() { return playerColor; }
}
