package com.twu28.biblioteca;

import java.util.ArrayList;

class Biblioteca {
    private BookCollection books = new BookCollection();
    private MovieCollection movies = new MovieCollection();

    public Biblioteca seedData() {
        books.seedBooks();
        movies.seedMovies();
        return this;
    }

    public void setBooks(BookCollection booksList) {
        books = booksList;
    }

    public int displayAllAvailableBooks() {
        return books.display();
    }

    public boolean findAndReserveBook(String name, User loggedInUser){
        Book bookFound;
        bookFound = books.findBookByName(name);
        if(bookFound == null)
            return false;
        else {
            reserveBook(bookFound, loggedInUser);
            return true;
        }
    }

    void reserveBook(Book bookFound, User loggedInUser) {
        books.remove(bookFound);
        loggedInUser.checkOutBook(bookFound);
    }

    public int displayAllMovies() {
        return movies.display();
    }
}
