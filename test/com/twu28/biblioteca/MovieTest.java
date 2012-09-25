package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.easymock.EasyMock.createMock;

public class MovieTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private Movie movie;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        movie = new Movie("foo", "bar", 1990, 1);
    }

    @After
    public void cleanUp() {
        movie = null;
    }

    @Test
    public void ShouldDisplayErrorOnInvalidMovieRating() {
        movie.setRating(11);
        assertTrue(outContent.toString().contains("Invalid Rating!"));
    }

    @Test
    public void ShouldDisplayAllFields() {
        String displayData = movie.getDisplayData();
        displayData.contains(movie.getName());
        displayData.contains(movie.getDirector());
        displayData.contains(String.valueOf(movie.getReleaseYear()));
        displayData.contains(String.valueOf(movie.getRating()));
    }

    @Test
    public void ShouldDisplayNAForUnsetRating() {
        movie.setRating(0);
        String displayData = movie.getDisplayData();
        displayData.contains("N/A");
    }
}
