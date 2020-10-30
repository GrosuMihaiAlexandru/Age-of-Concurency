package com.upt;

import java.util.ArrayList;

public class Player extends Thread {

    private boolean isAlive;

    private int[] resources = new int[4];

    private Hero hero;

    private ArrayList<Mercenary> mercenaries;

    public Player(int heroPosX, int heroPosY, char heroSymbol)
    {
        hero = new Hero(heroPosX, heroPosY, this, heroSymbol);
        isAlive = true;

        mercenaries = new ArrayList<>();
    }

    public void run()
    {
    }

    public void addResource(Resource.ResourceType resourceType, int value)
    {
        resources[resourceType.ordinal()] += value;
        // System.out.print(this.getName() + " " + resourceType + " value: " + resources[resourceType.ordinal()] + "\n");
    }

    public Hero getHero() {
        return hero;
    }

    public int getResource(Resource.ResourceType resourceType)
    {
        return resources[resourceType.ordinal()];
    }

}
