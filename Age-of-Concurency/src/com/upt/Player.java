package com.upt;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Player extends Thread implements ITaskFinishedCallback {

    private boolean isAlive;

    private int[] resources = new int[4];

    private Hero hero;

    private ArrayList<Mercenary> mercenaries;

    private City city;

    private String playerColor;

    private int trainMercenaryFoodCost = 50;
    private int trainMercenaryGoldCost = 25;

    private ArrayDeque<Command> commandsQueue = new ArrayDeque<Command>();

    public Player(int heroPosX, int heroPosY, String playerColor)
    {
        hero = new Hero(heroPosX, heroPosY, this);
        Grid.getInstance().tileFromPosition(heroPosX, heroPosY).setTileContent(hero);
        isAlive = true;

        this.playerColor = playerColor;

        mercenaries = new ArrayList<>();

        for (int i = 0; i < 4; i++)
            resources[i] = 100;
    }

    public synchronized void run()
    {
        while(!commandsQueue.isEmpty())
        {
            Command command = commandsQueue.poll();

            switch (command.commandStr)
            {
                case "collect":
                    System.out.println(playerColor + "Started collect!\n" + Grid.ANSI_RESET);
                    hero.startCollectingResource(command.resourceType, command.amount, this);
                    try {
                        // System.out.println("Waiting\n");
                        wait();
                    } catch (Exception e) {}
                    break;

                case "move":
                    System.out.println(playerColor + "Started move to " + command.x + ", " + command.y + "!\n" + Grid.ANSI_RESET);
                    hero.moveToDestination(command.x, command.y, this);
                    // System.out.println(playerColor);
                    // hero.printMap();
                    // System.out.println(Grid.ANSI_RESET);
                    try {
                        // System.out.println("Waiting\n");
                        wait();
                    } catch (Exception e) {}
                    break;

                case "train":
                    break;

                default:
                    System.out.println("Unrecognized command will be ignored.");
                    continue;
            }
        }
    }

    public void addCommand(Command command)
    {
        commandsQueue.add(command);
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

    @Override
    public synchronized void onFinish() {
        // System.out.println("onFinish");
        this.notify();
    }

    @Override
    public synchronized void onFail() {
        // System.out.println("onFail");
        notify();
    }
}
