package com.upt;

public class Resource implements ITileContent, IInteractable
{
    public enum ResourceType { food, wood, gold, stone}


    private ResourceType resourceType;
    private int resourceValue;

    public Resource(ResourceType resourceType, int value)
    {
        this.resourceType = resourceType;
        this.resourceValue = value;
    }

    public Resource (char resourceSymbol, int value) {
        this.resourceType = toResourceType(resourceSymbol);
        this.resourceValue = value;
    }

    public synchronized void collectResource(Player player)
    {
        if (resourceValue <= 0)
            return;

        // probleme de concurenta
        resourceValue --;

        System.out.print("resource left: " + resourceValue + "\n");
        player.addResource(resourceType, 1);
    }

    public ResourceType getResourceType()
    {
        return resourceType;
    }

    @Override
    public void interact(Hero hero, ActionType actionType) {

        collectResource(hero.getPlayer());
    }

    @Override
    public char getSymbol() {
        return toCharacter(resourceType);
    }

    private static char toCharacter(ResourceType type) {

        char c = 'E';

        switch (type)
        {
            case food:
                c = 'F';
                break;
            case wood:
                c = 'W';
                break;
            case gold:
                c = 'G';
                break;
            case stone:
                c = 'S';
                break;
        }

        return c;
    }

    public static ResourceType toResourceType(char character) {

        ResourceType type = null;

        switch (character) {
            case 'F':
                type = ResourceType.food;
                break;
            case 'W':
                type = ResourceType.wood;
                break;
            case 'G':
                type = ResourceType.gold;
                break;
            case 'S':
                type = ResourceType.stone;
                break;
        }

        return type;

    }
}
