package com.upt;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class City implements  ITileContent, IAttacker
{
    private Player player;

    private int posX;
    private int posY;

    public AtomicInteger getCurrentHealth() {
        return currentHealth;
    }

    private AtomicInteger currentHealth;
    private int maxHealth;

    private int attack;

    private int attackNo;

    private final int baseAttack = 50;

    private int cityLevel = 1;

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

    private boolean isRepairing = false;

    public City (int x, int y, Player player)
    {
        this.posX = x;
        this.posY = y;
        this.player = player;
        maxHealth = 1000;
        currentHealth = new AtomicInteger(maxHealth);
        attack = baseAttack;

        Grid.getInstance().tileFromPosition(x, y).setTileContent(this);
    }

    // repararea dureaza pana cand orasul e complet reparat sau playerul ramane fara resursele necesare
    // poti da repair in timp ce e atacat
    // TODO: da i repair
    public void startRepair(ITaskFinishedCallback callback)
    {
        if (!isRepairing) {
            isRepairing = true;
            new Thread(() ->
            {
                int wood = player.getResource(Resource.ResourceType.wood);
                int stone = player.getResource(Resource.ResourceType.stone);

                while (currentHealth.get() < maxHealth && wood >= woodRepairPercentageCost * cityLevel && stone >= stoneRepairPercentageCost * cityLevel)
                {
                    player.addResource(Resource.ResourceType.wood, -woodRepairPercentageCost * cityLevel);
                    player.addResource(Resource.ResourceType.stone, -stoneRepairPercentageCost * cityLevel);

                    // probleme de concurenta
                    currentHealth.getAndAdd(maxHealth / 100);

                    wood = player.getResource(Resource.ResourceType.wood);
                    stone = player.getResource(Resource.ResourceType.stone);

                    System.out.println(player.getPlayerColor() + "Repaired city HP: " + currentHealth.get() + " Wood: " + wood + " Stone: " + stone + Grid.ANSI_RESET);
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                isRepairing = false;
                callback.onFinish();
            }).start();
        }
    }

    @Override
    public String getSymbol()
    {
        return player.getPlayerColor() + "C" + Grid.ANSI_RESET;
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
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
        currentHealth.getAndAdd(-damage);

        if (currentHealth.get() <= 0)
        {
            onDeath();
            return;
        }

        System.out.println(player.getPlayerColor() + "City health left: " + currentHealth.get() + Grid.ANSI_RESET);
        // attack the enemy back
        if (!hasStartedAttacking)
        {
            synchronized (this)
            {
                if (!hasStartedAttacking)
                {
                    hasStartedAttacking = true;
                    attackNo = 0;

                    new Thread(() ->
                    {
                        var attackers = getAdjacentAttackers();
                        while (attackers.size() > 0 && isAlive())
                        {
                            attack(attackers.get(0));
                            attackNo++;
                            System.out.println(player.getPlayerColor() + " City attack no " + attackNo + ": " + ((Mercenary)attackers.get(0)).player.getPlayerColor() + "m" + ((Mercenary)attackers.get(0)).getNo() + " HP left: " + ((Mercenary)attackers.get(0)).getHealth() + Grid.ANSI_RESET);

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

    @Override
    public Player getPlayer() {
        return player;
    }

    private void onDeath()
    {
        Grid.getInstance().tileFromPosition(posX, posY).setTileContent(null);
        Grid.getInstance().displayGrid();
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

    private boolean isAlive()
    {
        return currentHealth.get() > 0;
    }
}
