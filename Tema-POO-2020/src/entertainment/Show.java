package entertainment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Show extends Video {

    private int noSeasons;
    private ArrayList<Season> seasons;
    private Map<Season, Integer> seen = new LinkedHashMap<>();

    public Show(final String name,
                final int year,
                final List<String> genres, final List<String> cast,
                final int noSeasons, final ArrayList<Season> seasons) {
        super(name, year, genres, cast);
        this.noSeasons = noSeasons;
        this.seasons = seasons;

    }

    public int getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(final int noSeasons) {
        this.noSeasons = noSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(final ArrayList<Season> seasons) {
        this.seasons = seasons;
    }

    public Map<Season, Integer> getSeen() {
        return seen;
    }

    public void setSeen(final Map<Season, Integer> seen) {
        this.seen = seen;
    }

    /**
     *
     * @return average rating for the show
     */
    @Override
    public double computeAvgRating() {

        double average = 0;
        boolean rated = false;

        for (Season s
                : this.seasons) {
            if (s.isRated()) {
                rated = true;
                break;
            }
        }
        // check if at least one seasons have been rated
        if (rated) {
            for (Season s
                    : this.seasons) {
                if (!(s.getRatings().isEmpty())) {
                    for (Double d: s.getRatings().values()) {
                        average += d;
                    }
                }
            }
            average /= this.noSeasons;
        }
        setRatingAverage(average);
        return average;
    }

    /**
     *
     * @return total duration of a show
     */
    @Override
    public int computeDuration() {
        int d = 0;

        for (Season s
                : this.seasons) {
            d += s.getDuration();
        }
        return d;
    }
}

