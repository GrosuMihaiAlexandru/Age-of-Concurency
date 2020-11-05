package com.upt;

import java.util.ArrayList;

public class Main
{
    public static void main(String args[])
    {
        String testCase = "SimpleMove";


        switch (testCase) {
            case "SimpleResource":
                case1();
                break;
            case "SimpleMove":
                case2();
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

        ArrayList<Thread> players = new ArrayList<Thread>();
        players.add(player1);
        players.add(player2);
        players.add(player3);

        startMapPrintingThread(players);
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

        ArrayList<Thread> players = new ArrayList<Thread>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);

        startMapPrintingThread(players);
    }

    private static void startMapPrintingThread(ArrayList<Thread> playerThreads)
    {
        new Thread() {
            public void run() {
                try {
                    sleep(100);
                } catch (Exception e) {
                }

                boolean playersAlive = true;

                while (playersAlive) {
                    System.out.println();
                    Grid.getInstance().displayGrid();

                    try {
                        sleep(500);
                    } catch (Exception e) {
                    }

                    playersAlive = false;
                    for(Thread t : playerThreads)
                        if (t.isAlive())
                            playersAlive = true;
                }
            }
        }.start();
    }
}