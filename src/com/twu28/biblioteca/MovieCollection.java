package com.twu28.biblioteca;

import java.util.ArrayList;

class MovieCollection {
    private final ArrayList<Movie> movies = new ArrayList<Movie>();

    public void seedMovies() {
        for(int i = 0; i < 15; i++) {
            String name = "Movie" + String.valueOf(i);
            String director = "Director" + String.valueOf(i);
            int year = 1990 + i;
            int rating = i;
            if(i > 10)
                rating = i/2;
            movies.add(new Movie(name, director, year, rating));
        }
    }

    public String display() {
        String displayData = new String();
        for(Movie movie : movies) {
            displayData += movie.getDisplayData();
        }
        return displayData;
    }

    public void add(Movie movie) {
        movies.add(movie);
    }
}
