package action;

import actor.Actor;
import common.Constants;
import comparators.RatingComparator;
import entertainment.Movie;
import entertainment.Show;

import java.util.*;
import java.util.stream.Collectors;

public  class Query {

    // id ul pentru actiunea
    private int id;
    private String objectType;
    private String sortCriteria;//asc sau desc
    private String criteria;
    // sunt doar 4 filtere, vad eu ce se intampla
    private List<List<String>> filter = new ArrayList<>(4);
    private int number;


    public Query(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        this.id = id;
        this.number = number;
        this.objectType = objectType;
        this.sortCriteria = sortCriteria;
        this.criteria = criteria;
        this.filter = filter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getSortCriteria() {
        return sortCriteria;
    }

    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public List<List<String>> getFilter() {
        return filter;
    }

    public void setFilter(List<List<String>> filter) {
        this.filter = filter;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // add specific methods
    public List<Actor> average(List<Actor>actors, List<Movie>movies, List<Show> shows){
        // better safe than sorry
        for (Actor actor:
             actors) {
            actor.computeAverage(movies, shows);
        }

        RatingComparator cmp = new RatingComparator();

        if (sortCriteria.equals("asc")){
            Collections.sort(actors, cmp);
        }
        else
            Collections.sort(actors, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.number <= actors.size()){
            List<Actor> first = actors.stream().limit(number).collect(Collectors.toList());
            return first;
        }
        if (this.number > actors.size())
            return actors;

        return null;
    }


    /*public double computeAvrgMovies(List<String> roles, List<Movie> movies, Actor actor){
        double average = 0;
        for (Movie m: movies){
            if(roles.contains(m) && !(m.getRatings().isEmpty())){

                for (Double d : m.getRatings().keySet())
                {
                    if(d != 0)
                        average += d;
                }
                average /= m.getRatings().size();
            }
        }
        return average;
    }*/

   /* public List<String> computeAverage(List<Actor> actors, List<Movie> movies, List<Show> shows){


        Map<Actor, Double> actorRatings = new LinkedHashMap<>();
        // List<String> videos = new ArrayList<>();
        // pentru fiecare actor extragem
        // filmele in care a jucat sau serialele si
        // luam si lista de ratinguri
        for (Actor actor: actors) {
            // filmele si serialele in care a jucat
            List<String> videos = actor.getFilmography();
            // media filmelor in care a jucat
            double average = computeAvrgMovies(videos, movies, actor);
            // calculeaza si pentru fiecare serial

            average = computeAvrgShows();

            average /= 2;

            actorRatings.putIfAbsent(actor, average);
            }
        }
    }*/
}
