package com.upt.debugging;

import com.upt.*;

import java.util.ArrayList;

public class ResourceDebug {

    public static void main(String[] args) {
        Grid.setGridPath("scenarios\\1.txt");
        Grid.getInstance().displayGrid();
        System.out.println();

        Player player = new Player(2, 2, '1');
        Grid.getInstance().tileFromPosition(2 , 2).setTileContent(player.getHero());

        Player player2 = new Player(3, 1, '2');
        Grid.getInstance().tileFromPosition(3 , 1).setTileContent(player2.getHero());

        Grid.getInstance().displayGrid();

        player.getHero().startCollectingResource(Resource.ResourceType.food, 1, new ITaskFinishedCallback() {
            @Override
            public void onFinish() {
                System.out.println("Player 1 finished collecting food.");
            }

            @Override
            public void onFail() {
                System.out.println("Player 1 error collecting food.");
            }
        });
        player2.getHero().startCollectingResource(Resource.ResourceType.food, 10, new ITaskFinishedCallback() {
            @Override
            public void onFinish() {
                System.out.println("Player 2 finished collecting food.");
            }

            @Override
            public void onFail() {
                System.out.println("Player 2 error collecting food.");
            }
        });
    }
}
