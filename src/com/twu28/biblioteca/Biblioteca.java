package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 11/09/12
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Biblioteca {
    public void displayMenu() {
        System.out.print("Please select one of the following options:" +
                "1. View All Books.\n" +
                "2. Reserve a book.\n" +
                "3. Check Library Card Number.\n" +
                "Please select your option: ");
    }

    public void displayWelcome() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void enterOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int option = 0;
        do {
            option = Integer.parseInt(bufferedReader.readLine());
            selectOption(option);
        }while(option <= 3);
    }

    protected int selectOption(int option) {
        if(option == 1)
            return 1;
        else if(option == 2)
            return 2;
        else if(option == 3)
            return 3;
        else
            return -1;
    }
}
