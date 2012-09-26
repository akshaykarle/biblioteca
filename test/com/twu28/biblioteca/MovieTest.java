package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.easymock.EasyMock.createMock;

public class MovieTest {
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
    public void ShouldDisplayErrorOnInvalidMovieRating() {
        commonTestData.movie.setRating(11);
        assertTrue(commonTestData.outContent.toString().contains("Invalid Rating!"));
    }

    @Test
    public void ShouldDisplayAllFields() {
        String displayData = commonTestData.movie.getDisplayData();
        displayData.contains(commonTestData.movie.getName());
        displayData.contains(commonTestData.movie.getDirector());
        displayData.contains(String.valueOf(commonTestData.movie.getReleaseYear()));
        displayData.contains(String.valueOf(commonTestData.movie.getRating()));
    }

    @Test
    public void ShouldDisplayNAForUnsetRating() {
        commonTestData.movie.setRating(0);
        String displayData = commonTestData.movie.getDisplayData();
        displayData.contains("N/A");
    }
}
