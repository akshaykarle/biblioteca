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
    public void ShouldDisplayListOfMoviesInCollection() throws IOException {
        factory.movieCollection.add(factory.movie);
        String displayData =  factory.movieCollection.display();
        displayData.contains(factory.movieName);
        displayData.contains(factory.director);
        displayData.contains(String.valueOf(factory.year));
        displayData.contains(String.valueOf(factory.rating));
    }
}
