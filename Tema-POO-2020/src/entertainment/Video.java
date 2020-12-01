package entertainment;

import users.User;
import java.util.List;

public abstract class Video {

    /**
        Parent class for the Movie and Shows
        because they have common structure
     */

    private String name;
    private int year;
    private List<String> genres;
    private List<String> cast;
    private double ratingAverage;
    private int noFavorite;
    private int noViews;
    private int durationVideo;
    private int id;
    private int genreView;


    public Video(final String name, final int year,
                 final List<String> genres, final List<String> cast,
                 final int id) {

        this.name = name;
        this.year = year;
        this.genres = genres;
        this.cast = cast;
        this.id = id;
        this.ratingAverage = 0;
        this.noFavorite = 0;
        this.noViews = 0;
        this.durationVideo = 0;
        this.genreView = 0;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * @param year
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     *
     * @return
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     *
     * @param genres
     */
    public void setGenres(final List<String> genres) {
        this.genres = genres;
    }

    /**
     *
     * @return
     */
    public List<String> getCast() {
        return cast;
    }

    /**
     *
     * @param cast
     */
    public void setCast(final List<String> cast) {
        this.cast = cast;
    }

    /**
     *
     * @return
     */
    public double getRatingAverage() {
        return ratingAverage;
    }

    /**
     *
     * @param ratingAverage
     */
    public void setRatingAverage(final double ratingAverage) {
        this.ratingAverage = ratingAverage;
    }

    /**
     *
     * @return
     */
    public int getNoFavorite() {
        return noFavorite;
    }

    /**
     *
     * @param noFavorite
     */
    public void setNoFavorite(final int noFavorite) {
        this.noFavorite = noFavorite;
    }

    /**
     *
     * @return
     */
    public int getNoViews() {
        return noViews;
    }

    /**
     *
     * @param noViews
     */
    public void setNoViews(final int noViews) {
        this.noViews = noViews;
    }

    /**
     *
     * @return
     */
    public int getDurationVideo() {
        return durationVideo;
    }

    /**
     *
     * @param durationVideo
     */
    public void setDurationVideo(final int durationVideo) {
        this.durationVideo = durationVideo;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    public int getGenreView() {
        return genreView;
    }

    public void setGenreView(int genreView) {
        this.genreView = genreView;
    }

    /**
     * @param user
     * add a video to the favourites for user
     * return the specific message for the action
     */
   public String addFavourite(final User user) {

        String newEntry = this.getName();
        String message = null;

        if (user.getFavourite().contains(newEntry)) {
            message = "error -> " + newEntry + " is already in favourite list";
        } else
        if (!(user.getHistory().containsKey(newEntry))) {
            message = "error -> " + newEntry + " is not seen";
        } else {
           user.getFavourite().add(newEntry);

           if (user.getFavourite().contains(newEntry)) {
               message = "success -> " + newEntry + " was added as favourite";
           }
        }
        return message;
   }

    /**
     * add new viewed video to the user's history
     * @param user views a video
     * @return specific message for a command
     */
    public String view(final User user) {
        String viewed = this.getName();
        String message;

        if (user == null) {
            return null;
        }
        if (user.getHistory().containsKey(viewed)) {
            user.getHistory().put(viewed, user.getHistory().get(viewed) + 1);

        } else {
            user.getHistory().putIfAbsent(viewed, 1);
        }
        int no  = user.getHistory().get(viewed);
        message = "success -> " + viewed +  " was viewed with total views of " + no;
        return message;
    }

    /**
     *
     * @return
     */
    public abstract double computeAvgRating();

    /**
     *
     * @return
     */
    public abstract int computeDuration();

}
