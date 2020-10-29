package com.upt.debugging;

import com.upt.City;
import com.upt.Grid;
import com.upt.Mercenary;
import com.upt.Player;

public class AttackerDebug {

    public static void main(String[] args) {
        Grid.setGridPath("scenarios\\1.txt");
        Grid.getInstance().displayGrid();
        System.out.println();

        Player player = new Player(3, 3);
        player.getHero().setSymbol('H');
        Grid.getInstance().tileFromPosition(3 , 3).setTileContent(player.getHero());

        Player player2 = new Player(4, 3);
        Grid.getInstance().tileFromPosition(4 , 3).setTileContent(player2.getHero());
        player2.getHero().setSymbol('H');

        City city = new City(2, 2, player);
        Grid.getInstance().tileFromPosition(2,2).setTileContent(city);

        Mercenary m1 = new Mercenary(1, 2, player2);
        Grid.getInstance().tileFromPosition(1,2).setTileContent(m1);
        Mercenary m2 = new Mercenary(3, 2, player2);
        Grid.getInstance().tileFromPosition(3,2).setTileContent(m2);
        Mercenary m3 = new Mercenary(2, 1, player2);
        Grid.getInstance().tileFromPosition(2,1).setTileContent(m3);

        Grid.getInstance().displayGrid();

        new Thread(() -> {

            int counter = 0;
            var attackables = m1.getAdjacentAttackers();
            while (m1.isAlive())
            {
                if (attackables.size() > 0)
                {
                    System.out.println("m1 HP: " + m1.getHealth() + " attack no: " + counter);
                    counter++;
                    m1.attack(attackables.get(0));
                }


                try {
                    Thread.sleep((1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int counter = 0;
            var attackables = m2.getAdjacentAttackers();
            while (m2.isAlive())
            {
                if (attackables.size() > 0)
                {
                    System.out.println("m2 HP: " + m2.getHealth() + " attack no: " + counter);
                    counter++;
                    m2.attack(attackables.get(0));
                }

                try {
                    Thread.sleep((1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            int counter = 0;
            var attackables = m3.getAdjacentAttackers();
            while (m3.isAlive())
            {
                if (attackables.size() > 0)
                {
                    System.out.println("m3 HP: " + m3.getHealth() + " attack no: " + counter);
                    counter++;
                    m3.attack(attackables.get(0));
                }

                try {
                    Thread.sleep((1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //player.start();
        //player2.start();
    }
}
