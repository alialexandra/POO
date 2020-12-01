package entertainment;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;

public final class Movie  extends Video {

    private int duration;
    private Map<String, Double> ratings = new LinkedHashMap<>();


    public Movie(final String name, final int year,
                 final List<String> genres, final List<String> cast,
                 final int duration) {
        super(name, year, genres, cast);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public Map<String, Double> getRatings() {
        return ratings;
    }

    public void setRatings(final Map<String, Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * add a new entry in the map specific for ratings
     * @param name
     * @param grade
     */
    public void setRating(final String name, final double grade) {
        this.ratings.put(name, grade);
    }


    /**
     * compute the average rating for a movie
     * @return
     */
    @Override
    public double computeAvgRating() {
        double average = 0;
        double count = 0;

        if (!(ratings.isEmpty())) {

            for (Double d: ratings.values()) {
                if (d != 0) {
                    average += d;
                    count++;
                }
            }
            average /= count;
        }
        setRatingAverage(average);
        return average;
    }

    /**
     *
     * @return
     */
    @Override
    public int computeDuration() {
        return this.duration;
    }
}




