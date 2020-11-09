package com.upt;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        // String testCase = "SimpleResource";
        // String testCase = "SimpleMove";
        // String testCase = "SimpleCityAttack";
        // String testCase = "FinalTest";

        System.out.println("Enter the case.");
        Scanner in = new Scanner(System.in);
        String testCase = in.next();

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
            case "FinalTest":
                case4();
                break;
            case "Move2":
                case5();
                break;
            case "MercenarySpawn":
                case6();
                break;
            default:
                System.out.println("Unrecognized case. Goodbye!");
        }
    }

    private static void case1()
    {
        Grid.setGridPath("scenarios\\resourceCollect02.txt");
        Player player1 = new Player(1, 1, Grid.ANSI_CYAN);
        Player player2 = new Player(8, 1, Grid.ANSI_GREEN);
        Player player3 = new Player(7, 6, Grid.ANSI_RED);

        player1.addCommand(new Command("move", 2, 6));
        player1.addCommand(new Command("collect", 150, Resource.ResourceType.food));

        player2.addCommand(new Command("move", 4, 6));
        player2.addCommand(new Command("collect", 50, Resource.ResourceType.food));

        player3.addCommand(new Command("move", 3, 7));
        player3.addCommand(new Command("collect", 50, Resource.ResourceType.food));
        player3.addCommand(new Command("move", 1, 2));

        player1.start();
        player2.start();
        player3.start();

        startMapPrintingThread();
    }

    private static void case2()
    {
        Grid.setGridPath("scenarios\\pathfindingAvoidance01.txt");
        Player player1 = new Player(1, 1, Grid.ANSI_CYAN);
        Player player2 = new Player(1, 4, Grid.ANSI_GREEN);
        Player player3 = new Player(2, 8, Grid.ANSI_RED);
        Player player4 = new Player(55, 2, Grid.ANSI_YELLOW);
        Player player5 = new Player(56, 6, Grid.ANSI_BLUE);
        Player player6 = new Player(55, 8, Grid.ANSI_PURPLE);

        player1.addCommand(new Command("move", 56, 8));
        player2.addCommand(new Command("move", 56, 7));
        player3.addCommand(new Command("move", 56, 2));
        player4.addCommand(new Command("move", 3, 8));
        player5.addCommand(new Command("move", 2, 7));
        player6.addCommand(new Command("move", 2, 2));

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

        player1.addCommand(new Command("sendMercenary", 17, 8));
        player1.addCommand(new Command("sendMercenary", 19, 8));
        player1.addCommand(new Command("sendMercenary", 18, 7));
        player2.addCommand(new Command("sendMercenary", 3, 2));

        player1.start();
        player2.start();

        startMapPrintingThread();
    }

    private static void case4()
    {
        Grid.setGridPath("scenarios\\finalTest.txt");
        Player cyan = new Player(4, 2, Grid.ANSI_CYAN);
        Player red = new Player(52, 2, Grid.ANSI_RED);
        Player green = new Player(30, 12, Grid.ANSI_GREEN);

        City city1 = new City(5, 2, cyan);
        City city2 = new City(53, 2, red);
        City city3 = new City(31, 12, green);

        cyan.setCity(city1);
        red.setCity(city2);
        green.setCity(city3);

        cyan.addCommand(new Command("move", 14, 8));
        cyan.addCommand(new Command("collect", 100, Resource.ResourceType.food));
        cyan.addCommand(new Command("move", 28, 8));
        cyan.addCommand(new Command("collect", 100, Resource.ResourceType.food));
        cyan.addCommand(new Command("move", 28, 3));
        cyan.addCommand(new Command("collect", 100, Resource.ResourceType.gold));


        red.addCommand(new Command("move", 48, 2));
        red.addCommand(new Command("sendMercenary", 32, 12));
        red.addCommand(new Command("sendMercenary", 31, 11));
        red.addCommand(new Command("move", 30, 8));
        red.addCommand(new Command("collect", 150, Resource.ResourceType.food));
        red.addCommand(new Command("move", 43, 8));
        red.addCommand(new Command("collect", 100, Resource.ResourceType.gold));
        red.addCommand(new Command("sendMercenary", 31, 11));
        red.addCommand(new Command("sendMercenary", 32, 12));
        red.addCommand(new Command("sendMercenary", 30, 12));

        green.addCommand(new Command("move", 15, 9));
        green.addCommand(new Command("collect", 150, Resource.ResourceType.food));
        green.addCommand(new Command("move", 29, 9));
        green.addCommand(new Command("collect", 100, Resource.ResourceType.food));
        green.addCommand(new Command("sendMercenary", 4, 2));
        green.addCommand(new Command("sendMercenary", 6, 2));
        green.addCommand(new Command("sendMercenary", 5, 3));
        green.addCommand(new Command("sendMercenary", 5, 1));

        cyan.start();
        red.start();
        green.start();

        startMapPrintingThread();
    }

    private static void case5() {
        Grid.setGridPath("scenarios\\pathfindingAvoidance02.txt");

        Player red = new Player(1, 1, Grid.ANSI_RED);
        Player green = new Player(6, 2, Grid.ANSI_GREEN);

        red.addCommand(new Command("move", 16, 1));
        green.addCommand(new Command("move", 8, 1));

        red.start();
        green.start();

        startMapPrintingThread();
    }

    private static void case6() {
        Grid.setGridPath("scenarios\\pathfindingAvoidance02.txt");

        Player red = new Player(8, 3, Grid.ANSI_RED);
        red.addResource(Resource.ResourceType.food, 200);
        red.addResource(Resource.ResourceType.gold, 200);

        City c = new City(2, 2, red);
        red.setCity(c);


        red.addCommand(new Command("sendMercenary", 13, 3));
        red.addCommand(new Command("sendMercenary", 13, 3));
        red.addCommand(new Command("sendMercenary", 13, 3));
        red.addCommand(new Command("sendMercenary", 13, 3));


        red.start();

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
                        sleep(1000);
                    } catch (Exception e) {
                    }
                }
            }
        }.start();
    }
}