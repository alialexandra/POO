package entertainment;

import users.User;

import java.util.ArrayList;
import java.util.List;

public  abstract class Video {

    /**
        Parent class for the Movie and Shows
        because they have common inputs
     */

    // id to remember the order of the movies

    private String name;
    private int year;
    private List<String> genres;
    private List<String> cast;
    private double ratingAverage;
    private int noFavorite;
    private int noViews;


    public Video(String name, int year,
                 List<String> genres, List<String> cast){

        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        ratingAverage = 0;// intial
        noFavorite = 0;
        noViews = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public double getRatingAverage() {
        return ratingAverage;
    }

    public void setRatingAverage(double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    public int getNoFavorite() {
        return noFavorite;
    }

    public void setNoFavorite(int noFavorite) {
        this.noFavorite = noFavorite;
    }

    public int getNoViews() {
        return noViews;
    }

    public void setNoViews(int noViews) {
        this.noViews = noViews;
    }

    /**
     * @param user
     * add a video to the favourites for user
     * return the specific message for the action
     */
   public String addFavourite(User user){

        /**
         * maybe this is not really needed
         * because is inside of the class
         * maybe I could add the message to be written here?
        */
        String newEntry = this.getName();
        String message = null;

        if(user.getFavourite().contains(newEntry)) {
            message = "error -> " + newEntry +" is already in favourite list";
        }
        else if(!(user.getHistory().containsKey(newEntry))) {
            message = "error -> " + newEntry +" is not seen";
        }
        else{
           user.getFavourite().add(newEntry);

           if (user.getFavourite().contains(newEntry)) {
               message = "success -> "+ newEntry +" was added as favourite";
               // nu sunt sigura
               this.noFavorite++;
           }
        }
        return message;
   }

    public String view(User user){
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

        message = "success -> " + viewed +  " was viewed with total views of " + no;
        return message;
    }

    public abstract double computeAvgRating();

}
