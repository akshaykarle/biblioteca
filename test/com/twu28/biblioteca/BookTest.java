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
        book = new Book();
    }

    @After
    public void cleanUp() {
        System.setOut(null);
        System.setErr(null);
        book = null;
    }

    @Test
    public void ShouldDisplayBookDetails() {
        book = book.seedData();
        book.display();
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.id)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.author)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.name)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.publisher)));
    }

    @Test
    public void isNotReservedShouldReturnTrueIfBookIsNotReserved() {
        book.setReserve(false);
        Assert.assertTrue(book.isNotReserved());
    }

    @Test
    public void isNotReservedShouldReturnFalseIfBookIsNotReserved() {
        book.setReserve(true);
        Assert.assertFalse(book.isNotReserved());
    }
}
