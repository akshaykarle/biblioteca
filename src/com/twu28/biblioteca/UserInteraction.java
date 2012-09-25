package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class UserInteraction {
    private UserCollection users = new UserCollection();
    User loggedInUser;
    private BookCollection books = new BookCollection();
    private MovieCollection movies = new MovieCollection();
    private final Librarian librarian = new Librarian();

    public void setBooks(BookCollection booksList) {
        books = booksList;
    }

    public BookCollection getBooks() {
        return books;
    }

    public void setMovies(MovieCollection movies) {
        this.movies = movies;
    }

    public void setUserCollection(UserCollection newUsers) {
        users = newUsers;
    }

    public void seedData() {
        books.seedBooks();
        movies.seedMovies();
        users.seedUsers();
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
        String displayData = movies.display();
        if(displayData.equals(""))
            System.out.println("No movies found!");
        else
            System.out.println(displayData);
    }

    private void displayAllBooksOption() {
        System.out.println("\nBiblioteca contains the following books:");
        String displayData = books.display();
        if(displayData.equals(""))
            System.out.println("No books present in Biblioteca!!");
        else {
            System.out.println("ID\tName\tAuthor\tPublisher");
            System.out.println(displayData);
        }
    }

    void reserveBookOption(User loggedInUser) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of book: ");
        String name = bufferedReader.readLine();
        boolean success = librarian.findAndReserveBook(name, loggedInUser, getBooks());
        if(success)
            System.out.println("Thank You! Enjoy the book.");
        else
            System.out.println("Sorry we don't have that book yet.");
    }

    private void checkCardNumberOption() {
        if(loggedInUser != null) {
            String displayData = loggedInUser.getDisplayData();
            System.out.println("Username\tEmail ID\t\t\tPhone Number");
            System.out.println(displayData);
        }
        else
            System.out.println("Please talk to Librarian. Thank you.");
    }
}
