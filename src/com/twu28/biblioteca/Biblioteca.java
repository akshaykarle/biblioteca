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
class Biblioteca {
    public void displayMenu() {
        System.out.print("Menu:" +
                "1. View All Books.\n" +
                "2. Reserve a book.\n" +
                "3. Check Library Card Number.\n" +
                "4. Exit." +
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
            option  = selectOption(option);
        }while(option == 0);
    }

    int selectOption(int option) {
        if(option == 1)
            return 1;
        if(option == 2)
            return 2;
        if(option == 3)
            return 3;
        if(option == 4) {
            System.exit(0);
            return 4;
        }
        else {
            System.out.println("Select a valid option!!");
            displayMenu();
            return 0;
        }
    }

    private void displayAllBooks() {
        for(int i = 0; i < numOfBooks; i++)
            books[i].display();
    }
}
