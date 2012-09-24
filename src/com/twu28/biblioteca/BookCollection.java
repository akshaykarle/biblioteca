package com.twu28.biblioteca;

import java.util.ArrayList;

public class BookCollection {
    ArrayList<Book> books = new ArrayList<Book>();

    public void seedBooks() {
        books.add(new Book(1, "foo", "foo", "foo", false));
        books.add(new Book(2, "bar", "bar", "bar", false));
        books.add(new Book(3, "bar", "bar", "bar", true));
    }

    public int display() {
        for(Book book : books)
            book.display();
        return books.size();
    }

    public void setBooks(ArrayList<Book> booksList) {
        books = booksList;
    }

    ArrayList<Book> findBooksByName(String name) {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for(Book book : books) {
            if(book.getName().equalsIgnoreCase(name))
                booksFound.add(book);
        }
        return booksFound;
    }
}
