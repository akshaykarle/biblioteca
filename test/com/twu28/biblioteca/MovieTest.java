package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.easymock.EasyMock.createMock;

public class MovieTest {
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
    public void ShouldDisplayErrorOnInvalidMovieRating() {
        factory.movie.setRating(11);
        assertTrue(factory.outContent.toString().contains("Invalid Rating!"));
    }

    @Test
    public void ShouldDisplayAllFields() {
        String displayData = factory.movie.getDisplayData();
        displayData.contains(factory.movie.getName());
        displayData.contains(factory.movie.getDirector());
        displayData.contains(String.valueOf(factory.movie.getReleaseYear()));
        displayData.contains(String.valueOf(factory.movie.getRating()));
    }

    @Test
    public void ShouldDisplayNAForUnsetRating() {
        factory.movie.setRating(0);
        String displayData = factory.movie.getDisplayData();
        displayData.contains("N/A");
    }
}
