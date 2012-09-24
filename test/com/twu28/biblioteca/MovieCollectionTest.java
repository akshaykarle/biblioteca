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
    private Movie movieMock;
    private MovieCollection movies;
    private ArrayList<Movie> moviesList;

    @Before
    public void setUp() {
        movieMock = createMock(Movie.class);
        movies = new MovieCollection();
        moviesList = new ArrayList<Movie>();
    }

    @After
    public void cleanUp() {
        movieMock = null;
        movies = null;
        moviesList = null;
    }

    @Test
    public void ShouldDisplayListOfMoviesInCollection() throws IOException {
        movieMock.display();
        replay(movieMock);

        moviesList.add(movieMock);
        movies.setMovies(moviesList);
        assertEquals(moviesList.size(), movies.display());
        verify(movieMock);
    }
}
