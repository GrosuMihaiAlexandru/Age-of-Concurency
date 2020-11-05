package com.upt;

public class Command {
    public int x, y, amount;
    String commandStr;
    Resource.ResourceType resourceType;

    public Command(String commandStr, int x, int y, int amount, Resource.ResourceType resourceType)
    {
        this.x = x;
        this.y = y;
        this.amount = amount;
        this.commandStr = commandStr;
        this.resourceType = resourceType;
    }
}
