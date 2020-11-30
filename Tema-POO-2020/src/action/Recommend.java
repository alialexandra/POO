package action;

import entertainment.Video;

import java.util.List;

public class Recommend {

    private int id;
    private String type;
    private String username;

    public Recommend(int id, String type, String username) {
        this.id = id;
        this.type = type;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Video standard(List<Video> videos){
        return null;
    }
    public Video bestUnseen(List<Video> videos){
        return null;
    }

    public Video popular(List<Video> videos){
        return null;
    }

    public Video favorite(List<Video> videos){
        return null;
    }

    public List<Video> search(List<Video> videos){
        return null;
    }


}
