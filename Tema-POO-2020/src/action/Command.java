package action;

import entertainment.Movie;
import entertainment.Season;
import entertainment.Show;
import entertainment.Video;
import users.User;
import common.Constants;

public final class Command {

    private int id;
    private String type;
    private String message;
    private double grade;
    private int season;
    private String title;
    private String username;


    public Command(final int id, final String type, final double grade,
                   final int season, final String username,
                   final String title) {
        this.id = id;
        this.type = type;
        this.message = null;
        this.grade = grade;
        this.season = season;
        this.username = username;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(final double grade) {
        this.grade = grade;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(final int season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * rate method specific for a movie
     * @param movie to be rated
     * @param user that gives a rating
     */

    public void rate(final Movie movie, final User user) {


        String entry = movie.getName();
        String userName = user.getUsername();

        if (this.grade != 0) {
            if (entry == null || userName == null) {
                return;
            }
            if (!(user.getHistory().containsKey(entry))) {
                this.message = "error -> " + entry + " is not seen";
            } else
            if (movie.getRatings().containsKey(userName)) {
                this.message = "error -> " + entry
                        + " has been already rated";
            } else {
                user.addRated(entry);
                movie.setRating(userName, this.grade);
                this.message = "success -> " + entry + " was rated with "
                        + this.grade + " by " + user.getUsername();

            }
        }

    }

    /**
     * similar to the method above, but for shows
     * we need another method because a show has seasons that can be rated
     * or not
     * @param show
     * @param user
     */
    public void rate(final Show show, final User user) {

        if (this.grade != 0 && season != 0) {
            String entry = show.getName();
            String userName = user.getUsername();
            Season currSeason = show.getSeasons().get(season - 1);

            if (!(user.getHistory().containsKey(entry))) {
                message = "error -> " + entry + " is not seen";
            } else
            if (currSeason.getRatings().containsKey(userName)) {
                this.message = "error -> " + entry + " is already rated";
            } else {

                if (!currSeason.isRated()) {
                    currSeason.setRated(true);
                }
                currSeason.setRating(userName, this.grade);
                user.addRated(entry);
                this.message = "success -> "
                        + entry + " was rated with " + this.grade
                        + " by " + user.getUsername();

            }
        }
    }

    /**
     * parser for view and favorite methods
     * @param user
     * @param video
     */
    public void execute(final User user, final Video video) {

        if (user != null && video != null) {
            if (this.type.equals(Constants.VIEW)) {
                this.message = video.view(user);
            }
            if (this.type.equals(Constants.FAVORITE)) {
                this.message = video.addFavourite(user);
            }
        }
    }

}

