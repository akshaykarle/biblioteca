package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BookTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    @Before
    public void setUp() {
        commonTestData.setUp();
    }

    @After
    public void cleanUp() {
        commonTestData.cleanUp();
    }

    @Test
    public void ShouldDisplayBookDetails() {
        String displayData = commonTestData.book.getDisplayData();
        displayData.contains(String.valueOf(commonTestData.id));
        displayData.contains(commonTestData.bookName);
        displayData.contains(commonTestData.author);
        displayData.contains(commonTestData.publisher);
    }
}
