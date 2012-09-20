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
import static org.junit.Assert.*;

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
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        biblioteca = null;
        bookMock1 = null;
        bookMock2 = null;
        bookMockArr = null;
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        bookMock1.display();
        replay(bookMock1);
        biblioteca.setBooks(new Book[]{bookMock1}, 1);
        biblioteca.displayAllBooks();
        verify(bookMock1);
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        expect(bookMock1.getName()).andReturn("foo");
        expect(bookMock2.getName()).andReturn("foo");
        replay(bookMock1, bookMock2);
        biblioteca.setBooks(bookMockArr, 2);
        assertArrayEquals(bookMockArr, biblioteca.findBooksByName("foo"));
        verify(bookMock1, bookMock2);
    }

    @Test
    public void ShouldReserveBookWithSuccess() throws IOException {
        expect(bookMock1.isNotReserved()).andReturn(true);
        bookMock1.reserve(true);
        replay(bookMock1, bookMock2);
        biblioteca.setBooks(bookMockArr, 2);
        assertTrue(biblioteca.reserveBook(bookMockArr));
        verify(bookMock1, bookMock2);
    }

    @Test
    public void ShouldReserveBookWithFailure() throws IOException {
        expect(bookMock1.isNotReserved()).andReturn(false);
        expect(bookMock2.isNotReserved()).andReturn(false);
        replay(bookMock1, bookMock2);
        biblioteca.setBooks(bookMockArr, 2);
        assertFalse(biblioteca.reserveBook(bookMockArr));
        verify(bookMock1, bookMock2);
    }
}
