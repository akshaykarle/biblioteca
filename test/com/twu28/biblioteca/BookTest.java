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

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void ShouldDisplayBookDetails() {
        Book book = new Book();
        book = book.seedData();
        book.display();
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.id)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.totalCopies)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.author)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.name)));
        Assert.assertTrue(outContent.toString().contains(String.valueOf(book.publisher)));
    }
}
