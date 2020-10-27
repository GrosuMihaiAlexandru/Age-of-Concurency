package com.upt.debugging;

import com.upt.Grid;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class LoadGridDebug {

    public static void main(String[] args) throws FileNotFoundException {
        Grid.setGridPath("scenarios\\1.txt");
        Grid.getInstance().displayGrid();
    }
}
