package com.twu28.biblioteca;

import java.util.ArrayList;

class BookCollection {
    private ArrayList<Book> books = new ArrayList<Book>();

    public void seedBooks() {
        books.add(new Book(1, "foo", "foo", "foo"));
        books.add(new Book(2, "bar", "bar", "bar"));
        books.add(new Book(3, "bar", "bar", "bar"));
    }

    public int display() {
        for(Book book : books)
            book.display();
        return books.size();
    }

    public void setBooks(ArrayList<Book> booksList) {
        books = booksList;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    ArrayList<Book> findAllBooksByName(String name) {
        ArrayList<Book> booksFound = new ArrayList<Book>();
        for(Book book : books) {
            if(book.getName().equalsIgnoreCase(name))
                booksFound.add(book);
        }
        return booksFound;
    }

    public Book findBookByName(String name) {
        return findAllBooksByName(name).get(0);
    }

    public void remove(Book book) {
        books.remove(book);
    }

    public void add(Book book) {
        books.add(book);
    }
}
