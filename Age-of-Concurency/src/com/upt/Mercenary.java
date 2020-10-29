package com.upt;

import java.util.ArrayList;

public class Mercenary extends Unit implements ITileContent, IAttacker {

    private volatile int health;
    private int attack;

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
            if (t.getTileContent() instanceof IAttacker)
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

    // in caz ca se bat mercenarii intre ei metoda este synchronized
    @Override
    public void takeDamage(int damage)
    {
        health -= damage;
        if (health <= 0)
            onDeath();
    }

    public boolean isAlive()
    {
        return health > 0;
    }

    private void onDeath()
    {
        Grid.getInstance().tileFromPosition(posX, posY).setTileContent(null);
    }

    public int getHealth()
    {
        return health;
    }
}
