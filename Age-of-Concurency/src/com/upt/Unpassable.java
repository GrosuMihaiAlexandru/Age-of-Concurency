package com.upt;


public class Unpassable implements ITileContent{

    private int posX;

    private int posY;

    public enum UnpassableType { water, mountains}
    private UnpassableType unpassableType;

    public Unpassable(int posX, int posY, char unpassableTypeSymbol) {
        this.posX = posX;
        this.posY = posY;
        this.unpassableType = toUnpassableType(unpassableTypeSymbol);
    }

    public static UnpassableType toUnpassableType(char character) {

        UnpassableType type = null;

        switch (character) {
            case '#':
                type = UnpassableType.mountains;
                break;
            case '~':
                type = UnpassableType.water;
                break;
        }

        return type;
    }

    public static Character toCharacter(UnpassableType type) {

        char c = 'E';

        switch (type) {
            case water:
                c = '~';
                break;
            case mountains:
                c = '#';
                break;
        }

        return c;
    }

    @Override
    public String getSymbol() {
        return toCharacter(unpassableType) + "";
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }
}
