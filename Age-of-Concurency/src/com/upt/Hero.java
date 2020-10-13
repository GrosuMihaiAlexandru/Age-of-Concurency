package com.upt;

public class Hero extends Unit implements ITileContent
{

    @Override
    public String displayContent() {
        return "H";
    }
}
