package com.twu28.biblioteca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Factory {

    final String userName = "111-1111";
    final String password = "abc";
    final String emailId = "foo@example.com";
    final Long phoneNumber = 1234567890L;

    final String movieName = "foo";
    final String director = "bar";
    final int year = 1990;
    final int rating = 8;

    final int id = 1;
    final String bookName = "foo";
    final String author = "bar";
    final String publisher = "blah";

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    ByteArrayInputStream inContent;

    UserCollection userCollection;
    BookCollection bookCollection;
    MovieCollection movieCollection;
    User user;
    Book book;
    Movie movie;

    public UserInteraction userInteraction;

    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        userInteraction = new UserInteraction();
        userCollection = new UserCollection();
        bookCollection = new BookCollection();
        movieCollection = new MovieCollection();
        user = new User(userName, password, emailId, phoneNumber);
        book = new Book(id, bookName, author, publisher);
        movie = new Movie(movieName, director, year, rating);
        userInteraction = new UserInteraction();
    }

    public void cleanUp() {
        userInteraction = null;
        user = null;
        book = null;
        movie = null;
        userCollection = null;
        bookCollection = null;
        movieCollection = null;
        userInteraction = null;
    }
}
