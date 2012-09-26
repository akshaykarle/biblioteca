package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class UserInteractionTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    @Before
    public void setUp() {
        commonTestData.setUp();
    }

    @After
    public void tearDown() {
        commonTestData.cleanUp();
    }

    @Test
    public void ShouldDisplayWelcome() {
        commonTestData.userInteraction.displayWelcome();
        assertTrue(commonTestData.outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        commonTestData.userInteraction.displayMenu();
        assertTrue(commonTestData.outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        commonTestData.userInteraction.selectOption(-1);
        assertTrue(commonTestData.outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);
        commonTestData.userInteraction.selectOption(1);
        assertTrue(commonTestData.outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void shouldDisplayBooksIfPresent() throws IOException {
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);

        commonTestData.userInteraction.selectOption(1);
        assertTrue(commonTestData.outContent.toString().contains(String.valueOf(commonTestData.id)));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.bookName));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.author));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.publisher));
    }

    @Test
    public void ShouldDisplaySuccessfulBookReservation() throws IOException {
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);

        commonTestData.inContent = new ByteArrayInputStream(commonTestData.bookName.getBytes());
        System.setIn(commonTestData.inContent);
        commonTestData.userInteraction.reserveBookOption(commonTestData.user);
        assertTrue(commonTestData.outContent.toString().contains("Thank You! Enjoy the book."));
    }

    @Test
    public void ShouldDisplayFailureOfBookReservation() throws IOException {
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);

        commonTestData.inContent = new ByteArrayInputStream("xyz".getBytes());
        System.setIn(commonTestData.inContent);
        commonTestData.userInteraction.reserveBookOption(commonTestData.user);
        assertTrue(commonTestData.outContent.toString().contains("Sorry we don't have that book yet."));
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumberIfUserNotAuthenticated() throws IOException {
        commonTestData.userInteraction.selectOption(3);
        assertTrue(commonTestData.outContent.toString().contains("Please talk to Librarian. Thank you."));
    }

    @Test
    public void ShouldDisplayUserDetailsIfUserIsLoggedIn() throws IOException {
        commonTestData.userCollection.add(commonTestData.user);
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);

        commonTestData.userInteraction.loggedInUser = commonTestData.user;
        commonTestData.userInteraction.selectOption(3);
    }

    @Test
    public void ShouldNotDisplayAnyMovies() throws IOException {
        commonTestData.userInteraction.setMovies(commonTestData.movieCollection);
        commonTestData.userInteraction.selectOption(4);
        assertTrue(commonTestData.outContent.toString().contains("No movies found!"));
    }

    @Test
    public void ShouldDisplayAllMovies() throws IOException {
        commonTestData.movieCollection.add(commonTestData.movie);
        commonTestData.userInteraction.setMovies(commonTestData.movieCollection);
        commonTestData.userInteraction.selectOption(4);
        assertTrue(commonTestData.outContent.toString().contains("Movie\t\tYear\tDirector\tRating"));
    }

    @Test
    public void ShouldDisplayErrorOnAuthenticationFailure() throws IOException {
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);
        commonTestData.inContent = new ByteArrayInputStream("foo\r\nfoo".getBytes());
        System.setIn(commonTestData.inContent);
        commonTestData.userInteraction.selectOption(5);
        assertTrue(commonTestData.outContent.toString().contains("Login failed! Please check your username/password"));
    }

    @Test
    public void ShouldDisplaySuccessOnAuthenticationOfValidUser() throws IOException {
        commonTestData.userCollection.add(commonTestData.user);
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);
        commonTestData.inContent = new ByteArrayInputStream((commonTestData.userName + "\r\n" + commonTestData.password).getBytes());
        System.setIn(commonTestData.inContent);
        commonTestData.userInteraction.selectOption(5);
        assertTrue(commonTestData.outContent.toString().contains("Authentication successful"));
    }

    @Test
    public void UserShouldBeLoggedInBeforeReservingBook() throws IOException {
        commonTestData.userInteraction.selectOption(2);
        assertTrue(commonTestData.outContent.toString().contains("Please login before viewing this option"));
    }
}
