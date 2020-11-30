package entertainment;

import users.User;

import java.util.*;

public class Movie  extends Video {

    private int duration;
    //private double rating;
    //private boolean rated; // asta e in plus
    // lista a toate ratingurile primite de un film de la un anumit user
    //name ul userului care a dat ratingul
    private Map<Double, String> ratings = new LinkedHashMap<>();




    public Movie(String name, int year, List<String> genres, List<String> cast, int duration) {
        super(name, year, genres, cast);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Map<Double, String> getRatings() {
        return ratings;
    }

    public void setRatings(Map<Double, String> ratings) {
        this.ratings = ratings;
    }


    // compute average rating for movies
    @Override
    public double computeAvgRating() {
        double average = 0;

        if(!(ratings.isEmpty())) {

            for (Double d: ratings.keySet()){
                if(d != 0){
                    average += d;
                }
            }
            average /= ratings.size();
        }
        setRatingAverage(average);
        return average;
    }

    @Override
    public int computeDuration() {
        return this.duration;
    }
}




