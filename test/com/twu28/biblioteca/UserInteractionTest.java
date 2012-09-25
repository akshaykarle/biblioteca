package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class UserInteractionTest {
    private final String userName = "111-1111";
    private final String password = "abc";
    private final String emailId = "foo@example.com";
    private final Long phoneNumber = 1234567890L;

    private final String movieName = "foo";
    private final String director = "bar";
    private final int year = 1990;
    private final int rating = 8;

    private final int id = 1;
    private final String bookName = "foo";
    private final String author = "bar";
    private final String publisher = "blah";

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private UserInteraction userInteraction;

    private UserCollection userCollection;
    private BookCollection bookCollection;
    private MovieCollection movieCollection;
    private User user;
    private Book book;
    private Movie movie;

    @Before
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
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        userInteraction = null;
        user = null;
        book = null;
        movie = null;
        userCollection = null;
        bookCollection = null;
        movieCollection = null;
    }

    @Test
    public void ShouldDisplayWelcome() {
        userInteraction.displayWelcome();
        assertTrue(outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        userInteraction.displayMenu();
        assertTrue(outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        userInteraction.selectOption(-1);
        assertTrue(outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        userInteraction.selectOption(1);
        userInteraction.setBooks(bookCollection);
        assertTrue(outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void ShouldDisplaySuccessfulBookReservation() throws IOException {
        bookCollection.add(book);
        userInteraction.setBooks(bookCollection);

        inContent = new ByteArrayInputStream(bookName.getBytes());
        System.setIn(inContent);
        userInteraction.reserveBookOption(user);
        assertTrue(outContent.toString().contains("Thank You! Enjoy the book."));
    }

    @Test
    public void ShouldDisplayFailureOfBookReservation() throws IOException {
        bookCollection.add(book);
        userInteraction.setBooks(bookCollection);

        inContent = new ByteArrayInputStream("xyz".getBytes());
        System.setIn(inContent);
        userInteraction.reserveBookOption(user);
        assertTrue(outContent.toString().contains("Sorry we don't have that book yet."));
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumberIfUserNotAuthenticated() throws IOException {
        userInteraction.selectOption(3);
        assertTrue(outContent.toString().contains("Please talk to Librarian. Thank you."));
    }

    @Test
    public void ShouldDisplayUserDetailsIfUserIsLoggedIn() throws IOException {
        userCollection.add(user);
        userInteraction.setUserCollection(userCollection);

        userInteraction.loggedInUser = user;
        userInteraction.selectOption(3);
    }

    @Test
    public void ShouldNotDisplayAnyMovies() throws IOException {
        userInteraction.setMovies(movieCollection);
        userInteraction.selectOption(4);
        assertTrue(outContent.toString().contains("No movies found!"));
    }

    @Test
    public void ShouldDisplayAllMovies() throws IOException {
        movieCollection.add(movie);
        userInteraction.setMovies(movieCollection);
        userInteraction.selectOption(4);
        assertTrue(outContent.toString().contains("Movie\t\tYear\tDirector\tRating"));
    }

    @Test
    public void ShouldDisplayErrorOnAuthenticationFailure() throws IOException {
        userInteraction.setUserCollection(userCollection);
        inContent = new ByteArrayInputStream("foo\r\nfoo".getBytes());
        System.setIn(inContent);
        userInteraction.selectOption(5);
        assertTrue(outContent.toString().contains("Login failed! Please check your username/password"));
    }

    @Test
    public void ShouldDisplaySuccessOnAuthenticationOfValidUser() throws IOException {
        userCollection.add(user);
        userInteraction.setUserCollection(userCollection);
        inContent = new ByteArrayInputStream((userName + "\r\n" + password).getBytes());
        System.setIn(inContent);
        userInteraction.selectOption(5);
        assertTrue(outContent.toString().contains("Authentication successful"));
    }

    @Test
    public void UserShouldBeLoggedInBeforeReservingBook() throws IOException {
        userInteraction.selectOption(2);
        assertTrue(outContent.toString().contains("Please login before viewing this option"));
    }
}
