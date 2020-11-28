package entertainment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    // pentru mine cred ca ar fi mai usor
    // sa fie un map cu sezonul si rate ul lui
    // mie mi se pare mmult mai ok sa am un double simplu
    // vad la citire daca ramane asa, nu stiu ce sa zic sincer
    //private double rating;
    private boolean rated;
    private Map<Double, String> ratings = new LinkedHashMap<>();

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.rated = false;// initially
        //this.ratings = new ArrayList<>();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

  /*  public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(final List<Double> ratings) {
        this.ratings = ratings;
    }*/

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public boolean isRated() {
        return rated;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setRatings(Map<Double, String> ratings) {
        this.ratings = ratings;
    }

    public Map<Double, String> getRatings() {
        return ratings;
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}

