package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

import static org.easymock.EasyMock.createMock;

public class MovieTest {
    private static final CommonTestData commonTestData = new CommonTestData();

    @Before
    public void setUp() {
        //arrange
        commonTestData.setUp();
    }

    @After
    public void cleanUp() {
        commonTestData.cleanUp();
    }

    @Test
    public void ShouldDisplayErrorOnInvalidMovieRating() {
        //act
        commonTestData.movie.setRating(11);
        //assert
        assertTrue(commonTestData.outContent.toString().contains("Invalid Rating!"));
    }

    @Test
    public void ShouldDisplayAllFields() {
        //act
        String displayData = commonTestData.movie.getDisplayData();
        //asserts
        assertTrue(displayData.contains(commonTestData.movie.getName()));
        assertTrue(displayData.contains(commonTestData.movie.getDirector()));
        assertTrue(displayData.contains(String.valueOf(commonTestData.movie.getReleaseYear())));
        assertTrue(displayData.contains(String.valueOf(commonTestData.movie.getRating())));
    }

    @Test
    public void ShouldDisplayNAForUnsetRating() {
        //arrange
        commonTestData.movie.setRating(0);
        //act
        String displayData = commonTestData.movie.getDisplayData();
        //asserts
        assertTrue(displayData.contains("N/A"));
    }
}
