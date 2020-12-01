package main;

import action.Command;
import action.Query;
import action.Recommend;
import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import entertainment.Video;
import fileio.ActionInputData;
import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import fileio.SerialInputData;

import users.User;
import common.Constants;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Class for parsing the database given at the input
 */
public final class Reader {

    private List<Actor> actors = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Video> movies = new ArrayList<>();
    private List<Video> shows = new ArrayList<>();
    private List<Query> queries = new ArrayList<>();
    private List<Command> commands = new ArrayList<>();
    private List<Recommend> recommends = new ArrayList<>();

    /**
     *
     * @return
     */
    public List<Actor> getActors() {
        return actors;
    }

    /**
     *
     * @param actors
     */
    public void setActors(final List<Actor> actors) {
        this.actors = actors;
    }

    /**
     *
     * @return
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     *
     * @param users
     */
    public void setUsers(final List<User> users) {
        this.users = users;
    }

    /**
     *
     * @return
     */
    public List<Query> getQueries() {
        return queries;
    }

    /**
     *
     * @param queries
     */
    public void setQueries(final List<Query> queries) {
        this.queries = queries;
    }

    /**
     *
     * @return
     */
    public List<Command> getCommands() {
        return commands;
    }

    /**
     *
     * @param commands
     */
    public void setCommands(final List<Command> commands) {
        this.commands = commands;
    }

    /**
     *
     * @return
     */
    public List<Recommend> getRecommends() {
        return recommends;
    }

    /**
     *
     * @param recommends
     */
    public void setRecommends(final List<Recommend> recommends) {
        this.recommends = recommends;
    }

    /**
     *
     * @return
     */
    public List<Video> getShows() {
        return shows;
    }

    /**
     *
     * @param shows
     */
    public void setShows(final List<Video> shows) {
        this.shows = shows;
    }

    /**
     *
     * @return
     */
    public List<Video> getMovies() {
        return movies;
    }

    /**
     *
     * @param movies
     */
    public void setMovies(final List<Video> movies) {
        this.movies = movies;
    }

    /**
     * parse the list of actors
      * @param actorsInput
     */
    public void parseActors(final List<ActorInputData> actorsInput) {

        for (ActorInputData actor
                : actorsInput) {
            this.actors.add(new Actor(actor.getName(),
                    actor.getCareerDescription(),
                    actor.getFilmography(),
                    actor.getAwards()));
        }

    }

    /**
     *
     * @param usersInput
     */
    public void parseUsers(final List<UserInputData> usersInput) {

        for (UserInputData user
                : usersInput) {
            this.users.add(new User(user.getUsername(),
                    user.getSubscriptionType(),
                    (LinkedHashMap<String, Integer>) user.getHistory(),
                    user.getFavoriteMovies()));
        }

    }

    /**
     *
     * @param moviesInput
     */
    public void parseMovie(final List<MovieInputData> moviesInput) {

        for (MovieInputData movie
                : moviesInput) {
            this.movies.add(new Movie(movie.getTitle(),
                    movie.getYear(),
                    movie.getGenres(),
                    movie.getCast(),
                    movie.getDuration()));
        }
    }

    /**
     *
     * @param showInput
     */
    public void parseShows(final List<SerialInputData> showInput) {
        for (SerialInputData show
                : showInput) {
            this.shows.add(new Show(show.getTitle(),
                    show.getYear(),
                    show.getGenres(),
                    show.getCast(),
                    show.getNumberSeason(),
                    show.getSeasons()));
        }

    }

    /**
     *
     * @param actions
     */
    public void parseAction(final List<ActionInputData> actions) {

        for (ActionInputData a
                : actions) {

            String actionType = a.getActionType();

            switch (actionType) {
                case Constants.COMMAND:
                    if (a.getGrade() == 0) {
                        this.commands.add(new Command(a.getActionId(),
                                a.getType(), 0, 0,
                                a.getUsername(), a.getTitle()));
                    }
                    if (a.getSeasonNumber() == 0
                            && a.getGrade() != 0) {
                        this.commands.add(new Command(a.getActionId(),
                                a.getType(), a.getGrade(), 0,
                                a.getUsername(), a.getTitle()));
                    }
                    if (a.getGrade() != 0
                            && a.getSeasonNumber() != 0) {
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
                    break;
                case Constants.RECOMMENDATION:
                    this.recommends.add(new Recommend(
                            a.getActionId(),
                            a.getActionType(),
                            a.getUsername()));
                    break;
                default:
                    break;

            }
        }
    }








}
