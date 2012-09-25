package com.twu28.biblioteca;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        book = new Book(1, "foo", "foo", "foo");
    }

    @After
    public void cleanUp() {
        book = null;
    }

    @Test
    public void ShouldDisplayBookDetails() {
        String displayData = book.getDisplayData();
        displayData.contains("1");
        displayData.contains("foo");
        displayData.contains("foo");
        displayData.contains("foo");
    }
}
