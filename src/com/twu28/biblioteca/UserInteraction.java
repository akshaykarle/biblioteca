package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UserInteraction {
    private Biblioteca biblioteca = new Biblioteca();
    private UserCollection users = new UserCollection();
    User loggedInUser;

    public void setBiblioteca(Biblioteca library) {
        biblioteca = library;
    }

    public void setUserCollection(UserCollection newUsers) {
        users = newUsers;
    }

    public void seedData() {
        users.seedUsers();
        biblioteca.seedData();
    }

    public void displayMenu() {
        System.out.print("\nMenu:\n" +
                "1. View All Books.\n" +
                "2. Reserve a book.\n" +
                "3. Check Library Card Number.\n" +
                "4. View All Movies and their ratings.\n" +
                "5. Login.\n" +
                "6. Exit.\n" +
                "Please select your option: ");
    }

    public void displayWelcome() {
        System.out.println("Welcome to Biblioteca!");
    }

    public void enterOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int option;
        do {
            displayMenu();
            option = Integer.parseInt(bufferedReader.readLine());
            selectOption(option);
        }while(true);
    }

    void selectOption(int option) throws IOException{
        if(option == 1) {
            displayAllBooksOption();
            return;
        }
        if(option == 2) {
            if(loggedInUser == null) {
                System.out.println("User Authentication failed. Please login before viewing this option.");
                return;
            }
            else {
            reserveBookOption(loggedInUser);
                return;
            }
        }
        if(option == 3) {
            checkCardNumberOption();
            return;
        }
        if(option == 4) {
            displayAllMoviesOption();
            return;
        }
        if(option == 5) {
            loggedInUser = loginOption();
            return;
        }
        if(option == 6) {
            System.out.print("Bye!");
            System.exit(0);
        }
        System.out.println("Select a valid option!!");
    }

    private User loginOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter username: ");
        String username = bufferedReader.readLine();

        System.out.print("Enter password: ");
        String password = bufferedReader.readLine();

        User loggedInUser = users.findAndAuthenticateUser(username, password);
        if(loggedInUser != null) {
            System.out.println("Authentication successful");
            return loggedInUser;
        }
        else {
            System.out.println("Login failed! Please check your username/password");
            return null;
        }
    }

    private void displayAllMoviesOption() {
        System.out.println("Movie\t\tYear\tDirector\tRating");
        int numOfMovies = biblioteca.displayAllMovies();
        if(numOfMovies == 0)
            System.out.println("No movies found!");
    }

    private void displayAllBooksOption() {
        System.out.println("\nBiblioteca contains the following books:");
        int numOfBooksDisplayed = biblioteca.displayAllAvailableBooks();
        if(numOfBooksDisplayed == 0)
            System.out.println("No books present in Biblioteca!!");
    }

    void reserveBookOption(User loggedInUser) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of book: ");
        String name = bufferedReader.readLine();
        boolean success = biblioteca.librarian.findAndReserveBook(name, loggedInUser, biblioteca.getBooks());
        if(success)
            System.out.println("Thank You! Enjoy the book.");
        else
            System.out.println("Sorry we don't have that book yet.");
    }

    private void checkCardNumberOption() {
        if(loggedInUser != null) {
            loggedInUser.display();
        }
        else
            System.out.println("Please talk to Librarian. Thank you.");
    }
}
