package com.upt;

public class Player extends Thread {

    private boolean isAlive;

    private int[] resources = new int[4];

    private Hero hero;

    public Player(int heroPosX, int heroPosY)
    {
        createCharacter(heroPosX, heroPosY);
        isAlive = true;
    }

    public void run()
    {
        while (isAlive) {
            try {
                var interactables = hero.getAdjacentInteractables();

                System.out.print(getName() + " Collect resource + " + interactables.size());
                System.out.println();

                if (interactables.size() > 0)
                    hero.collectResource(interactables.get(0));

                sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addResource(Resource.ResourceType resourceType, int value)
    {
        resources[resourceType.ordinal()] += value;
        System.out.print(this.getName() + " " + resourceType + " value: " + resources[resourceType.ordinal()] + "\n");
    }

    public void createCharacter(int posX, int posY)
    {
        hero = new Hero(posX, posY, this);
    }

    public Hero getHero() {
        return hero;
    }

}
