package com.twu28.biblioteca;

import java.io.IOException;

class Biblioteca {
    int numOfBooks, numOfMovies;
    private Book books[] = new Book [100];
    private Movie[] movies = new Movie[15];

    private void seedBooks() {
        books[0] = new Book(1, "foo", "foo", "foo", false);
        books[1] = new Book(2, "bar", "bar", "bar", false);
        books[2] = new Book(3, "bar", "bar", "bar", true);
        numOfBooks = 3;
    }

    private void seedMovies() {
        numOfMovies = 15;
        for(int i = 0; i < numOfMovies; i++) {
            String name = "Movie" + String.valueOf(i);
            String director = "Director" + String.valueOf(i);
            int year = 1990 + i;
            int rating = i;
            if(i > 10)
                rating = i/2;
            movies[i] = new Movie(name, director, year, rating);
        }
    }

    public Biblioteca seedData() {
        seedBooks();
        seedMovies();
        return this;
    }

    public void setBooks(Book[] newBooks, int number) {
        books = newBooks;
        numOfBooks = number;
    }

    public void setMovies(Movie[] newMovies, int number) {
        movies = newMovies;
        numOfMovies = number;
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
        for(int i = 0; i < numOfMovies; i++) {
            movies[i].display();
        }
        return numOfMovies;
    }
}
