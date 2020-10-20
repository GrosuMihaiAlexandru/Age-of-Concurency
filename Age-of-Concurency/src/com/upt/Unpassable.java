package com.upt;


public class Unpassable implements ITileContent{

    public enum UnpassableType { water, mountains}
    private UnpassableType unpassableType;

    public Unpassable(UnpassableType unpassableType) {
        this.unpassableType = unpassableType;
    }

    public Unpassable(char unpassableTypeSymbol) {
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
    public char getSymbol() {
        return toCharacter(unpassableType); }
}
