package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibrarianTest {
    private final int id = 1;
    private final String bookName = "foo";
    private final String author = "bar";
    private final String publisher = "blah";

    private final String userName = "111-1111";
    private final String password = "abc";
    private final String emailId = "foo@example.com";
    private final Long phoneNumber = 1234567890L;

    private Book book;
    private BookCollection bookCollection;
    private UserInteraction userInteraction;
    private User user;
    private Librarian librarian;

    @Before
    public void setUp() {
        book = new Book(id, bookName, author, publisher);
        bookCollection = new BookCollection();
        userInteraction = new UserInteraction();
        user = new User(userName, password, emailId, phoneNumber);
        librarian = new Librarian();
    }

    @After
    public void tearDown() {
        book = null;
        bookCollection = null;
        user = null;
        librarian = null;
    }

    @Test
    public void ShouldFindBookInCollectionAndFailWhenNoBooksFound() {
        bookCollection.add(book);
        userInteraction.setBooks(bookCollection);
        assertFalse(librarian.findAndReserveBook("xyz", user, userInteraction.getBooks()));
    }

    @Test
    public void ShouldFindBookInCollectionRemoveItFromCollectionAndAddToUser() {
        bookCollection.add(book);
        userInteraction.setBooks(bookCollection);
        assertTrue(librarian.findAndReserveBook(bookName, user, userInteraction.getBooks()));
        assertEquals(0, bookCollection.getBooks().size());
        assertNotNull(user.books.getBooks());
    }
}
