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
    private Map<String, Integer> history;
    private ArrayList<String> favourite;


    public User(String username, String sub, Map<String, Integer> history,
                ArrayList<String> favourite) {
        this.username = username;
        this.sub= sub;
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

    public Map<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(Map<String, Integer> history) {
        this.history = history;
    }

    public ArrayList<String> getFavourite() {
        return favourite;
    }

    public void setFavourite(ArrayList<String> favourite) {
        this.favourite = favourite;
    }
}
