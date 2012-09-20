package com.twu28.biblioteca;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BookTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Book book;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        book = new Book(1, "foo", "foo", "foo", false);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        book = null;
    }

    @Test
    public void ShouldDisplayBookDetails() {
        book.display();
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.getAuthor())));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.getId())));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.getName())));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.getPublisher())));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(!book.isNotReserved())));
    }

    @Test
    public void isNotReservedShouldReturnTrueIfBookIsReserved() {
        book.reserve(false);
        Assert.assertTrue(book.isNotReserved());
    }

    @Test
    public void isNotReservedShouldReturnFalseIfBookIsNotReserved() {
        book.reserve(true);
        Assert.assertFalse(book.isNotReserved());
    }
}
