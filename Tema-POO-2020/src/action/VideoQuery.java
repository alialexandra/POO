package action;


import comparators.VideoFavComparator;
import comparators.ViewsComparator;
import comparators.VideoRatingComp;
import comparators.DurationComp;
import entertainment.Video;
import users.User;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

public final class VideoQuery extends Query {

    public VideoQuery(final int id, final int number,
                      final String objectType, final  String sortCriteria,
                      final String criteria,
                      final List<List<String>> filter) {
        super(id, number, objectType, sortCriteria, criteria, filter);
    }

    /**
     * get the first N videos sorted by ratings
     * the methods above are similar to this one
     * @param videos
     * @return
     */
    public List<Video> ratingsList(final List<Video> videos) {

        List<Video> sorted = new ArrayList<>();
        for (Video v
                : videos) {
            v.setRatingAverage(v.computeAvgRating());
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            if (v.getGenres().contains(genre)
                    && String.valueOf(v.getYear()).equals(year)
                    && v.getRatingAverage() != 0) {
                sorted.add(v);
            }
        }


        VideoRatingComp cmp = new VideoRatingComp();

        if (this.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        } else
        if (this.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        if (this.getNumber() < sorted.size()) {
            List<Video> first;
            first = sorted.stream().limit(
                    this.getNumber()).collect(Collectors.toList());
            return first;
        }

        return sorted;
    }

    /**
     * compute the frequency of every video
     * in the users lists of favorites
     * @param v
     * @param users
     */
    private void computeFavFreq(final Video v, final List<User> users) {
        int counts = 0;
        for (User user
                : users) {
            for (String s
                    : user.getFavourite()) {
                if (v.getName().equals(s)) {
                    counts++;
                }
            }
        }
        v.setNoFavorite(counts);
    }

    /**
     *
     * @param videos
     * @param users
     * @return
     */
    public List<Video> favoriteList(final List<Video> videos,
                                    final List<User> users) {

        List<Video> sorted = new ArrayList<>();

        for (Video v
                : videos) {
            computeFavFreq(v, users);
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            /**
             * filter by year and genre
             */
            if (v.getGenres().contains(genre)
                    && String.valueOf(v.getYear()).equals(year)
                    && v.getNoFavorite() != 0) {
                sorted.add(v);
            }
        }

        VideoFavComparator cmp = new VideoFavComparator();

        if (this.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        } else
        if (this.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        if (super.getNumber() < sorted.size()) {
            List<Video> first;
            first = sorted.stream().limit(
                    this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }


    private void computeViewFreq(final Video v,
                                 final List<User> users) {
        int counts = 0;

        for (User user
                : users) {

            for (Map.Entry<String, Integer> entry
                    : user.getHistory().entrySet()) {
                if (v.getName().equals(entry.getKey())) {
                    counts += entry.getValue();
                }
            }
        }
        v.setNoViews(counts);
    }

    /**
     *
     * @param videos
     * @param users
     * @return
     */
    public List<Video> mostViewedList(final List<Video> videos,
                                      final List<User> users) {

        List<Video> sorted = new ArrayList<>();

        for (Video v
                : videos) {

            computeViewFreq(v, users);
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            if (v.getGenres().contains(genre)
                    && String.valueOf(v.getYear()).equals(year)
                    && v.getNoViews() != 0) {
                sorted.add(v);
            }
        }

        ViewsComparator cmp = new ViewsComparator();

        if (this.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        } else
        if (this.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        if (this.getNumber() < sorted.size()) {
            List<Video> first;
            first = sorted.stream().limit(
                    this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }

    /**
     *
     * @param videos
     * @return
     */
    public List<Video> longestList(final List<Video> videos) {

        List<Video> sorted = new ArrayList<>();

        for (Video v
                : videos) {
            v.setDurationVideo(v.computeDuration());
            String genre = super.getFilter().get(1).get(0);
            String year = super.getFilter().get(0).get(0);

            if (v.getGenres().contains(genre)
                    && String.valueOf(v.getYear()).equals(year)) {
                sorted.add(v);
            }
        }

        DurationComp cmp = new DurationComp();
        if (this.getSortCriteria().equals("asc")) {
            Collections.sort(sorted, cmp);
        } else
        if (this.getSortCriteria().equals("desc")) {
            Collections.sort(sorted, Collections.reverseOrder(cmp));
        }
        if (this.getNumber() < sorted.size()) {
            List<Video> first;
            first = sorted.stream().limit(
                    this.getNumber()).collect(Collectors.toList());
            return first;
        }
        return sorted;
    }
}
