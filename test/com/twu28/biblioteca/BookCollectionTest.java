package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.*;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertArrayEquals;

public class BookCollectionTest {
    private Book bookMock1,bookMock2;
    private BookCollection bookCollection;
    private ArrayList<Book> booksList;

    @Before
    public void setUp() {
        bookMock1 = createMock(Book.class);
        bookMock2 = createMock(Book.class);
        bookCollection = new BookCollection();
        booksList = new ArrayList<Book>();
    }

    @After
    public void cleanUp() {
        bookMock1 = null;
        bookMock2 = null;
        bookCollection = null;
        booksList = null;
    }

    @Test
    public void ShouldDisplayListOfBooks() throws IOException {
        bookMock1.display();
        replay(bookMock1);
        booksList.add(bookMock1);
        bookCollection.setBooks(booksList);
        assertEquals(booksList.size(), bookCollection.display());
        verify(bookMock1);
    }

    @Test
    public void ShouldFindTheCorrectBooks() {
        expect(bookMock1.getName()).andReturn("foo");
        expect(bookMock2.getName()).andReturn("foo");
        replay(bookMock1, bookMock2);
        booksList.add(bookMock1);
        booksList.add(bookMock2);
        bookCollection.setBooks(booksList);
        assertEquals(booksList, bookCollection.findBooksByName("foo"));
        verify(bookMock1, bookMock2);
    }
}
