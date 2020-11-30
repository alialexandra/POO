package users;


import java.lang.reflect.Array;
import java.util.*;
import java.util.Map;

/**
 * Class for users
 * */
public class User {

    // nu stiu daca sa adaug si un id..?
    // nu, mai mult la filme
    private String username;
    private String sub;
    private LinkedHashMap<String, Integer> history; // la initializare vreau sa l fac de tip LinkedHashMap ca sa mi mentina ordinea
    private List<String> favourite;
    // acum asta e useless off... viata...
     private List<String> ratedMovies = new ArrayList<>();


    public User(String username, String sub, LinkedHashMap<String, Integer> history,
                List<String> favourite) {
        this.username = username;
        this.sub = sub;
        this.history = history;
        this.favourite = favourite;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public LinkedHashMap<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(LinkedHashMap<String, Integer> history) {
        this.history = history;
    }

    public List<String> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<String> favourite) {
        this.favourite = favourite;
    }

    public void setRatedMovies(List<String> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    public List<String> getRatedMovies() {
        return ratedMovies;
    }
}
