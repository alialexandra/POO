package action;

import entertainment.Movie;
import entertainment.Season;
import entertainment.Show;
import entertainment.Video;
import users.User;
import common.Constants;

// am terminat cu comenzile, kind of
public class Command {

    private int id;
    private String type;// rating, view, favorite
    private String message; //mesajul care va fi scris in output
    // mai am user si numele filmului, dar cred ca le aleg la parsare??
    private double grade;
    private int season;
    private String title;
    private String username;


    public Command(int id, String type, double grade, int season, String username,
                    String title) {
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

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    // execute the given command
    // incerc cu video deocamdata
    // dar trebuie sa am grija cand fac citirea sa apelez ce trebuie
    // daca n am rate de facut dau double cu - sau ceva

    public void rate(Movie movie, User user) {


        String entry = movie.getName();
        String userName = user.getUsername();

        if(this.grade != 0) {
            if (entry != null && userName != null) {
                if (!(user.getHistory().containsKey(entry))) {
                    this.message = "error -> " + entry + " is not seen";
                }

                // nu e binee
                else if (movie.getRatings().containsValue(userName)) {
                    this.message = "error -> " + entry + " is already rated";
                } else {
                    //this.setRating(rate);
                    //nope setRated(true);
                    user.getRated().add(entry);
                    movie.getRatings().put(this.grade, userName);
                    this.message = "success -> " + entry + " was rated with " +
                            this.grade + " by " + user.getUsername();
                }
            }
        }

    }
    public void rate(Show show, User user) {

        if(this.grade != 0 && season != 0) {
            String entry = show.getName();
            String userName = user.getUsername();

            // aici e aceeasi poveste ca la vazut, ca fiecare sezon
            // poate fi rated doar o singura data

            Season currSeason = show.getSeasons().get(season - 1);
            int id = currSeason.getCurrentSeason();
            // nu stiu daca e redundant sau daca era suficeient
            // doar sa am get season si atat...

            if (!(user.getHistory().containsKey(entry))) {
                message = "error -> " + entry + " is not seen";
            }

            // nu stiu daca merge... :(
            else if (currSeason.getRatings().containsValue(userName)) {
                this.message = "error -> " + entry + " is already rated";
            } else {
            /*currSeason.setRating(rate);
            currSeason.setRated(true);*/
                if (currSeason.isRated() == false)
                    currSeason.setRated(true);
                currSeason.getRatings().put(this.grade, userName);
                user.getRated().add(show.getName());
                this.message = "success -> " + entry + " was rated with " + this.grade + " by " + user.getUsername();
            }
        }

    }
    //only for view and favourite
    // nu e buna ptc o sa am probleme din cauza ipului video
    public void execute(User user, Video video){


        if(user != null && video != null) {
            //if urile sunt viata mea
            if(this.type.equals(Constants.VIEW))
                this.message = video.view(user);
            else if (this.type.equals(Constants.FAVORITE))
                this.message = video.addFavourite(user);

            }
        }
    }

