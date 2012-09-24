package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class BibliotecaTest {
    private Biblioteca biblioteca;
    private BookCollection bookCollectionMock;
    private Book bookMock;
    private ArrayList<Book> booksList;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
        bookCollectionMock = createMock(BookCollection.class);
        bookMock = createMock(Book.class);
        booksList = new ArrayList<Book>();
    }

    @After
    public void cleanUp() {
        biblioteca = null;
        bookCollectionMock = null;
        booksList = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        expect(bookCollectionMock.findBooksByName("foo")).andReturn(booksList);
        replay(bookCollectionMock);
        biblioteca.setBooks(bookCollectionMock);
        assertFalse(biblioteca.findAndReserveBook("foo"));
        verify(bookCollectionMock);
    }

    @Test
    public void ShouldFindBookInCollection() {
        expect(bookMock.isNotReserved()).andReturn(true);
        bookMock.reserve(true);
        replay(bookMock);
        booksList.add(bookMock);
        expect(bookCollectionMock.findBooksByName("foo")).andReturn(booksList);
        replay(bookCollectionMock);
        biblioteca.setBooks(bookCollectionMock);
        assertTrue(biblioteca.findAndReserveBook("foo"));
        verify(bookCollectionMock, bookMock);
    }

    @Test
    public void ShouldReserveBookWithSuccess() throws IOException {
        expect(bookMock.isNotReserved()).andReturn(true);
        bookMock.reserve(true);
        replay(bookMock);
        booksList.add(bookMock);
        assertTrue(biblioteca.reserveBook(booksList));
        verify(bookMock);
    }

    @Test
    public void ShouldReserveBookWithFailure() throws IOException {
        expect(bookMock.isNotReserved()).andReturn(false);
        replay(bookMock);
        ArrayList<Book> booksList = new ArrayList<Book>();
        booksList.add(bookMock);
        assertFalse(biblioteca.reserveBook(booksList));
        verify(bookMock);
    }
}
