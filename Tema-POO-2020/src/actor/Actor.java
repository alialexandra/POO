package actor;


import entertainment.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public final class Actor {
    private String name;
    private String career;
    private List<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private double average;
    private int noAwards;

    public Actor(final String name, final String career,
                 final List<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
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

    public void setName(final String name) {
        this.name = name;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(final String career) {
        this.career = career;
    }

    public List<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final List<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(final double average) {
        this.average = average;
    }

    public int getNoAwards() {
        return noAwards;
    }

    public void setNoAwards(final int noAwards) {
        this.noAwards = noAwards;
    }


    /**
     *
     * @param names
     * @param videos
     * @return
     */
    private List<Video> videoList(final List<String> names,
                                  final List<Video> videos) {

        List<Video> getVideos = new ArrayList<>();

        for (String s
                : names) {
            for (Video v
                    : videos) {
                if (v.getName().equals(s)) {
                    getVideos.add(v);
                }
            }
        }

        return getVideos;

    }

    /**
     * compute the average rating for an actor
     * @param videos
     */
    public void computeAverage(final List<Video> videos) {

        List<Video> result = videoList(this.filmography, videos);

        double av = 0;
        double count = 0;

        for (Video v
                : result) {
            if (v.computeAvgRating() != 0) {
                av += v.computeAvgRating();
                count++;
            }

        }
        if (count != 0) {
            this.average = av / count;
        }

    }


}
