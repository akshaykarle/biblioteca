package com.twu28.biblioteca;

import java.io.IOException;
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

    public int displayAllBooks() {
        return books.display();
    }

    public boolean findAndReserveBook(String name){
        ArrayList<Book> booksFound;
        booksFound = books.findBooksByName(name);
        if(booksFound.size() == 0)
            return false;
        else {
            return reserveBook(booksFound);
        }
    }

    boolean reserveBook(ArrayList<Book> booksFound) {
        for (Book book : booksFound) {
            if(book != null && book.isNotReserved()) {
                book.reserve(true);
                return true;
            }
        }
        return false;
    }

    public int displayAllMovies() {
        return movies.display();
    }
}
