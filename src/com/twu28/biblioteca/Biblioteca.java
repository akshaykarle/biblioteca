package com.twu28.biblioteca;

class Biblioteca {
    private BookCollection books = new BookCollection();
    private MovieCollection movies = new MovieCollection();

    public void seedData() {
        books.seedBooks();
        movies.seedMovies();
    }

    public void setBooks(BookCollection booksList) {
        books = booksList;
    }

    public void setMovies(MovieCollection movies) {
        this.movies = movies;
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

    private void reserveBook(Book bookFound, User loggedInUser) {
        books.remove(bookFound);
        loggedInUser.checkOutBook(bookFound);
    }

    public int displayAllMovies() {
        return movies.display();
    }
}
