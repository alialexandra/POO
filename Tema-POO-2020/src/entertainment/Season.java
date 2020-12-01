package entertainment;

import java.util.LinkedHashMap;
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
     * check if the season is rated
     */
    private boolean rated;
    /**
     * ratings for each season
     */
    private Map<String, Double> ratings = new LinkedHashMap<>();

    /**
     *
     * @param currentSeason
     * @param duration
     */
    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.rated = false;

    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public void setRated(final boolean rated) {
        this.rated = rated;
    }

    public boolean isRated() {
        return rated;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setRatings(final Map<String, Double> ratings) {
        this.ratings = ratings;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

    /**
     *
     * @param name
     * @param grade
     */
    public void setRating(final String name, final double grade) {
        this.ratings.put(name, grade);
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

