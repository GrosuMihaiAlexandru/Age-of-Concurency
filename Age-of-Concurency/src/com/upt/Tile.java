package com.upt;

public class Tile {

    public enum TileType { Empty, Resource, Character, City,   }

    private TileType tileType;
    private Coord coord;

    public Tile(Coord coord, TileType tileType)
    {
        this.coord = coord;
        this.tileType = tileType;
    }

    public Resource getResource()
    {

    }

    public Coord getCoord()
    {
        return coord;
    }
}
