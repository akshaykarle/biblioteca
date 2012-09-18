package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInteractor {
    Biblioteca biblioteca = new Biblioteca();

    public void seedData() {
        biblioteca.seedData();
    }

    public void displayMenu() {
        System.out.print("\nMenu:\n" +
                "1. View All Books.\n" +
                "2. Reserve a book.\n" +
                "3. Check Library Card Number.\n" +
                "4. Exit.\n" +
                "Please select your option: ");
    }

    public void displayWelcome() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void enterOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int option = 0;
        boolean success;
        do {
            displayMenu();
            option = Integer.parseInt(bufferedReader.readLine());
            selectOption(option);
        }while(true);
    }

    boolean selectOption(int option) throws IOException{
        if(option == 1) {
            displayAllBooksOption();
            return true;
        }
        if(option == 2) {
            reserveBookOption();
            return true;
        }
        if(option == 3) {
            checkCardNumberOption();
            return true;
        }
        if(option == 4) {
            System.out.print("Bye!");
            System.exit(0);
        }
        System.out.println("Select a valid option!!");
        return false;
    }

    private void displayAllBooksOption() {
        System.out.println("\nBiblioteca contains the following books:");
        int numOfBooksDisplayed = biblioteca.displayAllBooks();
        if(numOfBooksDisplayed == 0)
            System.out.println("No books present in Biblioteca!!");
    }

    private void reserveBookOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of book: ");
        String name = bufferedReader.readLine();
        boolean success = biblioteca.findAndReserveBook(name);
        if(success)
            System.out.println("Thank You! Enjoy the book.");
        else
            System.out.println("Sorry we don't have that book yet.");
    }

    private void checkCardNumberOption() {
        System.out.println("Please talk to Librarian. Thank you.");
    }
}
