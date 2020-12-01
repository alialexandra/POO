package action;

import entertainment.Video;

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
    public Video standard(final List<Video> videos) {
        return null;
    }

    /**
     *
     * @param videos
     * @return
     */
    public Video bestUnseen(final List<Video> videos) {
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
     * @param videos
     * @return
     */
    public Video favorite(final List<Video> videos) {
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


}
