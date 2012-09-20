package com.twu28.biblioteca;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserInteractorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private UserInteractor userInteractor;
    private Biblioteca bibliotecaMock;
    private User userMock;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        userInteractor = new UserInteractor();
        bibliotecaMock = createMock(Biblioteca.class);
        userMock = createMock(User.class);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        bibliotecaMock = null;
        userInteractor = null;
        userMock = null;
    }

    @Test
    public void ShouldDisplayWelcome() {
        userInteractor.displayWelcome();
        assertTrue(outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        userInteractor.displayMenu();
        assertTrue(outContent.toString().contains("Menu"));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        userInteractor.selectOption(-1);
        assertTrue(outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        userInteractor.selectOption(1);
        assertTrue(outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void ShouldDisplaySuccesfulBookReservation() throws IOException {
        String name = "foo";
        expect(bibliotecaMock.findAndReserveBook(name)).andReturn(true);
        replay(bibliotecaMock);
        userInteractor.setBiblioteca(bibliotecaMock);
        inContent = new ByteArrayInputStream(name.getBytes());
        System.setIn(inContent);
        userInteractor.selectOption(2);
        assertTrue(outContent.toString().contains("Thank You! Enjoy the book."));
        verify(bibliotecaMock);
    }

    @Test
    public void ShouldDisplayFailureOfBookReservation() throws IOException {
        expect(bibliotecaMock.findAndReserveBook("foo")).andReturn(false);
        replay(bibliotecaMock);
        userInteractor.setBiblioteca(bibliotecaMock);
        inContent = new ByteArrayInputStream("foo".getBytes());
        System.setIn(inContent);
        userInteractor.selectOption(2);
        assertTrue(outContent.toString().contains("Sorry we don't have that book yet."));
        verify(bibliotecaMock);
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumber() throws IOException {
        userInteractor.selectOption(3);
        assertTrue(outContent.toString().contains("Please talk to Librarian. Thank you."));
    }

    @Test
    public void ShouldNotDisplayAnyMovies() throws IOException {
        expect(bibliotecaMock.displayAllMovies()).andReturn(0);
        replay(bibliotecaMock);
        userInteractor.setBiblioteca(bibliotecaMock);
        userInteractor.selectOption(4);
        assertTrue(outContent.toString().contains("No movies found!"));
        verify(bibliotecaMock);
    }

    @Test
    public void ShouldDisplayAllMovies() throws IOException {
        expect(bibliotecaMock.displayAllMovies()).andReturn(15);
        replay(bibliotecaMock);
        userInteractor.setBiblioteca(bibliotecaMock);
        userInteractor.selectOption(4);
        assertTrue(outContent.toString().contains("Movie\t\tYear\tDirector\tRating"));
        verify(bibliotecaMock);
    }

    @Test
    public void ShouldFindUserByName() {
        expect(userMock.getUserName()).andReturn("foo");
        replay(userMock);
        userInteractor.setValidUsers(new User[]{userMock}, 1);
        assertEquals(userMock, userInteractor.findUserByName("foo"));
        verify(userMock);
    }

    @Test
    public void ShouldDisplayErrorOnAuthenticationFailure() throws IOException {
        expect(userMock.getUserName()).andReturn("foo");
        expect(userMock.authenticate("foo", "foo")).andReturn(false);
        replay(userMock);
        inContent = new ByteArrayInputStream("foo\r\nfoo".getBytes());
        System.setIn(inContent);
        userInteractor.setValidUsers(new User[]{userMock}, 1);
        userInteractor.selectOption(5);
        assertTrue(outContent.toString().contains("Login failed! Please check your username/password"));
        verify(userMock);
    }

    @Test
    public void ShouldDisplaySuccessOnAuthenticationOfValidUser() throws IOException {
        expect(userMock.getUserName()).andReturn("111-1111");
        expect(userMock.authenticate("111-1111", "ex@mple")).andReturn(true);
        replay(userMock);
        inContent = new ByteArrayInputStream("111-1111\r\nex@mple".getBytes());
        System.setIn(inContent);
        userInteractor.setValidUsers(new User[]{userMock}, 1);
        userInteractor.selectOption(5);
        assertTrue(outContent.toString().contains("Authentication successful"));
        verify(userMock);
    }

    /*@Test
    public void UserShouldBeLoggedInBeforeReservingBook() throws IOException {
        userInteractor.selectOption(2);
        assertTrue(outContent.toString().contains("Please login before viewing this option"));
    }*/

}
