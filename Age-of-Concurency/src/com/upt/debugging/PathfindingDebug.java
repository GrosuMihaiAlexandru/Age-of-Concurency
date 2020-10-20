package com.upt.debugging;

import com.upt.Grid;
import com.upt.Hero;
import com.upt.Unit;
import com.upt.Unpassable;

import java.util.ArrayList;

public class PathfindingDebug {

    public static void main(String[] args) {
        Grid grid = new Grid(10, 10);
        grid.displayGrid();
        System.out.println();

        Hero hero = new Hero(1,1, null);
        grid.tileFromPosition(1, 1).tileContent = hero;
        grid.displayGrid();

        hero.setGrid(grid);
        hero.createLeeMatrix();
        hero.printMap();
        ArrayList<Unit.PathfindingTile> path = hero.getPathToDestination(8,6);

        for (Unit.PathfindingTile p : path) {
            // grid.tileFromPosition(p.selfX, p.selfY).tileContent = new Unpassable('#');
            System.out.println(p.selfX + " : " + p.selfY);
        }

        System.out.println();
        hero.printMap();

        return;
    }

}
