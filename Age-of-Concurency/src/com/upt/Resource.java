package com.upt;

public class Resource implements ITile
{
    public enum ResourceType { food, wood, gold, stone}

    private Coord coord;
    private ResourceType resourceType;
    private int resourceValue;

    public Resource(ResourceType resourceType, int value, Coord coord)
    {
        this.resourceType = resourceType;
        this.resourceValue = value;
        this.coord = coord;
    }

    public void collectResource()
    {
        // probleme de concurenta
        resourceValue --;
    }

    public ResourceType getResourceType()
    {
        return resourceType;
    }

    @Override
    public Coord getCoord() {
        return coord;
    }
}
