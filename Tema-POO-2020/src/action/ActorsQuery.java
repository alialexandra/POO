package action;

import actor.Actor;
import actor.ActorsAwards;
import comparators.AwardComparator;
import comparators.NameComparator;
import comparators.RatingComparator;
import entertainment.Video;
import utils.Utils;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ActorsQuery extends Query {


    public ActorsQuery(final int id, final int number,
                       final String objectType, final String sortCriteria,
                       final String criteria, final List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    /**
     * method that sorts the actors by average rating
     * @param actors list of all actors
     * @param videos list o all videos in database
     * @return the N actors sorted by average rating
     */
    public List<Actor> averageList(final List<Actor> actors, final List<Video> videos) {

        List<Actor> sortActors = new ArrayList<>();

        for (Actor actor
                : actors) {
            actor.computeAverage(videos);
            if (actor.getAverage() != 0) {
                sortActors.add(actor);
            }
        }

        RatingComparator cmp = new RatingComparator();

        if (super.getSortCriteria().equals("asc")) {
            Collections.sort(sortActors, cmp);
        }
        if (super.getSortCriteria().equals("desc")) {
            Collections.sort(sortActors, Collections.reverseOrder(cmp));
        }

        if (super.getNumber() < sortActors.size()) {
            List<Actor> first;
            first = sortActors.stream().limit(super.getNumber()).collect(Collectors.toList());
            return first;
        }

        return sortActors;
    }

    /**
     * similar with the method before
     * the sorting criteria differs
     * @param actors
     * @return
     */
    public List<Actor> awardsList(final List<Actor> actors) {

        List<String> awards = super.getFilter().get(getFilter().size() - 1);
        List<Actor> sortActors = new ArrayList<>();
        List<ActorsAwards> awards1 = new ArrayList<>();

        for (String s
                : awards) {
            awards1.add(Utils.stringToAwards(s));

        }
        for (Actor actor
                : actors) {

            if (actor.getAwards().keySet().containsAll(awards1)) {
                sortActors.add(actor);
            }
        }

        AwardComparator cmp = new AwardComparator();

        if (super.getSortCriteria().equals("asc")) {
            Collections.sort(sortActors, cmp);
        }
        if (super.getSortCriteria().equals("desc")) {
            Collections.sort(sortActors, Collections.reverseOrder(cmp));
        }
        return sortActors;

    }

    /**
     *
     * @param actors
     * @return list of actors that contains the
     * given key-words in their career description
     */
    public List<Actor> filterList(final List<Actor> actors) {

        Map<String, Boolean> keyWordsFreq = new LinkedHashMap<>();

        List<String> keyWords = super.getFilter().get(getFilter().size() - 2);
        List<Actor> sorted = new ArrayList<>();

        for (Actor actor: actors) {
            for (String s : keyWords) {
                keyWordsFreq.put(s, false);
                if (Pattern.compile(Pattern.quote(s),
                        Pattern.CASE_INSENSITIVE).matcher(actor.getCareer()).find()) {
                    keyWordsFreq.put(s, true);
                }
            }

            HashSet<Boolean> values = new HashSet<>(keyWordsFreq.values());
            boolean isUnique = values.size() == 1 && values.contains(true);

            if (isUnique) {
                sorted.add(actor);
            }
        }

        NameComparator cmp = new NameComparator();

        if (super.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        }
        if (super.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        return sorted;
    }


}
