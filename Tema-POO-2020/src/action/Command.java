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


    public Command(int id, String type) {
        this.id = id;
        this.type = type;
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

    // execute the given command
    // incerc cu video deocamdata
    // dar trebuie sa am grija cand fac citirea sa apelez ce trebuie
    // daca n am rate de facut dau double cu - sau ceva

    public void rate(Movie movie, User user, double rate) {


        String entry = movie.getName();
        String userName = user.getUsername();

        if(entry != null && userName != null) {
            if (!(user.getHistory().containsKey(entry))) {
                this.message = "error -> " + entry + " is not seen\n";
            }

            // nu e binee
            else if (movie.getRatings().containsValue(userName)) {
                this.message = "error -> " + entry + " is already rated\n";
            } else {
                //this.setRating(rate);
                //nope setRated(true);
                user.getRatedMovies().add(entry);
                movie.getRatings().put(rate, userName);
                this.message = "success -> " + entry + " was rated with " + rate + " by " + user.getUsername() + "\n";
            }
        }

    }
    public void rate(Show show, User user, double rate, int season) {

        String entry = show.getName();
        String userName = user.getUsername();

        // aici e aceeasi poveste ca la vazut, ca fiecare sezon
        // poate fi rated doar o singura data

        Season currSeason = show.getSeasons().get(season);
        int id = currSeason.getCurrentSeason();
        // nu stiu daca e redundant sau daca era suficeient
        // doar sa am get season si atat...

        if (!(user.getHistory().containsKey(entry))){ message = "error -> " + entry + " is not seen\n";}

        // nu stiu daca merge... :(
        else if(currSeason.getRatings().containsValue(userName)){
            this.message = "error -> " + entry +" is already rated\n";
        }
        else{
            /*currSeason.setRating(rate);
            currSeason.setRated(true);*/
            if(currSeason.isRated() == false)
                currSeason.setRated(true);
            currSeason.getRatings().put(rate, userName);
            this.message = "success -> " + entry +" was rated with " + rate +" by " + user.getUsername() +"\n";
        }

    }
    //
    public void execute(User user, Video video, String type, double rate, int season){
        if(user != null && video != null) {
            //if urile sunt viata mea
            if(type.equals(Constants.VIEW))
                this.message = video.view(user);
            else if (type.equals(Constants.FAVORITE))
                this.message = video.addFavourite(user);

            }
        }
    }

