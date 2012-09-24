package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class BibliotecaTest {
    private final int id = 1;
    private final String bookName = "foo";
    private final String author = "bar";
    private final String publisher = "blah";

    private final String userName = "111-1111";
    private final String password = "abc";
    private final String emailId = "foo@example.com";
    private final Long phoneNumber = 1234567890L;

    private Biblioteca biblioteca;
    private BookCollection bookCollection;
    private Book book;
    private User user;

    @Before
    public void setUp() {
        biblioteca = new Biblioteca();
        bookCollection = new BookCollection();
        book = new Book(id, bookName, author, publisher);
        user = new User(userName, password, emailId, phoneNumber);
    }

    @After
    public void cleanUp() {
        biblioteca = null;
        bookCollection = null;
        book = null;
        user = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        bookCollection.add(book);
        biblioteca.setBooks(bookCollection);
        assertFalse(biblioteca.findAndReserveBook("xyz", user));
    }

    @Test
    public void ShouldFindBookInCollectionRemoveItFromCollectionAndAddToUser() {
        bookCollection.add(book);
        biblioteca.setBooks(bookCollection);
        assertTrue(biblioteca.findAndReserveBook(bookName, user));
        assertEquals(0, bookCollection.getBooks().size());
        assertNotNull(user.books.getBooks());
    }
}
