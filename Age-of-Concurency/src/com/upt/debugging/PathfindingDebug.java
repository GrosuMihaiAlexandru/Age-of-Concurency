package com.upt.debugging;

import com.upt.*;

public class PathfindingDebug {

    public static void main(String[] args) {
        Grid.setGridPath("scenarios\\pathfinding_debug01.txt");
        Grid.getInstance().displayGrid();
        System.out.println();

        // check whole path
        // do concurrency

        /*Hero hero1 = new Hero(1,1, null, '1');
        Grid.getInstance().tileFromPosition(1, 1).setTileContent(hero1);
        int dest1X = 3, dest1Y = 2;
        hero1.moveToDestination(dest1X, dest1Y, new ITaskFinishedCallback() {
            @Override
            public void onFinish() {
                System.out.println("Move ended for hero 1");
            }

            @Override
            public void onFail() {
                System.out.println("Move failed for hero 1");
            }
        });

        Hero hero2 = new Hero(8,6, null, '2');
        Grid.getInstance().tileFromPosition(8, 6).setTileContent(hero2);
        int dest2X = 2, dest2Y = 1;
        hero2.moveToDestination(dest2X, dest2Y, new ITaskFinishedCallback() {
            @Override
            public void onFinish() {
                System.out.println("Move ended for hero 2");
            }

            @Override
            public void onFail() {
                System.out.println("Move failed for hero 2");
            }
        });

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
                        sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
*/
        return;
    }
}
