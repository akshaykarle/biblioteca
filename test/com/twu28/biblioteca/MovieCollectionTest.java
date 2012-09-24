package com.twu28.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class MovieCollectionTest {
    private final String movieName = "foo";
    private final String director = "bar";
    private final int year = 1990;
    private final int rating = 8;

    private Movie movie;
    private MovieCollection movies;

    @Before
    public void setUp() {
        movie = new Movie(movieName, director, year, rating);
        movies = new MovieCollection();
    }

    @After
    public void cleanUp() {
        movie = null;
        movies = null;
    }

    @Test
    public void ShouldDisplayListOfMoviesInCollection() throws IOException {
        movies.add(movie);
        assertEquals(1, movies.display());
    }
}
