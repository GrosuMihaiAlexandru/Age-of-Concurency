package com.upt.debugging;

import com.upt.Grid;
import com.upt.Hero;
import com.upt.Unit;
import com.upt.Unpassable;

import java.util.ArrayList;

public class PathfindingDebug {

    public static void main(String[] args) {
        Grid grid = new Grid("scenarios\\pathfinding_debug01.txt");
        grid.displayGrid();
        System.out.println();

        Hero hero = new Hero(1,1, null);
        grid.tileFromPosition(1, 1).tileContent = hero;
        grid.displayGrid();

        hero.setGrid(grid);
        hero.createLeeMatrix();
        hero.printMap();
        ArrayList<Unit.PathfindingTile> path = hero.getPathToDestination(8,6);

        /*
        for (Unit.PathfindingTile p : path) {
            // grid.tileFromPosition(p.selfX, p.selfY).tileContent = new Unpassable('#');
            System.out.println(p.selfX + " : " + p.selfY);
        }

        System.out.println();
        hero.printMap();
        */

        new Thread() {
            public void run() {
                for ( int i = 0; i < path.size(); i++) {
                    Unit.PathfindingTile p = path.get(i);

                    grid.tileFromPosition(hero.getPosX(), hero.getPosY()).tileContent = null;
                    hero.setPosX(p.selfX);
                    hero.setPosY(p.selfY);
                    grid.tileFromPosition(p.selfX, p.selfY).tileContent = hero;
                    System.out.println();
                    grid.displayGrid();
                    try {
                        sleep(500);
                    }
                    catch (Exception e) { }
                }
            }
        }.start();

        return;
    }
}
