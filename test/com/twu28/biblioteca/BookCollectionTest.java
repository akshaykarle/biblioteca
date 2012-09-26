package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class BookCollectionTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    private Book book1, book2;
    private ArrayList<Book> booksList;

    @Before
    public void setUp() {
        book1 = new Book(commonTestData.id, commonTestData.bookName, commonTestData.author, commonTestData.publisher);
        book2 = new Book(commonTestData.id + 1, commonTestData.bookName, commonTestData.author, commonTestData.publisher);
        commonTestData.setUp();
        booksList = new ArrayList<Book>();
    }

    @After
    public void cleanUp() {
        book1 = null;
        book2 = null;
        commonTestData.cleanUp();
        booksList = null;
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        booksList.add(book1);
        commonTestData.bookCollection.setBooks(booksList);
        String displayData = commonTestData.bookCollection.display();
        assertTrue(displayData.contains(String.valueOf(commonTestData.id)));
        assertTrue(displayData.contains(String.valueOf(commonTestData.bookName)));
        assertTrue(displayData.contains(String.valueOf(commonTestData.author)));
        assertTrue(displayData.contains(String.valueOf(commonTestData.publisher)));
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        booksList.add(book1);
        booksList.add(book2);
        commonTestData.bookCollection.setBooks(booksList);
        assertEquals(booksList, commonTestData.bookCollection.findAllBooksByName("foo"));
    }

    @Test
    public void ShouldFindAndReturnTheFirstMatchedBook() {
        booksList.add(book1);
        booksList.add(book2);
        commonTestData.bookCollection.setBooks(booksList);
        assertEquals(book1, commonTestData.bookCollection.findBookByName("foo"));
    }
}
