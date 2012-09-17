package com.twu28.biblioteca;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Biblioteca biblioteca;
    private Book bookMock;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        biblioteca = new Biblioteca();
        bookMock = createMock(Book.class);
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        biblioteca = null;
        bookMock = null;
    }

    @Test
    public void ShouldDisplayWelcome() {
        biblioteca.displayWelcome();
        assertTrue(outContent.toString().contains("Welcome to Biblioteca!\n"));
    }

    @Test
    public void ShouldDisplayMenu() {
        biblioteca.displayMenu();
        assertTrue(outContent.toString().contains(""));
    }

    @Test
    public void ShouldHandleInvalidOptions() throws IOException {
        assertEquals(0, biblioteca.selectOption(-1));
        assertTrue(outContent.toString().contains("Select a valid option!!"));
    }

    @Test
    public void shouldNotDisplayBooksIfNotPresent() throws IOException {
        biblioteca.selectOption(1);
        assertTrue(outContent.toString().contains("No books present in Biblioteca!!"));
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        bookMock.display();
        replay(bookMock);
        biblioteca.setBooks(new Book[]{bookMock});
        biblioteca.selectOption(1);
        verify(bookMock);
    }

    @Test
    public void ShouldFindTheCorrectBook() {
        bookMock.name = "foo";
        biblioteca.setBooks(new Book[]{bookMock});
        assertEquals(bookMock, biblioteca.findBookByName(bookMock.name));
    }

    @Test
    public void ShouldReserveBookWithSuccess() throws IOException {
        expect(bookMock.reserve()).andReturn(true);
        replay(bookMock);
        biblioteca.reserveBook(bookMock);
        assertTrue(outContent.toString().contains("Thank You! Enjoy the book."));
        verify(bookMock);
    }

    @Test
    public void ShouldReserveBookWithFailure() throws IOException {
        expect(bookMock.reserve()).andReturn(false);
        replay(bookMock);
        biblioteca.reserveBook(bookMock);
        assertTrue(outContent.toString().contains("Sorry we don't have that book yet."));
        verify(bookMock);
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumber() {
        biblioteca.checkCardNumber();
        assertTrue(outContent.toString().contains("Please talk to Librarian. Thank you."));
    }
}