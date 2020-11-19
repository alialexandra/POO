package video;

import java.util.ArrayList;
import java.util.List;

public  class Video {

    /**
        Parent class for the Movie and Shows
        they have common inputs
     */
    // ma gandesc daca sa mai adaug si un id
    // in care sa retin si al catelea a intrat filmul
    // sau sa fac un hash map direct la instantiere?

    private int id;
    private String name;
    private int year;
    private ArrayList<String> genres;
    private ArrayList<String> cast;
    private boolean rated;



    public Video(int id, String name, int year,
                 ArrayList<String> genres, ArrayList<String> cast){
        this.id = id;
        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    // TODO: view, favourite, rating


    // cred ca se poate implemneta oricum
   /* public abstract void favourite();

    // cred ca se poate implementa aici
    public abstract void view();

    public abstract void rating();*/





}
