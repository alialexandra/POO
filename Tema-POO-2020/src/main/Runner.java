package main;

import action.*;
import actor.Actor;
import common.Constants;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.ActionInputData;
import users.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Runner {

    // get tasks done

    // first comes the commands
    private List<Actor> actors;
    private List<User> users;
    private List<Video> movies;
    private List<Video> shows;
    private List<Query> queries;
    private List<Command> commands;
    private List<Recommend> recommends;
    private Map<Integer, String> outMessage = new LinkedHashMap<>();

    public Runner(List<Actor> actors, List<User> users,
                  List<Video> movies, List<Video> shows,
                  List<Query> queries, List<Command> commands,
                  List<Recommend> recommends) {

        this.actors = actors;
        this.users = users;
        this.movies = movies;
        this.shows = shows;
        this.queries = queries;
        this.commands = commands;
        this.recommends = recommends;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Video> getMovies() {
        return movies;
    }

    public void setMovies(List<Video> movies) {
        this.movies = movies;
    }

    public List<Video> getShows() {
        return shows;
    }

    public void setShows(List<Video> shows) {
        this.shows = shows;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public Map<Integer, String> getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(Map<Integer, String> outMessage) {
        this.outMessage = outMessage;
    }

    private void executeCommand(Command c){

        User currUser = null;

        for (User user:
                this.users) {
            if (user.getUsername().equals(c.getUsername()))
            {
                currUser = user;
                break;
            }
        }

        Video currVideo = null;
        for (Video movie:
                this.movies) {
            if (movie.getName().equals(c.getTitle()))
            {
                currVideo = movie;

                break;
            }
        }

        if (currVideo == null){
            for (Video show:
                    this.shows) {
                if (show.getName().equals(c.getTitle()))
                {
                    currVideo = show;
                    break;
                }
            }
        }

        if(!(c.getType().equals(Constants.RATING))){
            c.execute(currUser, currVideo );
            this.outMessage.put(c.getId(), c.getMessage());
        }
        else if (c.getType().equals(Constants.RATING)){
            if (c.getSeason() == 0){
                assert currVideo != null;
                c.rate((Movie) currVideo, currUser);
                this.outMessage.put(c.getId(), c.getMessage());
            }
            else if(c.getSeason() != 0){
                c.rate((Show) currVideo, currUser);
                this.outMessage.put(c.getId(), c.getMessage());
            }
        }
    }


    private ArrayList<String> convertActorToString(List<Actor> actorsN){
        ArrayList<String> names = new ArrayList<>();

        for (Actor a:
             actorsN) {
            names.add(a.getName());
        }

        return names;
    }

    private ArrayList<String> convertVideoToString(List<Video> videos){
        ArrayList<String> names = new ArrayList<>();

        for (Video v:
                videos) {
            names.add(v.getName());
        }

        return names;
    }

    private ArrayList<String> convertUserToString(List<User> usersN){
        ArrayList<String> names = new ArrayList<>();

        for (User u:
                usersN) {
            names.add(u.getUsername());
        }
        return names;
    }

    private void executeActorQuery(Query q){

        ActorsQuery actorsQuery = new ActorsQuery(
                q.getId(),
                q.getNumber(),
                q.getObjectType(),
                q.getSortCriteria(),
                q.getCriteria(),
                q.getFilter()
        );

        List<Actor> result = new ArrayList<>();

        if (actorsQuery.getCriteria().equals(Constants.AVERAGE)) {
            List<Video> videos = new ArrayList<>();
            videos.addAll(this.movies);
            videos.addAll(this.shows);
            result = actorsQuery.averageList(this.actors, videos);
            //"Query result: [Camila Sodi, Luis Ernesto Franco, Chris Cooper]"

            String message = "Query result: " + convertActorToString(result);
            this.outMessage.put(q.getId(), message);

        }
        else if (actorsQuery.getCriteria().equals(Constants.AWARDS)){
            result = actorsQuery.awardsList(this.actors);
            //"Query result: [Camila Sodi, Luis Ernesto Franco, Chris Cooper]"
            String message = "Query result: " + convertActorToString(result);
            this.outMessage.put(q.getId(), message);
        }
        else if (actorsQuery.getCriteria().equals(Constants.FILTER_DESCRIPTIONS)){
            result = actorsQuery.filterList(this.actors);
            //"Query result: [Camila Sodi, Luis Ernesto Franco, Chris Cooper]"
            String message = "Query result: " + convertActorToString(result);
            this.outMessage.put(q.getId(), message);
        }
    }

    private void executeVideoQuery(Query q){

        VideoQuery videoQuery = new VideoQuery(
                q.getId(),
                q.getNumber(),
                q.getObjectType(),
                q.getSortCriteria(),
                q.getCriteria(),
                q.getFilter()
        );

        List<Video> result = new ArrayList<>();
        //
        if (videoQuery.getCriteria().equals(Constants.RATING)) {

            if (q.getObjectType().equals(Constants.MOVIES)){
                result = videoQuery.ratingsList(this.movies);
            }
            if (q.getObjectType().equals(Constants.SHOWS)){
                result = videoQuery.ratingsList(this.shows);
            }


            String message = "Query result: " + convertVideoToString(result);
            this.outMessage.put(q.getId(), message);

        }
        else if (videoQuery.getCriteria().equals(Constants.FAVORITE)){

            if (q.getObjectType().equals(Constants.MOVIES)){
                result = videoQuery.favoriteList(this.movies);
            }
            if (q.getObjectType().equals(Constants.SHOWS)){
                result = videoQuery.favoriteList(this.shows);
            }
            String message = "Query result: " + convertVideoToString(result);
            this.outMessage.put(q.getId(), message);
        }
        else if (videoQuery.getCriteria().equals(Constants.LONGEST)){
            if (q.getObjectType().equals(Constants.MOVIES)){
                result = videoQuery.longestList(this.movies);
            }
            if (q.getObjectType().equals(Constants.SHOWS)){
                result = videoQuery.longestList(this.shows);
            }
            String message = "Query result: " + convertVideoToString(result);
            this.outMessage.put(q.getId(), message);
        }
        else if (videoQuery.getCriteria().equals(Constants.MOST)){
            if (q.getObjectType().equals(Constants.MOVIES)){
                result = videoQuery.mostViewedList(this.movies);
            }
            if (q.getObjectType().equals(Constants.SHOWS)){
                result = videoQuery.mostViewedList(this.shows);
            }
            String message = "Query result: " + convertVideoToString(result);
            this.outMessage.put(q.getId(), message);
        }



    }

    private void executeUserQuery(Query q) {


            UsersQuery usersQuery = new UsersQuery(
                    q.getId(),
                    q.getNumber(),
                    q.getObjectType(),
                    q.getSortCriteria(),
                    q.getCriteria(),
                    q.getFilter()
            );

            List<User> result = new ArrayList<>();

                result = usersQuery.ratings(this.users);
                //"Query result: [Camila Sodi, Luis Ernesto Franco, Chris Cooper]"

                String message = "Query result: " + convertUserToString(result);
                this.outMessage.put(q.getId(), message);

    }


    public void executeActions(List<ActionInputData> actions) {

        for (ActionInputData a:
             actions) {
            // daca avem o comanda
            if (a.getActionType().equals(Constants.COMMAND)){
                Command c = commands.get(0);
                executeCommand(c);
                // dupa ce a executat comanda bye-bye
                commands.remove(c);
            }
            // daca avem un query:
            // mor aici frate, nu mai pot
            if (a.getActionType().equals(Constants.QUERY)){
                Query q = queries.get(0);
                if (q.getObjectType().equals(Constants.ACTORS)){
                    executeActorQuery(q);
                }
                else if (q.getObjectType().equals(Constants.MOVIES) ||
                        q.getObjectType().equals(Constants.SHOWS)){
                    executeVideoQuery(q);
                }
                else if (q.getObjectType().equals(Constants.USERS)){
                    executeUserQuery(q);
                }
                // probabil un caz de query invalid
                queries.remove(q);
            }
        }
    }



}
