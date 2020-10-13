package com.upt;

public class GameBoard {
    private static final int WIDTH = 32;
    private static final int HEIGHT = 8;
    private Tile[][] tiles = new Tile[HEIGHT][WIDTH];

    public GameBoard() {
        // TODO: generate tiles
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    public void displayGameBoard() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(tiles[i][j]);
            }
            System.out.println();
        }
    }

}
