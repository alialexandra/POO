package actor;

import entertainment.Movie;
import entertainment.Season;
import entertainment.Show;

import java.util.List;
import java.util.Map;


// as putea sa ma folosesc de clasele facute pentru citit informatia
// idk, parca e cam in plus
public class Actor {

    private String name;
    private String career;
    private List<String> filmography;
    private Map<String, Integer> awards;// nu stiu cum sa fac cu Actor Awards
    private double average;
    private int noAwards;

    public Actor(String name, String career,
                 List<String> filmography,
                Map<String, Integer> awards){
        this.name = name;
        this.awards = awards;
        this.career = career;
        this.filmography = filmography;
        this.average = 0;
        this.noAwards = awards.size();
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

    public List<String> getFilmography() {
        return filmography;
    }
// o sa vine alta tipuri la instantiere

    public void setFilmography(List<String> filmography) {
        this.filmography = filmography;
    }

    public Map<String, Integer> getAwards() {
        return awards;
    }

    public void setAwards(Map<String, Integer> awards) {
        this.awards = awards;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getNoAwards() {
        return noAwards;
    }

    public void setNoAwards(int noAwards) {
        this.noAwards = noAwards;
    }

    // metode pentru actori
    // sortare dupa rating

    public void computeAverage(List<Movie> movies, List<Show> shows){

        double averageMovie = 0, averageShow = 0;
        double average = 0;
        int movieCount = 0, showCount = 0;

        for (Movie m : movies){
            if(this.filmography.contains(m) && !(m.getRatings().isEmpty())){
                movieCount++;
                for (Double d : m.getRatings().keySet())
                {
                    if(d != 0)
                        averageMovie += d;
                }
                averageMovie /= m.getRatings().size();
                average += averageMovie;
            }
        }


        for (Show s : shows) {
            if(this.filmography.contains(s)){
                showCount++;
            for (Season season:s.getSeasons()) {
                // if the actor has played in the specific show

                if(season.isRated()){
                    for (Double d:season.getRatings().keySet()) {
                        averageShow += d;
                    }
                }else{
                    averageShow += 0;
                }
            }
            averageShow /= s.getNoSeasons();
            average += averageShow;
            }

        }

        if(movieCount != 0 && showCount != 0)
        {
            this.average = average/(movieCount + showCount);
        }
    }

}
