package video;

import java.util.*;
import java.util.List;

public class Movie  extends Video {

    private int duration;

    public Movie(int id, String name, int year, ArrayList<String> genres, ArrayList<String> cast, int duration) {
        super(id, name, year, genres, cast);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /** TODO: add methods
     * */
}
