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

        Hero hero1 = new Hero(1,1, null, '1');
        Grid.getInstance().tileFromPosition(1, 1).tileContent = hero1;
        Grid.getInstance().displayGrid();

        hero1.createLeeMatrix();
        hero1.printMap();
        ArrayList<Unit.PathfindingTile> path = hero1.getPathToDestination(8,6);

        new Thread() {
            public void run() {
                for ( int i = 0; i < path.size(); i++) {
                    Unit.PathfindingTile p = path.get(i);

                    Grid.getInstance().tileFromPosition(hero1.getPosX(), hero1.getPosY()).tileContent = null;
                    hero1.setPosX(p.selfX);
                    hero1.setPosY(p.selfY);
                    Grid.getInstance().tileFromPosition(p.selfX, p.selfY).tileContent = hero1;
                    System.out.println();
                    try {
                        sleep(500);
                    }
                    catch (Exception e) { }
                }
            }
        }.start();

        Hero hero2 = new Hero(2,9, null, '2');
        Grid.getInstance().tileFromPosition(1, 1).tileContent = hero1;
        Grid.getInstance().displayGrid();

        hero2.createLeeMatrix();
        hero2.printMap();
        ArrayList<Unit.PathfindingTile> path2 = hero2.getPathToDestination(2,2);

        new Thread() {
            public void run() {
                for ( int i = 0; i < path2.size(); i++) {
                    Unit.PathfindingTile p = path2.get(i);

                    Grid.getInstance().tileFromPosition(hero2.getPosX(), hero2.getPosY()).tileContent = null;
                    hero2.setPosX(p.selfX);
                    hero2.setPosY(p.selfY);
                    Grid.getInstance().tileFromPosition(p.selfX, p.selfY).tileContent = hero2;
                    System.out.println();
                    try {
                        sleep(500);
                    }
                    catch (Exception e) { }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while(true) {
                    System.out.println();
                    Grid.getInstance().displayGrid();

                    try {
                        sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();

        return;
    }
}
