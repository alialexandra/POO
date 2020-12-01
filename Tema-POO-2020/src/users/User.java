package users;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

/**
 * Class for users
 * */
public final class User {

    private String username;
    private String sub;
    private LinkedHashMap<String, Integer> history;
    private List<String> favourite;
    private ArrayList<String> rated = new ArrayList<>();


    public User(final String username, final String sub,
                final LinkedHashMap<String, Integer> history,
                final List<String> favourite) {
        this.username = username;
        this.sub = sub;
        this.history = history;
        this.favourite = favourite;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getSub() {
        return sub;
    }

    /**
     *
     * @param sub
     */
    public void setSub(final String sub) {
        this.sub = sub;
    }

    /**
     *
     * @return
     */
    public LinkedHashMap<String, Integer> getHistory() {
        return history;
    }

    /**
     *
     * @param history
     */
    public void setHistory(final LinkedHashMap<String, Integer> history) {
        this.history = history;
    }

    /**
     *
     * @return
     */
    public List<String> getFavourite() {
        return favourite;
    }

    /**
     *
     * @param favourite
     */
    public void setFavourite(final List<String> favourite) {
        this.favourite = favourite;
    }

    /**
     *
     * @param ratedMovies
     */
    public void setRated(final ArrayList<String> ratedMovies) {
        this.rated = ratedMovies;
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getRated() {
        return rated;
    }

    /**
     *
     * @param v
     */
    public void addRated(final String v) {
        this.rated.add(v);
    }
}
