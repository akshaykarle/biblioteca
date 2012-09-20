package com.twu28.biblioteca;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertTrue;

public class UserInteractorTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private ByteArrayInputStream inContent;
    private UserInteractor userInteractor;
    private Biblioteca bibliotecaMock;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        userInteractor = new UserInteractor();
        bibliotecaMock = createMock(Biblioteca.class);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        bibliotecaMock = null;
        userInteractor = null;
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
}
