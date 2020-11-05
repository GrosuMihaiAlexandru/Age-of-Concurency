package com.upt;

public class Resource implements ITileContent, IInteractable
{
    public enum ResourceType { food, wood, gold, stone}

    private int posX;

    private int posY;

    private ResourceType resourceType;
    private int resourceValue;

    public Resource (int posX, int posY, char resourceSymbol, int value) {
        this.posX = posX;
        this.posY = posY;
        this.resourceType = toResourceType(resourceSymbol);
        this.resourceValue = value;
    }

    public synchronized void collectResource(Player player)
    {
        // probleme de concurenta
        resourceValue --;

        if (resourceValue <= 0)
        {
            System.out.println("Resource value on death: " + resourceValue);
            onDeath();
            return;
        }

        System.out.print("resource left: " + resourceValue + "\n");
        player.addResource(resourceType, 1);
    }

    private void onDeath()
    {
        Grid.getInstance().tileFromPosition(getPosX(), getPosY()).setTileContent(null);
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
    public String getSymbol() {
        return toCharacter(resourceType) + "";
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
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
