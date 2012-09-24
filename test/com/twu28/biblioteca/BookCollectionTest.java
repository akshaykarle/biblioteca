package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.expect;

public class BookCollectionTest {
    private final int id = 1;
    private final String name = "foo";
    private final String author = "bar";
    private final String publisher = "blah";

    private Book book1, book2;
    private BookCollection bookCollection;
    private ArrayList<Book> booksList;

    @Before
    public void setUp() {
        book1 = new Book(id, name, author, publisher);
        book2 = new Book(id + 1, name, author, publisher);
        bookCollection = new BookCollection();
        booksList = new ArrayList<Book>();
    }

    @After
    public void cleanUp() {
        book1 = null;
        book2 = null;
        bookCollection = null;
        booksList = null;
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        booksList.add(book1);
        bookCollection.setBooks(booksList);
        assertEquals(booksList.size(), bookCollection.display());
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        booksList.add(book1);
        booksList.add(book2);
        bookCollection.setBooks(booksList);
        assertEquals(booksList, bookCollection.findAllBooksByName("foo"));
    }

    @Test
    public void ShouldFindAndReturnTheFirstMatchedBook() {
        booksList.add(book1);
        booksList.add(book2);
        bookCollection.setBooks(booksList);
        assertEquals(book1, bookCollection.findBookByName("foo"));
    }
}
