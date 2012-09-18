package com.twu28.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Biblioteca {
    int numOfBooks = 0;
    private Book books[] = new Book [100];

    public Biblioteca seedData() {
        books[0] = new Book(1, "foo", "foo", "foo", false);
        books[1] = new Book(2, "bar", "bar", "bar", false);
        books[2] = new Book(3, "bar", "bar", "bar", true);
        numOfBooks = 3;
        return this;
    }

    public void setBooks(Book[] newBooks, int number) {
        books = newBooks;
        numOfBooks = number;
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
        do {
            displayMenu();
            option = Integer.parseInt(bufferedReader.readLine());
            option  = selectOption(option);
        }while(option >= 0);
    }

    int selectOption(int option) throws IOException{
        if(option == 1) {
            displayAllBooks();
            return 1;
        }
        if(option == 2) {
            findAndReserveBook();
            return 2;
        }
        if(option == 3) {
            checkCardNumber();
            return 3;
        }
        if(option == 4) {
            System.out.print("Bye!");
            System.exit(0);
        }
        System.out.println("Select a valid option!!");
        return 0;
    }

    private void displayAllBooks() {
        if(numOfBooks == 0) {
            System.out.println("No books present in Biblioteca!!");
            return;
        }
        System.out.println("\nBiblioteca contains the following books:");
        for(int i = 0; i < numOfBooks; i++)
            books[i].display();
    }

    private void findAndReserveBook() throws IOException{
        Book[] booksFound;
        booksFound = findBooks();
        if(booksFound[0] == null)
            System.out.println("Book not found!");
        else {
            reserveBook(booksFound);
        }
    }

    void reserveBook(Book[] booksFound) {
        for (int i = 0; i < numOfBooks; i++) {
            if(booksFound[i] != null && booksFound[i].isNotReserved()) {
                booksFound[i].setReserve(true);
                System.out.println("Thank You! Enjoy the book.");
                return;
            }
        }
        System.out.println("Sorry we don't have that book yet.");
    }

    private Book[] findBooks() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of book: ");
        String name = bufferedReader.readLine();
        Book[] booksFound;
        booksFound = findBooksByName(name);
        return booksFound;
    }

    Book[] findBooksByName(String name) {
        Book[] booksFound = new Book[100];
        int j = 0;
        for(int i = 0; i < numOfBooks; i++) {
            if(books[i].getName().equalsIgnoreCase(name))
                booksFound[j++] = books[i];
        }
        return booksFound;
    }

    private void checkCardNumber() {
        System.out.println("Please talk to Librarian. Thank you.");
    }
}
