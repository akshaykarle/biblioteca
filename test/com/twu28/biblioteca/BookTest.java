package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

public class BookTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    @Before
    public void setUp() {
        commonTestData.setUp(); //arrange
    }

    @After
    public void cleanUp() {
        commonTestData.cleanUp();
    }

    @Test
    public void ShouldDisplayBookDetails() {
        String displayData = commonTestData.book.getDisplayData(); //act
        //asserts
        assertTrue(displayData.contains(String.valueOf(commonTestData.id)));
        assertTrue(displayData.contains(commonTestData.bookName));
        assertTrue(displayData.contains(commonTestData.author));
        assertTrue(displayData.contains(commonTestData.publisher));
    }
}
