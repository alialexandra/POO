package action;

import actor.Actor;
import comparators.*;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import users.User;

import java.util.*;
import java.util.stream.Collectors;

public class VideoQuery extends Query {

    // aceeasi chestie ca la actori nu stiu daca sa adaug si niste campuri p
    // pentru listele de care am nevoie
    public VideoQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }


    public List<Video> ratingsList(List<Video> videos){

        List<Video> sorted = new ArrayList<>();
        for (Video v: sorted) {
            v.setRatingAverage(v.computeAvgRating());

            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);


            if (v.getGenres().contains(genre) &&
                    String.valueOf(v.getYear()).equals(year)&&
                    v.getRatingAverage() != 0)
            {
                sorted.add(v);
            }
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

    private void computeFavFreq(Video v, List<User> users){
        int counts = 0;

        for (User user:
             users) {
            for (String s:
                    user.getFavourite()) {
                if (v.getName().equals(s)){
                    counts++;
                }
            }
        }

        v.setNoFavorite(counts);
    }
    // done
    public List<Video> favoriteList(List<Video> videos, List<User> users) {

        //sortare dupa cat

        List<Video> sorted = new ArrayList<>();

        for (Video v:
             videos) {
            computeFavFreq(v, users);
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);


            if (v.getGenres().contains(genre) &&
                String.valueOf(v.getYear()).equals(year)&&
                v.getNoFavorite() != 0)
            {
                sorted.add(v);
            }
        }

        VideoFavComparator cmp = new VideoFavComparator();

        if (this.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(super.getNumber() < sorted.size()){// <= ??

            List<Video> first;
            first = sorted.stream().limit(this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;

    }


    private void computeViewFreq(Video v, List<User> users){
        int counts = 0;

        for (User user:
                users) {

            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                if (v.getName().equals(entry.getKey())){
                    counts += entry.getValue();
                }
            }
        }

        v.setNoViews(counts);
    }

    public List<Video> mostViewedList(List<Video> videos, List<User> users){

        List<Video> sorted = new ArrayList<>();

        for (Video v:
             videos) {
            computeViewFreq(v,users);

            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            if (v.getGenres().contains(genre) &&
                    String.valueOf(v.getYear()).equals(year) &&
                    v.getNoViews() != 0)
            {
                sorted.add(v);
            }
        }

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
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            if (v.getGenres().contains(genre) &&
                    String.valueOf(v.getYear()).equals(year))
            {
                sorted.add(v);
            }
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
