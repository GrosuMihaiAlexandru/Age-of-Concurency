package com.upt;

public class City implements  ITileContent, IInteractable, IAttacker
{
    private Player player;


    private float currentHealth;
    private float maxHealth;

    private float attack;

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

    public City (Player player)
    {
        this.player = player;
        maxHealth = 1000;
        currentHealth = maxHealth;
    }

    private void repair()
    {
        int wood = player.getResource(Resource.ResourceType.wood);
        int stone = player.getResource(Resource.ResourceType.stone);

         
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
    public void takeDamage(float damage)
    {
        // probleme de concurenta
        if (currentHealth > 0)
        {
            currentHealth -= damage;
        }
    }

    private int calculateWoodRepairCost()
    {
        int percentageHealthMissing = (int)((1 - (currentHealth / maxHealth)) * 100);
        return percentageHealthMissing * woodRepairPercentageCost * cityLevel;
    }

    private int calculateStoneRepairCost()
    {
        int percentageHealthMissing = (int)((1 - (currentHealth / maxHealth)) * 100);
        return percentageHealthMissing * stoneRepairPercentageCost * cityLevel;
    }
}
