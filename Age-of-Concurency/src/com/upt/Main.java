package com.upt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Grid grid = new Grid("Age-of-Concurency\\scenarios\\1.txt");
        grid.displayGrid();

    }
}
