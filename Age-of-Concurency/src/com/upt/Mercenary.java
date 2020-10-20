package com.upt;

public class Mercenary extends Unit implements ITileContent {

    public Mercenary(int x, int y, Player player) {
        super(x, y, player);
    }

    public char getSymbol() {
        return 'M';
    }

}
