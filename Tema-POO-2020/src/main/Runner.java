package main;

import action.Command;
import action.Query;
import action.Recommend;
import actor.Actor;
import common.Constants;
import entertainment.Movie;
import entertainment.Video;
import fileio.ActionInputData;
import users.User;

import java.util.ArrayList;
import java.util.List;

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

    private void executeCommand(Command c){
        User currUser;
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


        if(c.getType().equals(Constants.VIEW)){

        }
    }
    public void executeActions(List<ActionInputData> actions) {

        for (ActionInputData a:
             actions) {
            if (a.getActionType().equals(Constants.COMMAND)){
                // vreau sa sterg comanda dupa ce s-a executat
                // le iau pe rand
                // sper printr-o minune sa mearga...
                // frate mi-au amortit mainile
                Command c = commands.get(0);
                executeCommand(c);
                //NU MAI POT. VREAU SA TERMIN CA SIMT CA MOR
                ///OFFFFFFFFFF
                // fiind user

            }
        }
    }



}
