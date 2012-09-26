package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.easymock.EasyMock.createMock;

public class MovieCollectionTest {
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
    public void ShouldDisplayListOfMoviesInCollection() throws IOException {
        commonTestData.movieCollection.add(commonTestData.movie);
        String displayData =  commonTestData.movieCollection.display();
        displayData.contains(commonTestData.movieName);
        displayData.contains(commonTestData.director);
        displayData.contains(String.valueOf(commonTestData.year));
        displayData.contains(String.valueOf(commonTestData.rating));
    }
}
