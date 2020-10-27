package com.upt;

import java.util.ArrayList;

public class Mercenary extends Unit implements ITileContent, IAttacker {

    private float health;
    private float attack;

    public Mercenary(int x, int y, Player player) {
        super(x, y, player);

        health = 100;
        attack = 50;
    }

    public ArrayList<IAttacker> getAdjacentAttackers()
    {
        var attackers = new ArrayList<IAttacker>();
        var neighbours = Grid.getInstance().getNeighbours(Grid.getInstance().tileFromPosition(posX, posY));

        for (Tile t : neighbours)
        {
            if (t.getTileContent() instanceof Resource)
                attackers.add((IAttacker) t.getTileContent());
        }

        return attackers;
    }

    public char getSymbol() {
        return 'M';
    }

    @Override
    public void attack(IAttacker attacker)
    {
        attacker.takeDamage(this.attack);
    }

    @Override
    public void takeDamage(float damage)
    {
        // probleme de concuren ta
        if (health > 0)
        {
            health -= damage;
        }
    }
}
