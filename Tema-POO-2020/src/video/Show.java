package video;

import entertainment.Season;

import java.util.*;

public class Show extends Video {

    private int noSeasons;
    private ArrayList<Season> seasons;

    public Show(int id, String name, int year, ArrayList<String> genres, ArrayList<String> cast,
                int noSeasons, ArrayList<Season> seasons) {
        super(id, name, year, genres, cast);
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

    //TODO: add methods for shows
}
