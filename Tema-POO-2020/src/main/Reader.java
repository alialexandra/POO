package main;

import action.Command;
import action.Query;
import action.Recommend;
import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.*;
import users.User;
import common.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Reader {
    // clasa in care pun ce am citit

    private List<Actor> actors = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Video> movies = new ArrayList<>();
    private List<Video> shows = new ArrayList<>();
    private List<Query> queries = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private List<Recommend> recommends = new ArrayList<>();

    public Reader(){}

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

    public List<Video> getVideos() {
        return movies;
    }

    public void setVideos(List<Video> movies) {
        this.movies = movies;
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

    public List<Video> getShows() {
        return shows;
    }

    public void setShows(List<Video> shows) {
        this.shows = shows;
    }

    public List<Video> getMovies() {
        return movies;
    }

    public void setMovies(List<Video> movies) {
        this.movies = movies;
    }

    // metode de instantiere a claselor
    
    public void parseActors(List<ActorInputData> actorsInput){



        for (ActorInputData actor :
             actorsInput) {
            this.actors.add(new Actor(actor.getName(),
                    actor.getCareerDescription(),
                    actor.getFilmography(),
                    actor.getAwards()));
        }

    }

    public void parseUsers(List<UserInputData> usersInput){



        for (UserInputData user :
                usersInput) {
            this.users.add(new User(user.getUsername(),
                    user.getSubscriptionType(),
                    (LinkedHashMap<String, Integer>) user.getHistory(),
                    user.getFavoriteMovies()
                    ));
        }


    }

    public void parseMovie(List<MovieInputData> moviesInput){



        for (MovieInputData movie :
                moviesInput) {
            this.movies.add(new Movie(movie.getTitle(),
                    movie.getYear(),
                    movie.getGenres(),
                    movie.getCast(),
                    movie.getDuration()
            ));
        }

    }

    public void parseShows(List<SerialInputData> showInput){



        for (SerialInputData show :
                showInput) {
            this.shows.add(new Show(show.getTitle(),
                    show.getYear(),
                    show.getCast(),
                    show.getGenres(),
                    show.getNumberSeason(),
                    show.getSeasons()
            ));
        }

    }

    // parse commands

    public void parseAction(List<ActionInputData> actions){


        for (ActionInputData a:
             actions) {
            String actionType = a.getActionType();

            switch (actionType){
                case Constants.COMMAND:
                    if(a.getGrade() == 0) {
                        this.commands.add(new Command(a.getActionId(),
                                a.getType(),0,0,
                                a.getUsername(), a.getTitle()));
                    }
                    if (a.getSeasonNumber() == 0 &&
                        a.getGrade() != 0){
                        this.commands.add(new Command(a.getActionId(),
                                a.getType(), a.getGrade(), 0,
                                a.getUsername(), a.getTitle()));
                    }
                    if (a.getGrade() != 0 &&
                    a.getSeasonNumber() != 0){
                        this.commands.add(new Command(a.getActionId(),
                                a.getType(), a.getGrade(),
                                a.getSeasonNumber(),
                                a.getUsername(),
                                a.getTitle()));
                    }
                    break;
                case Constants.QUERY:
                    this.queries.add(new Query(a.getActionId(),
                            a.getNumber(),
                            a.getObjectType(),
                            a.getSortType(),
                            a.getCriteria(),
                            a.getFilters()));
                case Constants.RECOMMENDATION:
                    this.recommends.add(new Recommend(
                            a.getActionId(),
                            a.getActionType(),
                            a.getUsername()));
                default:
                    break;

            }

        }
    }








}
