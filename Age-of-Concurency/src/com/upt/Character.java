package com.upt;

public class Character implements ITile
{
    private Player player;
    private Coord coord;

    public Character(Player player, Coord coord)
    {
        this.player = player;
        this.coord = coord;
    }

    @Override
    public Coord getCoord() {
        return coord;
    }
}
