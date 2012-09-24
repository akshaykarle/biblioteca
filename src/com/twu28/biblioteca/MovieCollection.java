package com.twu28.biblioteca;

import java.util.ArrayList;

public class MovieCollection {
    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    ArrayList<Movie> movies = new ArrayList<Movie>();

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

    public int display() {
        for(Movie movie : movies) {
            movie.display();
        }
        return movies.size();
    }
}
