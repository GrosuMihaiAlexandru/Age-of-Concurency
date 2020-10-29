package com.upt;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class City implements  ITileContent, IInteractable, IAttacker
{
    private Player player;

    private int posX;
    private int posY;

    private AtomicInteger currentHealth;
    private int maxHealth;

    private int attack;

    private final int baseAttack = 50;

    private int cityLevel;

    private int foodUpgradeCost;
    private int woodUpgradeCost;
    private int goldUpgradeCost;
    private int stoneUpgradeCost;

    private final int baseFoodUpgradeCost = 500;
    private final int baseWoodUpgradeCost = 1000;
    private final int baseGoldUpgradeCost = 1000;
    private final int baseStoneUpgradeCost = 250;

    private final float upgradeCostMultiplier = 1.5f;
    private final float healthUpgradeMultiplier = 2f;
    private final float attackUpgradeMultiplier = 1.1f;

    private final int woodRepairPercentageCost = 5;
    private final int stoneRepairPercentageCost = 2;

    private volatile boolean hasStartedAttacking = false;

    public City (int x, int y, Player player)
    {
        this.posX = x;
        this.posY = y;
        this.player = player;
        maxHealth = 1000;
        currentHealth = new AtomicInteger(maxHealth);
        attack = baseAttack;
    }

    // repararea dureaza pana cand orasul e complet reparat sau playerul ramane fara resursele necesare
    private void repair()
    {
         new Thread(() ->
         {
             int wood = player.getResource(Resource.ResourceType.wood);
             int stone = player.getResource(Resource.ResourceType.stone);

             while (currentHealth.get() < maxHealth && wood >= woodRepairPercentageCost * cityLevel && stone >= stoneRepairPercentageCost  * cityLevel)
             {
                 player.addResource(Resource.ResourceType.wood, -woodRepairPercentageCost);
                 player.addResource(Resource.ResourceType.stone, -stoneRepairPercentageCost);

                 // probleme de concurenta
                 currentHealth.getAndAdd(maxHealth / 100);

                 wood = player.getResource(Resource.ResourceType.wood);
                 stone = player.getResource(Resource.ResourceType.stone);
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
    }

    @Override
    public void interact(Hero hero, ActionType actionType)
    {
        // Handle hero interactions
        if (actionType == ActionType.repair)
        {
            repair();
        }
        else if (actionType == ActionType.upgrade)
        {

        }
        else if (actionType == ActionType.train)
        {

        }
    }

    @Override
    public char getSymbol()
    {
        return 'C';
    }

    @Override
    public void attack(IAttacker attacker)
    {
        attacker.takeDamage(attack);
    }

    @Override
    public void takeDamage(int damage)
    {
        // probleme de concurenta
        if (currentHealth.get() > 0) {
            currentHealth.getAndAdd(-damage);

            System.out.println("City health left: " + currentHealth.get());
            // attack the enemy back
            if (!hasStartedAttacking)
            {
                synchronized (this)
                {
                    if (!hasStartedAttacking)
                    {
                        hasStartedAttacking = true;

                        new Thread(() ->
                        {
                            var attackers = getAdjacentAttackers();
                            while (attackers.size() > 0)
                            {
                                System.out.println(" City attack " + attackers.get(0));
                                attack(attackers.get(0));

                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                attackers = getAdjacentAttackers();
                            }
                            hasStartedAttacking = false;
                        }).start();
                    }
                }
            }
        }
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
}
