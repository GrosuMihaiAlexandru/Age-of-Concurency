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

    public void collectResource(Player player)
    {
        // probleme de concurenta
        resourceValue --;
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
    public String displayContent() {
        String display = "F";
        switch (resourceType)
        {
            case food:
                display = "F";
                break;
            case wood:
                display = "W";
                break;
            case gold:
                display = "G";
                break;
            case stone:
                display = "S";
                break;
        }

        return display;
    }
}
