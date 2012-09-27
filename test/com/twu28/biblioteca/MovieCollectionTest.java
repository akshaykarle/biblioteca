package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertTrue;
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
        //arrange
        commonTestData.movieCollection.add(commonTestData.movie);
        //act
        String displayData =  commonTestData.movieCollection.display();
        //asserts
        assertTrue(displayData.contains(commonTestData.movieName));
        assertTrue(displayData.contains(commonTestData.director));
        assertTrue(displayData.contains(String.valueOf(commonTestData.year)));
        assertTrue(displayData.contains(String.valueOf(commonTestData.rating)));
    }
}
