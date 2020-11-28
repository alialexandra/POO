package action;

import actor.Actor;
import comparators.AwardComparator;
import comparators.NameComparator;
import comparators.RatingComparator;
import entertainment.Movie;
import entertainment.Show;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public  class Query {

    // id ul pentru actiunea
    private int id;
    private String objectType;
    private String sortCriteria;//asc sau desc
    private String criteria;
    // sunt doar 4 filtere, vad eu ce se intampla
    private List<List<String>> filter;
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

    // average for actors - finished
    public List<Actor> average(List<Actor>actors, List<Movie>movies, List<Show> shows){
        // better safe than sorry
        List<Actor> sortActors = actors;

        for (Actor actor:
             sortActors) {
            actor.computeAverage(movies, shows);
        }


        RatingComparator cmp = new RatingComparator();

        if (this.sortCriteria.equals("asc")){
            Collections.sort(sortActors, cmp);
        }
        else if(this.sortCriteria.equals("desc"))
            Collections.sort(sortActors, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(this.number < actors.size()){// <= ??
            List<Actor> first;
            first = sortActors.stream().limit(number).collect(Collectors.toList());
            return first;
        }

        return sortActors;
    }

    public List<Actor> getAwarded(List<Actor> actors) {

        List<String> awards = this.filter.get(this.filter.size()-1);// sau 3
        List<Actor> sortActors = new ArrayList<>();
        for (Actor actor: actors){
            if(actor.getAwards().containsAll(awards)){
                sortActors.add(actor);
            }
        }
        AwardComparator cmp = new AwardComparator();

        if (this.sortCriteria.equals("asc")){
            Collections.sort(sortActors, cmp);
        }
        else if(this.sortCriteria.equals("desc"))
            Collections.sort(sortActors, Collections.reverseOrder(cmp));

        return sortActors;

    }

    public List<Actor> getFiltered(List<Actor> actors){

        Map<String, Boolean> keyWordsFreq = new LinkedHashMap<>();

        List<String> keyWords = this.filter.get(this.filter.size()-2);
        List<Actor> sorted = new ArrayList<>();
//Pattern.compile(Pattern.quote(wantedStr), Pattern.CASE_INSENSITIVE).matcher(source).find();

        for (Actor actor: actors) {
            for (String s : keyWords) {
                keyWordsFreq.put(s, false);
                //List<String> match = Arrays.asList(actor.getCareer().split(" "));
                if (Pattern.compile(Pattern.quote(s),
                        Pattern.CASE_INSENSITIVE).matcher(actor.getCareer()).find()){
                    keyWordsFreq.put(s,true);
                }
            }
            HashSet<Boolean> values = new HashSet<>(keyWordsFreq.values());
            boolean isUnique = values.size() == 1 && values.contains(true);

            if (isUnique) {
                sorted.add(actor);
            }
        }
        NameComparator cmp = new NameComparator();

        if (this.sortCriteria.equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(this.sortCriteria.equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        return sorted;



    }
}
