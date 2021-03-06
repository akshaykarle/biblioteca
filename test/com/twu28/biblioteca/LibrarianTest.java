package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibrarianTest {
    private static final CommonTestData commonTestData = new CommonTestData();
    private Librarian librarian;

    @Before
    public void setUp() {
        commonTestData.setUp();
        librarian = new Librarian();
    }

    @After
    public void tearDown() {
        commonTestData.cleanUp();
        librarian = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        //arrange
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);
        //act and assert
        assertFalse(librarian.findAndReserveBook("xyz", commonTestData.user, commonTestData.userInteraction.getBooks()));
    }

    @Test
    public void ShouldFindBookInCollectionRemoveItFromCollectionAndAddToUser() {
        //arrange
        commonTestData.bookCollection.add(commonTestData.book);
        commonTestData.userInteraction.setBooks(commonTestData.bookCollection);
        //act and asserts
        assertTrue(librarian.findAndReserveBook(commonTestData.bookName, commonTestData.user, commonTestData.userInteraction.getBooks()));
        assertEquals(0, commonTestData.bookCollection.getBooks().size());
        assertNotNull(commonTestData.user.books.getBooks());
    }
}
