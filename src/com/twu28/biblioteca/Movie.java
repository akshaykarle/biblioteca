package com.twu28.biblioteca;

public class Movie {
    private String name, director;
    private int rating, releaseYear;

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getRating() {
        return rating;
    }


    public void setRating(int movieRating) {
        if(movieRating >= 0 && movieRating <= 10)
            rating = movieRating;
        else
            System.out.println("Invalid Rating!");
    }

    public Movie(String movieName, String movieDirector, int movieReleaseYear, int movieRating) {
        name = movieName;
        director = movieDirector;
        releaseYear = movieReleaseYear;
        setRating(movieRating);
    }

    public void display() {
        System.out.print(name + "\t" + director + "\t" + releaseYear + "\t\t");
        if(rating == 0)
            System.out.println("N/A");
        else
            System.out.println(rating);
    }
}