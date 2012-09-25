package com.twu28.biblioteca;

class Librarian {
    public boolean findAndReserveBook(String name, User loggedInUser, BookCollection books){
        Book bookFound;
        bookFound = books.findBookByName(name);
        if(bookFound == null)
            return false;
        else {
            reserveBook(bookFound, loggedInUser, books);
            return true;
        }
    }

    private void reserveBook(Book bookFound, User loggedInUser, BookCollection books) {
        books.remove(bookFound);
        loggedInUser.checkOutBook(bookFound);
    }
}
