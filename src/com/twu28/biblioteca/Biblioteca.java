package com.twu28.biblioteca;

import java.io.IOException;

class Biblioteca {
    int numOfBooks, numOfMovies;
    private Book books[] = new Book [100];
    private MovieCollection movies = new MovieCollection();

    private void seedBooks() {
        books[0] = new Book(1, "foo", "foo", "foo", false);
        books[1] = new Book(2, "bar", "bar", "bar", false);
        books[2] = new Book(3, "bar", "bar", "bar", true);
        numOfBooks = 3;
    }

    public Biblioteca seedData() {
        seedBooks();
        movies.seedMovies();
        return this;
    }

    public void setBooks(Book[] newBooks, int number) {
        books = newBooks;
        numOfBooks = number;
    }

    public void setMoviesCollection(MovieCollection newMovies) {
        movies = newMovies;
    }

    public int displayAllBooks() {
        for(int i = 0; i < numOfBooks; i++)
            books[i].display();
        return numOfBooks;
    }

    public boolean findAndReserveBook(String name) throws IOException{
        Book[] booksFound;
        booksFound = findBooksByName(name);
        if(booksFound[0] == null)
            return false;
        else {
            return reserveBook(booksFound);
        }
    }

    boolean reserveBook(Book[] booksFound) {
        for (int i = 0; i < numOfBooks; i++) {
            if(booksFound[i] != null && booksFound[i].isNotReserved()) {
                booksFound[i].reserve(true);
                return true;
            }
        }
        return false;
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

    public int displayAllMovies() {
        return movies.display();
    }
}
