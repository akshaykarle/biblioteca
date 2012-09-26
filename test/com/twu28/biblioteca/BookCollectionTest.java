package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.expect;

public class BookCollectionTest {
    private static final Factory factory = new Factory();

    private Book book1, book2;
    private ArrayList<Book> booksList;

    @Before
    public void setUp() {
        book1 = new Book(factory.id, factory.bookName, factory.author, factory.publisher);
        book2 = new Book(factory.id + 1, factory.bookName, factory.author, factory.publisher);
        factory.setUp();
        booksList = new ArrayList<Book>();
    }

    @After
    public void cleanUp() {
        book1 = null;
        book2 = null;
        factory.cleanUp();
        booksList = null;
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        booksList.add(book1);
        factory.bookCollection.setBooks(booksList);
        String displayData = factory.bookCollection.display();
        assertTrue(displayData.contains(String.valueOf(factory.id)));
        assertTrue(displayData.contains(String.valueOf(factory.bookName)));
        assertTrue(displayData.contains(String.valueOf(factory.author)));
        assertTrue(displayData.contains(String.valueOf(factory.publisher)));
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        booksList.add(book1);
        booksList.add(book2);
        factory.bookCollection.setBooks(booksList);
        assertEquals(booksList, factory.bookCollection.findAllBooksByName("foo"));
    }

    @Test
    public void ShouldFindAndReturnTheFirstMatchedBook() {
        booksList.add(book1);
        booksList.add(book2);
        factory.bookCollection.setBooks(booksList);
        assertEquals(book1, factory.bookCollection.findBookByName("foo"));
    }
}
