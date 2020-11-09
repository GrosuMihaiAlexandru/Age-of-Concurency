package com.upt;

import java.util.ArrayList;

public class Mercenary extends Unit implements IAttacker {

    private volatile int health;
    private int attack;

    private boolean stopAttacking = false;

    public int getNo() {
        return no;
    }

    private int no;
    private static int allMercenaries = 0;

    public Mercenary(int x, int y, Player player) {
        super(x, y, player);

        health = 200;
        attack = 50;
        no = allMercenaries;    // shouldn't this be atomic?
        allMercenaries++;
    }

    public ArrayList<IAttacker> getAdjacentAttackers()
    {
        var attackers = new ArrayList<IAttacker>();
        var neighbours = Grid.getInstance().getNeighbours(Grid.getInstance().tileFromPosition(posX, posY));

        for (Tile t : neighbours)
        {
            if (t.getTileContent() instanceof IAttacker && ((IAttacker)t.getTileContent()).getPlayer() != player)
                attackers.add((IAttacker) t.getTileContent());
        }

        return attackers;
    }

    public String getSymbol() {
        return player.getPlayerColor() + "M" + Grid.ANSI_RESET;
    }

    public void startAttackingAdjacentEnemy(ITaskFinishedCallback callback)
    {
        stopAttacking = false;
        new Thread(() -> {

            int counter = 1;
            while (isAlive() && !stopAttacking)
            {
                var attackables = getAdjacentAttackers();

                if (attackables.size() > 0)
                {
                    counter++;
                    attack(attackables.get(0));
                    System.out.println(player.getPlayerColor() + "m" + no + " attack no: " + counter + ": " + ((City)attackables.get(0)).getPlayer().getPlayerColor() + " City HP left: " + ((City)attackables.get(0)).getCurrentHealth() + Grid.ANSI_RESET);}
                else
                {
                    // Dc orasul o fost distrus
                    callback.onFail();
                    return;
                }

                try {
                    Thread.sleep((1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            callback.onFinish();
        }).start();
    }

    public void stopAttacking()
    {
        stopAttacking = true;
    }

    @Override
    public void attack(IAttacker attacker)
    {
        attacker.takeDamage(this.attack);
    }

    // in caz ca se bat mercenarii intre ei metoda este synchronized
    @Override
    public synchronized void takeDamage(int damage)
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
