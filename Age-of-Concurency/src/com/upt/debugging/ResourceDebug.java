package com.upt.debugging;

import com.upt.*;

import java.util.ArrayList;

public class ResourceDebug {

    public static void main(String[] args) {
        Grid.setGridPath("scenarios\\1.txt");
        Grid.getInstance().displayGrid();
        System.out.println();

        Player player = new Player(2, 2);
        Grid.getInstance().tileFromPosition(2 , 2).tileContent = player.getHero();
        Grid.getInstance().displayGrid();


        Player player2 = new Player(3, 1);
        Grid.getInstance().tileFromPosition(3 , 1).tileContent = player2.getHero();

        Grid.getInstance().displayGrid();

        player.start();
        player2.start();
    }
}
