package com.upt.debugging;

import com.upt.*;

import java.util.ArrayList;

public class ResourceDebug {

    public static void main(String[] args) {
        Grid grid = new Grid("scenarios\\1.txt");
        grid.displayGrid();
        System.out.println();

        Player player = new Player(2, 2);
        player.getHero().setGrid(grid);
        grid.tileFromPosition(2 , 2).tileContent = player.getHero();
        grid.displayGrid();


        Player player2 = new Player(3, 1);
        player2.getHero().setGrid(grid);
        grid.tileFromPosition(3 , 1).tileContent = player2.getHero();

        grid.displayGrid();

        player.start();
        player2.start();
    }
}
