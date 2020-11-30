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

    /*public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }*/

    @Override
    public String view(User user) {
        /**
         * get the movie to be viewed
         * */
        String viewed = this.getName();
        String message;

        if(user == null){
            message = "User doesn't exists.";
        }


        if (user.getHistory().containsKey(viewed)){
            user.getHistory().put(viewed, user.getHistory().get(viewed) + 1);

        }
        else {
            user.getHistory().putIfAbsent(viewed, 1);
        }
        //success -> Brexit: The Uncivil War was viewed with total views of 1
        int no  = user.getHistory().get(viewed);

        message = "success -> " + viewed +  " was viewed with total views of " + no + "\n";
        return message;
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
        super.setRatingAverage(average);
        return average;
    }


}




