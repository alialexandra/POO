package action;

import comparators.BestRatingComp;
import comparators.FavoriteComp;
import entertainment.Video;
import users.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Recommend {

    private int id;
    private String type;
    private String username;

    public Recommend(final int id, final String type,
                     final String username) {
        this.id = id;
        this.type = type;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     *
     * @param videos
     * @return
     */
    public Video standard(final List<Video> videos,
                          final User user) {
        Video found = null;

        for (Video v
                : videos) {
            if (!user.getHistory().containsKey(v.getName())) {
               found = v;
               break;
            }
        }
        return found;

    }

    /**
     *
     * @param videos
     * @return
     */
    public Video bestUnseen(final List<Video> videos,
                            final User user) {

        ArrayList<Video> unseen = new ArrayList<>();

        for (Video v
                : videos) {
            if (!user.getHistory().containsKey(v.getName())) {
                unseen.add(v);
            }
        }
        BestRatingComp cmp = new BestRatingComp();

        unseen.sort(Collections.reverseOrder(cmp));

        if (!unseen.isEmpty()) {
            return unseen.get(0);
        }
        return null;
    }

    /**
     *
     * @param videos
     * @return
     */
    public Video popular(final List<Video> videos) {
        return null;
    }

    /**
     *
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
     * @return
     */
    public Video favorite(final List<Video> videos,
                          final User user,
                          final List<User> users) {

        ArrayList<Video> unseen = new ArrayList<>();

        for (Video v
                : videos) {
            if (!user.getHistory().containsKey(v.getName())) {
                computeFavFreq(v, users);
                unseen.add(v);
            }
        }

        FavoriteComp cmp = new FavoriteComp();
        unseen.sort(Collections.reverseOrder(cmp));

        if (!unseen.isEmpty()) {
            return unseen.get(0);
        }
        return null;

    }

    /**
     *
     * @param videos
     * @return
     */
    public List<Video> search(final List<Video> videos) {
        return null;
    }


    /**
     * make the recommendation based on its type
     * @param type
     */
    public String execute(final String type) {
        return null;
    }


}
