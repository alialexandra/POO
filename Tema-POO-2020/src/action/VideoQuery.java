package action;

import actor.Actor;
import comparators.RatingComparator;
import comparators.VideoRatingComp;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import users.User;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VideoQuery extends Query {

    // aceeasi chestie ca la actori nu stiu daca sa adaug si niste campuri p
    // pentru listele de care am nevoie
    public VideoQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    // add specific methods for query made for videos

    public List<Video> computeRatings(List<Video> videos){
        //poate e in plus asta, idk

        List<Video> sorted = videos;
        for (Video v: sorted) {
            v.setRatingAverage(v.computeAvgRating());
        }

        VideoRatingComp cmp = new VideoRatingComp();

        if (super.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(super.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(super.getNumber() < sorted.size()){// <= ??
            List<Video> first;
            first = sorted.stream().limit(super.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }

    // varinata diferita pentru favorite, in sensul ca
    // incrementez de fiecare data cand un video este adaugat cu succes la
    public List<Video> computeFavorite(List<User> users, List<Video> videos) {

    }
}
