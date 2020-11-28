package entertainment;

import users.User;

import java.util.*;

public class Show extends Video {

    private int noSeasons;
    private List<Season> seasons;
    private Map<Season, Integer> seen;
    //private Map<Season, Double> ratings; // ratings pe sezon


    public Show(int id, String name, int year, List<String> genres, List<String> cast,
                int noSeasons, boolean rated) {
        super(id, name, year, genres, cast);
        this.noSeasons = noSeasons;
        this.seasons = new ArrayList<>(noSeasons);

    }

    public int getNoSeasons() {
        return noSeasons;
    }

    public void setNoSeasons(int noSeasons) {
        this.noSeasons = noSeasons;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }


    public Map<Season, Integer> getSeen() {
        return seen;
    }

    public void setSeen(Map<Season, Integer> seen) {
        this.seen = seen;
    }


    @Override
    public String view(User user) {

        String message = null;

        String viewed = this.getName();
        Set<Integer> values = new HashSet<>(seen.values());

        // extra from the method for movies
        // I have to check if all the seasons have been seen
        // with that exact number I want to increase

        if (user.getHistory().containsKey(viewed)) {

            int noViews = user.getHistory().get(viewed);

            // ma gandesc ca deja sezoanele trebuie sa fie marcate ca vazute
            // o sa fie distractie si cu astea
            // cel mai probabil o sa am nevoie de o metoda
            // de traverse pentru map care sa mi incrementeze numarul de vizionari
            if(values.size() == 1 &&
                this.getSeen().containsValue(noViews + 1))
                user.getHistory().put(viewed, user.getHistory().get(viewed) + 1);
        }
        else{
            // if the show has never been seen
            // mda nu stiu daca o sa setez eu sa fie vazute sezoanele
            // sau mi se cere in input
            // && this.getSeen().containsValue(0) - sa fie pe 0 , dar cred
            // ca e redundanta verifcarea asta
            // oare trebuie sa adaug eu si 1 la vazut toate sezoanele...
            if(values.size() == 1
             && this.getSeen().containsValue(1)) {
                user.getHistory().putIfAbsent(viewed, 1);
                int no  = user.getHistory().get(viewed);
                message = "success -> " + viewed +  " was viewed with total views of " + no + "\n";

            }
        }

        return message;
    }


    /*public String rate(User user, double rate, int season) {
        String message;
        String entry = this.getName();
        String userName = user.getUsername();

        // aici e aceeasi poveste ca la vazut, ca fiecare sezon
        // poate fi rated doar o singura data

        Season currSeason = getSeasons().get(season);
        int id = currSeason.getCurrentSeason();
        // nu stiu daca e redundant sau daca era suficeient
        // doar sa am get season si atat...

        if (!(user.getHistory().containsKey(entry))){ message = "error -> " + entry + " is not seen\n";}

        // nu stiu daca merge... :(
        else if(currSeason.getRatings().containsValue(userName)){
            message = "error -> " + entry +" is already rated\n";
        }
        else{
            *//*currSeason.setRating(rate);
            currSeason.setRated(true);*//*
            currSeason.getRatings().put(rate, userName);
            message = "success -> " + entry +" was rated with " + rate +" by " + user.getUsername() +"\n";
        }

        return message;
    }*/
}

