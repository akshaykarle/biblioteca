package com.twu28.biblioteca;

class Movie {
    private final String name;
    private final String director;
    private int rating;
    private final int releaseYear;

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

    public String getDisplayData() {
        String displayData = name + "\t" + director + "\t" + releaseYear + "\t\t";
        if(rating == 0)
            displayData += "N/A";
        else
            displayData += String.valueOf(rating);
        displayData += "\n";
        return displayData;
    }
}
