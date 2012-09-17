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
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BibliotecaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Biblioteca biblioteca;
    private Book bookMock1, bookMock2;
    private Book[] bookMockArr;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        biblioteca = new Biblioteca();
        bookMock1 = createMock(Book.class);
        bookMock2 = createMock(Book.class);
        bookMockArr = new Book[100];
        bookMockArr[0] = bookMock1;
        bookMockArr[1] = bookMock2;
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        biblioteca = null;
        bookMock1 = null;
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
        bookMock1.display();
        replay(bookMock1);
        biblioteca.setBooks(new Book[]{bookMock1}, 1);
        biblioteca.selectOption(1);
        verify(bookMock1);
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        bookMock1.name = "foo";
        bookMock2.name = "foo";
        biblioteca.setBooks(bookMockArr, 2);
        assertEquals(2, biblioteca.numOfBooks);
        assertArrayEquals(bookMockArr, biblioteca.findBooksByName("foo"));
    }

    @Test
    public void ShouldReserveBookWithSuccess() throws IOException {
        expect(bookMock1.isNotReserved()).andReturn(true);
        bookMock1.setReserve(true);
        replay(bookMock1, bookMock2);
        biblioteca.setBooks(bookMockArr, 2);
        biblioteca.reserveBook(bookMockArr);
        assertTrue(outContent.toString().contains("Thank You! Enjoy the book."));
        verify(bookMock1, bookMock2);
    }

    @Test
    public void ShouldReserveBookWithFailure() throws IOException {
        expect(bookMock1.isNotReserved()).andReturn(false);
        expect(bookMock2.isNotReserved()).andReturn(false);
        replay(bookMock1, bookMock2);
        biblioteca.setBooks(bookMockArr, 2);
        biblioteca.reserveBook(bookMockArr);
        assertTrue(outContent.toString().contains("Sorry we don't have that book yet."));
        verify(bookMock1, bookMock2);
    }

    @Test
    public void ShouldSendErrorMessageOncheckCardNumber() {
        biblioteca.checkCardNumber();
        assertTrue(outContent.toString().contains("Please talk to Librarian. Thank you."));
    }
}
