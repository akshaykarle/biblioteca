package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class BibliotecaTest {
    private Biblioteca biblioteca;
    private BookCollection bookCollectionMock;
    private Book bookMock;
    private User userMock;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
        bookCollectionMock = createMock(BookCollection.class);
        bookMock = createMock(Book.class);
        userMock = createMock(User.class);
    }

    @After
    public void cleanUp() {
        biblioteca = null;
        bookCollectionMock = null;
        userMock = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        expect(bookCollectionMock.findBookByName("foo")).andReturn(null);
        replay(bookCollectionMock);
        biblioteca.setBooks(bookCollectionMock);
        assertFalse(biblioteca.findAndReserveBook("foo", userMock));
        verify(bookCollectionMock);
    }

    @Test
    public void ShouldFindBookInCollectionAndRemoveIt() {
        expect(bookCollectionMock.findBookByName("foo")).andReturn(bookMock);
        bookCollectionMock.remove(bookMock);
        replay(bookCollectionMock);
        biblioteca.setBooks(bookCollectionMock);
        assertTrue(biblioteca.findAndReserveBook("foo", userMock));
        verify(bookCollectionMock);
    }

    @Test
    public void ReserveBookShouldRemoveBookFromItsCollection() throws IOException {
        bookCollectionMock.remove(bookMock);
        replay(bookCollectionMock);
        biblioteca.setBooks(bookCollectionMock);
        biblioteca.reserveBook(bookMock, userMock);
        verify(bookCollectionMock);
    }

    @Test
    public void ReserveBookShouldAddBookToUser() {
        userMock.checkOutBook(bookMock);
        replay(userMock);
        biblioteca.setBooks(bookCollectionMock);
        biblioteca.reserveBook(bookMock, userMock);
        verify(userMock);
    }
}
