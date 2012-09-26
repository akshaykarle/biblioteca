package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibrarianTest {
    private static final Factory factory = new Factory();
    private Librarian librarian;

    @Before
    public void setUp() {
        factory.setUp();
        librarian = new Librarian();
    }

    @After
    public void tearDown() {
        factory.cleanUp();
        librarian = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        factory.bookCollection.add(factory.book);
        factory.userInteraction.setBooks(factory.bookCollection);
        assertFalse(librarian.findAndReserveBook("xyz", factory.user, factory.userInteraction.getBooks()));
    }

    @Test
    public void ShouldFindBookInCollectionRemoveItFromCollectionAndAddToUser() {
        factory.bookCollection.add(factory.book);
        factory.userInteraction.setBooks(factory.bookCollection);
        assertTrue(librarian.findAndReserveBook(factory.bookName, factory.user, factory.userInteraction.getBooks()));
        assertEquals(0, factory.bookCollection.getBooks().size());
        assertNotNull(factory.user.books.getBooks());
    }
}
