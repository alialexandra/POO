package action;

import actor.Actor;
import comparators.*;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class VideoQuery extends Query {

    // aceeasi chestie ca la actori nu stiu daca sa adaug si niste campuri p
    // pentru listele de care am nevoie
    public VideoQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }


    public List<Video> ratingsList(List<Video> videos){


        List<Video> sorted = videos;
        for (Video v: sorted) {
            v.setRatingAverage(v.computeAvgRating());
        }

        //daca am timp il rezolv
        VideoRatingComp cmp = new VideoRatingComp();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.getNumber() < sorted.size()){// <= ??
            List<Video> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }

        return sorted;
    }

    // varinata diferita pentru favorite, in sensul ca
    // incrementez de fiecare data cand un video este adaugat cu succes la
    // lista de favorites a unui user

    public List<Video> favoriteList(List<Video> videos) {

        //sortare dupa cat
        List<Video> sorted = videos;


        VideoFavComparator cmp = new VideoFavComparator();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.getNumber() < sorted.size()){// <= ??
            List<Video> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;

    }



    public List<Video> mostViewedList(List<Video> videos){

        List<Video> sorted = videos;

        ViewsComparator cmp = new ViewsComparator();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.getNumber() < sorted.size()){// <= ??
            List<Video> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }

    public List<Video> longestList(List<Video> videos){

        List<Video> sorted = new ArrayList<>();

        for (Video v:
             videos) {
            v.setDurationVideo(v.computeDuration());
            sorted.add(v);
        }

        DurationComp cmp = new DurationComp();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.getNumber() < sorted.size()){// <= ??
            List<Video> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }


}
