package actor;

import java.util.ArrayList;
import java.util.List;

public class Actor {

    private String name;
    private String career;
    private ArrayList<String> filmography;
    private ArrayList<String> awards;

    public Actor(String name, String career,
                 ArrayList<String> filmography,
                ArrayList<String> awards){
        this.name = name;
        this.awards = awards;
        this.career = career;
        this.filmography = filmography;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public ArrayList<String> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<String> awards) {
        this.awards = awards;
    }



    // metode pentru actori



}
