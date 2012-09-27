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
        commonTestData.setUp(); //arrange
    }

    @After
    public void tearDown() {
        commonTestData.cleanUp();
    }

    @Test
    public void ShouldDisplayWelcome() {
        //act
        commonTestData.userInteraction.displayWelcome();
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        //act
        commonTestData.userInteraction.displayMenu();
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        //act
        commonTestData.userInteraction.selectOption(-1);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        //arrange
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);
        //act
        commonTestData.userInteraction.selectOption(1);
        //arrange
        assertTrue(commonTestData.outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void shouldDisplayBooksIfPresent() throws IOException {
        //arrange
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);
        //act
        commonTestData.userInteraction.selectOption(1);
        //asserts
        assertTrue(commonTestData.outContent.toString().contains(String.valueOf(commonTestData.id)));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.bookName));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.author));
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.publisher));
    }

    @Test
    public void ShouldDisplaySuccessfulBookReservation() throws IOException {
        //arrange
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);

        commonTestData.inContent = new ByteArrayInputStream(commonTestData.bookName.getBytes());
        System.setIn(commonTestData.inContent);
        //act
        commonTestData.userInteraction.reserveBookOption(commonTestData.user);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Thank You! Enjoy the book."));
    }

    @Test
    public void ShouldDisplayFailureOfBookReservation() throws IOException {
        //arrange
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);

        commonTestData.inContent = new ByteArrayInputStream("xyz".getBytes());
        System.setIn(commonTestData.inContent);
        //act
        commonTestData.userInteraction.reserveBookOption(commonTestData.user);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Sorry we don't have that book yet."));
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumberIfUserNotAuthenticated() throws IOException {
        //act
        commonTestData.userInteraction.selectOption(3);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Please talk to Librarian. Thank you."));
    }

    @Test
    public void ShouldDisplayUserDetailsIfUserIsLoggedIn() throws IOException {
        //arrange
        commonTestData.userCollection.add(commonTestData.user);
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);

        commonTestData.userInteraction.loggedInUser = commonTestData.user;
        //act
        commonTestData.userInteraction.selectOption(3);
        assertTrue(commonTestData.outContent.toString().contains(commonTestData.userName));
    }

    @Test
    public void ShouldNotDisplayAnyMovies() throws IOException {
        //arrange
        commonTestData.userInteraction.setMovies(commonTestData.movieCollection);
        //act
        commonTestData.userInteraction.selectOption(4);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("No movies found!"));
    }

    @Test
    public void ShouldDisplayAllMovies() throws IOException {
        //arrange
        commonTestData.movieCollection.add(commonTestData.movie);
        commonTestData.userInteraction.setMovies(commonTestData.movieCollection);
        //act
        commonTestData.userInteraction.selectOption(4);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Movie\t\tYear\tDirector\tRating"));
    }

    @Test
    public void ShouldDisplayErrorOnAuthenticationFailure() throws IOException {
        //arrange
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);
        commonTestData.inContent = new ByteArrayInputStream("foo\r\nfoo".getBytes());
        System.setIn(commonTestData.inContent);
        //act
        commonTestData.userInteraction.selectOption(5);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Login failed! Please check your username/password"));
    }

    @Test
    public void ShouldDisplaySuccessOnAuthenticationOfValidUser() throws IOException {
        //arrange
        commonTestData.userCollection.add(commonTestData.user);
        commonTestData.userInteraction.setUserCollection(commonTestData.userCollection);
        commonTestData.inContent = new ByteArrayInputStream((commonTestData.userName + "\r\n" + commonTestData.password).getBytes());
        System.setIn(commonTestData.inContent);
        //act
        commonTestData.userInteraction.selectOption(5);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Authentication successful"));
    }

    @Test
    public void UserShouldBeLoggedInBeforeReservingBook() throws IOException {
        //act
        commonTestData.userInteraction.selectOption(2);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Please login before viewing this option"));
    }
}
