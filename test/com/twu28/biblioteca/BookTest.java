package com.twu28.biblioteca;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BookTest {
    private static final Factory factory = new Factory();

    @Before
    public void setUp() {
        factory.setUp();
    }

    @After
    public void cleanUp() {
        factory.cleanUp();
    }

    @Test
    public void ShouldDisplayBookDetails() {
        String displayData = factory.book.getDisplayData();
        displayData.contains(String.valueOf(factory.id));
        displayData.contains(factory.bookName);
        displayData.contains(factory.author);
        displayData.contains(factory.publisher);
    }
}
