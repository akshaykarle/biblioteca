package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class UserInteractionTest {
    private static final Factory factory = new Factory();

    @Before
    public void setUp() {
        factory.setUp();
    }

    @After
    public void tearDown() {
        factory.cleanUp();
    }

    @Test
    public void ShouldDisplayWelcome() {
        factory.userInteraction.displayWelcome();
        assertTrue(factory.outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        factory.userInteraction.displayMenu();
        assertTrue(factory.outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        factory.userInteraction.selectOption(-1);
        assertTrue(factory.outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        factory.userInteraction.setBooks(factory.bookCollection);
        factory.userInteraction.selectOption(1);
        assertTrue(factory.outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void shouldDisplayBooksIfPresent() throws IOException {
        factory.bookCollection.add(factory.book);
        factory.userInteraction.setBooks(factory.bookCollection);

        factory.userInteraction.selectOption(1);
        assertTrue(factory.outContent.toString().contains(String.valueOf(factory.id)));
        assertTrue(factory.outContent.toString().contains(factory.bookName));
        assertTrue(factory.outContent.toString().contains(factory.author));
        assertTrue(factory.outContent.toString().contains(factory.publisher));
    }

    @Test
    public void ShouldDisplaySuccessfulBookReservation() throws IOException {
        factory.bookCollection.add(factory.book);
        factory.userInteraction.setBooks(factory.bookCollection);

        factory.inContent = new ByteArrayInputStream(factory.bookName.getBytes());
        System.setIn(factory.inContent);
        factory.userInteraction.reserveBookOption(factory.user);
        assertTrue(factory.outContent.toString().contains("Thank You! Enjoy the book."));
    }

    @Test
    public void ShouldDisplayFailureOfBookReservation() throws IOException {
        factory.bookCollection.add(factory.book);
        factory.userInteraction.setBooks(factory.bookCollection);

        factory.inContent = new ByteArrayInputStream("xyz".getBytes());
        System.setIn(factory.inContent);
        factory.userInteraction.reserveBookOption(factory.user);
        assertTrue(factory.outContent.toString().contains("Sorry we don't have that book yet."));
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumberIfUserNotAuthenticated() throws IOException {
        factory.userInteraction.selectOption(3);
        assertTrue(factory.outContent.toString().contains("Please talk to Librarian. Thank you."));
    }

    @Test
    public void ShouldDisplayUserDetailsIfUserIsLoggedIn() throws IOException {
        factory.userCollection.add(factory.user);
        factory.userInteraction.setUserCollection(factory.userCollection);

        factory.userInteraction.loggedInUser = factory.user;
        factory.userInteraction.selectOption(3);
    }

    @Test
    public void ShouldNotDisplayAnyMovies() throws IOException {
        factory.userInteraction.setMovies(factory.movieCollection);
        factory.userInteraction.selectOption(4);
        assertTrue(factory.outContent.toString().contains("No movies found!"));
    }

    @Test
    public void ShouldDisplayAllMovies() throws IOException {
        factory.movieCollection.add(factory.movie);
        factory.userInteraction.setMovies(factory.movieCollection);
        factory.userInteraction.selectOption(4);
        assertTrue(factory.outContent.toString().contains("Movie\t\tYear\tDirector\tRating"));
    }

    @Test
    public void ShouldDisplayErrorOnAuthenticationFailure() throws IOException {
        factory.userInteraction.setUserCollection(factory.userCollection);
        factory.inContent = new ByteArrayInputStream("foo\r\nfoo".getBytes());
        System.setIn(factory.inContent);
        factory.userInteraction.selectOption(5);
        assertTrue(factory.outContent.toString().contains("Login failed! Please check your username/password"));
    }

    @Test
    public void ShouldDisplaySuccessOnAuthenticationOfValidUser() throws IOException {
        factory.userCollection.add(factory.user);
        factory.userInteraction.setUserCollection(factory.userCollection);
        factory.inContent = new ByteArrayInputStream((factory.userName + "\r\n" + factory.password).getBytes());
        System.setIn(factory.inContent);
        factory.userInteraction.selectOption(5);
        assertTrue(factory.outContent.toString().contains("Authentication successful"));
    }

    @Test
    public void UserShouldBeLoggedInBeforeReservingBook() throws IOException {
        factory.userInteraction.selectOption(2);
        assertTrue(factory.outContent.toString().contains("Please login before viewing this option"));
    }
}
