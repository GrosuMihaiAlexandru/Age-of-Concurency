package com.upt;

import java.util.ArrayList;

public class Main
{
    public static void main(String args[])
    {
        // String testCase = "SimpleResource";
        // String testCase = "SimpleMove";
        String testCase = "SimpleCityAttack";

        switch (testCase) {
            case "SimpleResource":
                case1();
                break;
            case "SimpleMove":
                case2();
                break;
            case "SimpleCityAttack":
                case3();
                break;
            default:
                System.out.println("Unrecognized case. Goodbye!");
        }
    }

    private static void case1()
    {
        Grid.setGridPath("scenarios\\resourceCollect.txt");
        Player player1 = new Player(1, 1, Grid.ANSI_CYAN);
        Player player2 = new Player(8, 1, Grid.ANSI_GREEN);
        Player player3 = new Player(7, 6, Grid.ANSI_RED);

        player1.addCommand(new Command("move", 2, 6, 0, Resource.ResourceType.food));
        player1.addCommand(new Command("collect", 0, 0, 150, Resource.ResourceType.food));

        player2.addCommand(new Command("move", 4, 6, 0, Resource.ResourceType.food));
        player2.addCommand(new Command("collect", 0, 0, 50, Resource.ResourceType.food));

        player3.addCommand(new Command("move", 3, 7, 0, Resource.ResourceType.food));
        player3.addCommand(new Command("collect", 0, 0, 50, Resource.ResourceType.food));
        player3.addCommand(new Command("move", 1, 2, 50, Resource.ResourceType.food));


        player1.start();
        player2.start();
        player3.start();

        startMapPrintingThread();
    }

    private static void case2()
    {
        Grid.setGridPath("scenarios\\pathfindingAvoidance.txt");
        Player player1 = new Player(1, 1, Grid.ANSI_CYAN);
        Player player2 = new Player(1, 4, Grid.ANSI_GREEN);
        Player player3 = new Player(2, 8, Grid.ANSI_RED);
        Player player4 = new Player(55, 2, Grid.ANSI_YELLOW);
        Player player5 = new Player(56, 6, Grid.ANSI_BLUE);
        Player player6 = new Player(55, 8, Grid.ANSI_PURPLE);

        player1.addCommand(new Command("move", 56, 8, 0, Resource.ResourceType.food));
        player2.addCommand(new Command("move", 56, 7, 0, Resource.ResourceType.food));
        player3.addCommand(new Command("move", 56, 2, 0, Resource.ResourceType.food));
        player4.addCommand(new Command("move", 3, 8, 0, Resource.ResourceType.food));
        player5.addCommand(new Command("move", 2, 7, 0, Resource.ResourceType.food));
        player6.addCommand(new Command("move", 2, 2, 0, Resource.ResourceType.food));

        player1.start();
        player2.start();
        player3.start();
        player4.start();
        player5.start();
        player6.start();

        startMapPrintingThread();
    }

    private static void case3()
    {
        Grid.setGridPath("scenarios\\mercenaryAttack.txt");
        Player player1 = new Player(1, 1, Grid.ANSI_CYAN);
        Player player2 = new Player(20, 9, Grid.ANSI_RED);

        City city1 = new City(4, 2, player1);
        City city2 = new City(18, 8, player2);
        player1.setCity(city1);
        player2.setCity(city2);

        player1.addResource(Resource.ResourceType.gold, 1000);
        player1.addResource(Resource.ResourceType.food, 1000);

        player2.addResource(Resource.ResourceType.gold, 1000);
        player2.addResource(Resource.ResourceType.food, 1000);

        player1.addCommand(new Command("sendMercenary", 17, 8, 0, Resource.ResourceType.food));
        player1.addCommand(new Command("sendMercenary", 19, 8, 0, Resource.ResourceType.food));
        player1.addCommand(new Command("sendMercenary", 18, 7, 0, Resource.ResourceType.food));
        player2.addCommand(new Command("sendMercenary", 2, 2, 0, Resource.ResourceType.food));

        player1.start();
        player2.start();

        startMapPrintingThread();
    }

    private static void startMapPrintingThread()
    {
        new Thread() {
            public void run() {
                try {
                    sleep(100);
                } catch (Exception e) {
                }

                while (true) {
                    System.out.println();
                    Grid.getInstance().displayGrid();

                    try {
                        sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }
}