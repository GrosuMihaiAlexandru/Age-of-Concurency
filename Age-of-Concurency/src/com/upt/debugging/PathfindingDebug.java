package com.upt.debugging;

import com.upt.Grid;
import com.upt.Hero;
import com.upt.Unit;
import com.upt.Unpassable;

import java.util.ArrayList;

public class PathfindingDebug {

    public static void main(String[] args) {
        Grid.setGridPath("scenarios\\pathfinding_debug01.txt");
        Grid.getInstance().displayGrid();
        System.out.println();

        Hero hero = new Hero(1,1, null);
        Grid.getInstance().tileFromPosition(1, 1).tileContent = hero;
        Grid.getInstance().displayGrid();

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

                    Grid.getInstance().tileFromPosition(hero.getPosX(), hero.getPosY()).tileContent = null;
                    hero.setPosX(p.selfX);
                    hero.setPosY(p.selfY);
                    Grid.getInstance().tileFromPosition(p.selfX, p.selfY).tileContent = hero;
                    System.out.println();
                    Grid.getInstance().displayGrid();
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
