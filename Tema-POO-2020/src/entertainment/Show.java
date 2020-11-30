package entertainment;

import users.User;

import java.util.*;

public class Show extends Video {

    private int noSeasons;
    private ArrayList<Season> seasons;
    private Map<Season, Integer> seen = new LinkedHashMap<>();
    //private Map<Season, Double> ratings; // ratings pe sezon


    public Show(String name, int year, List<String> genres, List<String> cast,
                int noSeasons, ArrayList<Season> seasons) {
        super(name, year, genres, cast);
        this.noSeasons = noSeasons;
        this.seasons = seasons;

    }

    public int getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(int noSeasons) {
        this.noSeasons = noSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons = seasons;
    }


    public Map<Season, Integer> getSeen() {
        return seen;
    }

    public void setSeen(Map<Season, Integer> seen) {
        this.seen = seen;
    }




    @Override
    public double computeAvgRating() {

        double average = 0;
        boolean rated = false;

        for (Season s:
             this.seasons) {
            if (s.isRated()){
                rated = true;
                break;
            }
        }
        // daca cel putin un sezon a primit rating
        if (rated) {
            for (Season s:
                 this.seasons) {
                if (!(s.getRatings().isEmpty())){
                    for (Double d: s.getRatings().values()){
                        average += d;
                    }
                }
            }
            average /= this.noSeasons;
        }
        // inca ma gandesc daca le mai las
        setRatingAverage(average);
        return average;
    }

    @Override
    public int computeDuration() {
        int d = 0;
        for (Season s:
             this.seasons) {
            d += s.getDuration();
        }

        return d;
    }
}

