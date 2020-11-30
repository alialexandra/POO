package action;

import actor.Actor;
import actor.ActorsAwards;
import comparators.AwardComparator;
import comparators.NameComparator;
import comparators.RatingComparator;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import utils.Utils;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ActorsQuery extends Query{

    // nu stiua daca la creere sa adaug si listele de actori si video de care am nevoie

    public ActorsQuery(int id, int number, String objectType, String sortCriteria, String criteria, List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    // average for actors - finished

    public List<Actor> averageList(List<Actor>actors, List<Video> videos){
        // better safe than sorry
        List<Actor> sortActors = actors;


        for (Actor actor:
                sortActors) {
            actor.computeAverage(videos);
        }


        RatingComparator cmp = new RatingComparator();

        if (super.getSortCriteria().equals("asc")){
            Collections.sort(sortActors, cmp);
        }
        else if(super.getSortCriteria().equals("desc"))
            Collections.sort(sortActors, Collections.reverseOrder(cmp));

        //List<String> firstNElementsList = list.stream().limit(n).collect(Collectors.toList())
        if(super.getNumber() < sortActors.size()){// <= ??
            List<Actor> first;
            first = sortActors.stream().limit(super.getNumber()).collect(Collectors.toList());
            return first;
        }

        return sortActors;
    }

    public List<Actor> awardsList(List<Actor> actors) {

        List<String> awards = super.getFilter().get(3);// sau 3
        List<Actor> sortActors = new ArrayList<>();

        List<ActorsAwards> awards1 = new ArrayList<>();

        for (String s:
             awards) {
            awards1.add(Utils.stringToAwards(s));
        }
        for (Actor actor: actors){

            if(actor.getAwards().keySet().containsAll(awards1)){
                sortActors.add(actor);
            }
        }
        AwardComparator cmp = new AwardComparator();

        if (super.getSortCriteria().equals("asc")){
            Collections.sort(sortActors, cmp);
        }
        else if(super.getSortCriteria().equals("desc"))
            Collections.sort(sortActors, Collections.reverseOrder(cmp));

        return sortActors;

    }

    public List<Actor> filterList(List<Actor> actors){

        Map<String, Boolean> keyWordsFreq = new LinkedHashMap<>();

        List<String> keyWords = super.getFilter().get(2);
        List<Actor> sorted = new ArrayList<>();

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

        if (super.getSortCriteria().equals("asc")){
            Collections.sort(sorted, cmp);
        }
        else if(super.getSortCriteria().equals("desc"))
            Collections.sort(sorted, Collections.reverseOrder(cmp));

        return sorted;
    }


}
