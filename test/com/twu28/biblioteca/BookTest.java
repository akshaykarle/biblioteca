package com.twu28.biblioteca;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created with IntelliJ IDEA.
 * User: akshay
 * Date: 15/09/12
 * Time: 2:55 PM
 * To change this template use File | Settings | File Templates.
 */
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
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.totalCopies)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.author)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.name)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.publisher)));
    }

    @Test
    public void ShouldReserveIfAvailable() {
        book.totalCopies = 1;
        book.reserved = 0;
        Assert.assertTrue(book.reserve());
    }

    @Test
    public void ShouldNotReserveIfNotAvailable() {
        book.totalCopies = 1;
        book.reserved = 1;
        Assert.assertFalse(book.reserve());
    }

    @Test
    public void ReserveShouldReduceNumberOfAvailableBooksIfSuccess() {
        book.totalCopies = 1;
        book.reserved = 0;
        book.reserve();
        Assert.assertEquals(1, book.reserved);
    }

    @Test
    public void ReserveShouldNotReduceNumberOfAvailableBooksIfFailure() {
        book.totalCopies = 1;
        book.reserved = 1;
        book.reserve();
        Assert.assertEquals(1, book.reserved);
    }
}
