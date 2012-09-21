package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInteractor {
    Biblioteca biblioteca = new Biblioteca();
    User[] appUsers = new User[10];
    private int numOfUsers;
    private User loggedInUser;

    public void setBiblioteca(Biblioteca library) {
        biblioteca = library;
    }

    public void setValidUsers(User[] validUsers, int number) {
        appUsers = validUsers;
        numOfUsers = number;
    }

    public void seedData() {
        seedUsers();
        biblioteca.seedData();
    }

    private void seedUsers() {
        numOfUsers = 3;
        for(int i = 0; i < numOfUsers; i++) {
            String name = "111-111" + String.valueOf(i + 1);
            String password = "abc";
            String emailId = "user" + String.valueOf(i + 1) + "@thoughtworks.com";
            int contactNumber = 1234567890;
            appUsers[i] = new User(name, password, emailId, contactNumber);
        }
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

    boolean selectOption(int option) throws IOException{
        if(option == 1) {
            displayAllBooksOption();
            return true;
        }
        if(option == 2) {
            if(loggedInUser == null) {
                System.out.println("User Authentication failed. Please login before viewing this option.");
                return true;
            }
            else {
            reserveBookOption();
            return true;
            }
        }
        if(option == 3) {
            checkCardNumberOption();
            return true;
        }
        if(option == 4) {
            displayAllMoviesOption();
            return true;
        }
        if(option == 5) {
            loggedInUser = loginOption();
            return true;
        }
        if(option == 6) {
            System.out.print("Bye!");
            System.exit(0);
        }
        System.out.println("Select a valid option!!");
        return false;
    }

    private User loginOption() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter username: ");
        String username = bufferedReader.readLine();

        System.out.print("Enter password: ");
        String password = bufferedReader.readLine();

        User user = findUserByName(username);
        if(user != null && user.authenticate(password)) {
            System.out.println("Authentication successful");
            return user;
        }
        else {
            System.out.println("Login failed! Please check your username/password");
            return null;
        }
    }

    User findUserByName(String username) {
        for(int i = 0; i < numOfUsers; i++) {
            if(appUsers[i].getUserName().equals(username))
                return appUsers[i];
        }
        return null;
    }

    private void displayAllMoviesOption() {
        System.out.println("Movie\t\tYear\tDirector\tRating");
        int numOfMovies = biblioteca.displayAllMovies();
        if(numOfMovies == 0)
            System.out.println("No movies found!");
    }

    private void displayAllBooksOption() {
        System.out.println("\nBiblioteca contains the following books:");
        int numOfBooksDisplayed = biblioteca.displayAllBooks();
        if(numOfBooksDisplayed == 0)
            System.out.println("No books present in Biblioteca!!");
    }

    void reserveBookOption() throws IOException {
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
        if(loggedInUser != null) {
            loggedInUser.display();
        }
        else
            System.out.println("Please talk to Librarian. Thank you.");
    }
}
