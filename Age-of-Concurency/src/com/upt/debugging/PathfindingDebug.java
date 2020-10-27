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
        Grid.getInstance().tileFromPosition(1, 1).setTileContent(hero1);
        int dest1X = 8, dest1Y = 6;

        new Thread() {
            public void run() {


                while (hero1.getPosX() != dest1X && hero1.getPosY() != dest1Y) {
                    hero1.createLeeMatrix();
                    // hero2.printMap(); the lee matrix
                    ArrayList<Unit.PathfindingTile> path = hero1.getPathToDestination(dest1X,dest1Y);

                    for (int i = 0; i < path.size(); i++) {
                        Unit.PathfindingTile t = path.get(i);

                        if (Grid.getInstance().tileFromPosition(t.selfX, t.selfY).getTileContent() == null) {
                            // cell is empty, start moving
                            Grid.getInstance().tileFromPosition(hero1.getPosX(), hero1.getPosY()).setTileContent(null);
                            hero1.setPosX(t.selfX);
                            hero1.setPosY(t.selfY);
                            Grid.getInstance().tileFromPosition(t.selfX, t.selfY).setTileContent(hero1);
                            System.out.println();
                            try {
                                sleep(1500);
                            } catch (Exception e) {
                            }
                        } else {
                            // something is in the way. Recalculate path and continue moving
                            continue;
                        }
                    }
                }
            }
        }.start();

        Hero hero2 = new Hero(8,6, null, '2');
        Grid.getInstance().tileFromPosition(1, 1).setTileContent(hero2);
        int dest2X = 1, dest2Y = 1;

        new Thread() {
            public void run() {
                try {
                    sleep(10);
                } catch (Exception e) {
                }

                while (hero2.getPosX() != dest2X && hero2.getPosY() != dest2Y) {
                    hero2.createLeeMatrix();
                    // hero2.printMap(); the lee matrix
                    ArrayList<Unit.PathfindingTile> path = hero2.getPathToDestination(dest2X,dest2Y);

                    for (int i = 0; i < path.size(); i++) {
                        Unit.PathfindingTile t = path.get(i);

                        if (Grid.getInstance().tileFromPosition(t.selfX, t.selfY).getTileContent() == null) {
                            // cell is empty, start moving
                            Grid.getInstance().tileFromPosition(hero2.getPosX(), hero2.getPosY()).setTileContent(null);
                            hero2.setPosX(t.selfX);
                            hero2.setPosY(t.selfY);
                            Grid.getInstance().tileFromPosition(t.selfX, t.selfY).setTileContent(hero2);
                            System.out.println();
                            try {
                                sleep(1500);
                            } catch (Exception e) {
                            }
                        } else {
                            // something is in the way. Recalculate path and continue moving
                            break;
                        }
                    }
                }
            }
        }.start();

        new Thread() {
            public void run() {
                try {
                    sleep(100);
                } catch (Exception e) {
                }

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
