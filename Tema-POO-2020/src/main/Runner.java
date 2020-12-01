package main;

import action.Query;
import action.ActorsQuery;
import action.VideoQuery;
import action.UsersQuery;
import action.Recommend;
import action.Command;
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

public final class Runner {

    /**
     * get tasks done
     * first comes the commands
     */
    private List<Actor> actors;
    private List<User> users;
    private List<Video> movies;
    private List<Video> shows;
    private List<Query> queries;
    private List<Command> commands;
    private List<Recommend> recommends;
    private Map<Integer, String> outMessage = new LinkedHashMap<>();

    public Runner(final List<Actor> actors, final List<User> users,
                  final List<Video> movies, final List<Video> shows,
                  final List<Query> queries, final List<Command> commands,
                  final List<Recommend> recommends) {

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

    public void setActors(final List<Actor> actors) {
        this.actors = actors;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    public List<Video> getMovies() {
        return movies;
    }

    public void setMovies(final List<Video> movies) {
        this.movies = movies;
    }

    public List<Video> getShows() {
        return shows;
    }

    public void setShows(final List<Video> shows) {
        this.shows = shows;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(final List<Query> queries) {
        this.queries = queries;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(final List<Command> commands) {
        this.commands = commands;
    }

    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(final List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public Map<Integer, String> getOutMessage() {
        return outMessage;
    }

    /**
     * choose the command to be executed
     * rating, view or favorite
     * @param c
     */
    private void executeCommand(final Command c) {

        User currUser = null;

        for (User user
                : this.users) {
            if (user.getUsername().equals(c.getUsername())) {
                currUser = user;
                break;
            }
        }

        Video currVideo = null;
        // search for the needed video
        for (Video movie
                : this.movies) {
            if (movie.getName().equals(c.getTitle())) {
                currVideo = movie;
                break;
            }
        }

        if (currVideo == null) {
            for (Video show
                    : this.shows) {
                if (show.getName().equals(c.getTitle())) {
                    currVideo = show;
                    break;
                }
            }
        }

        if (!(c.getType().equals(Constants.RATING))) {
            c.execute(currUser, currVideo);
            this.outMessage.put(c.getId(), c.getMessage());
            //(c.getType().equals(Constants.RATING))
        } else {
            if (c.getSeason() == 0) {
                assert currVideo != null;
                assert currUser != null;
                c.rate((Movie) currVideo, currUser);
            } else {
                c.rate((Show) currVideo, currUser);
            }
            this.outMessage.put(c.getId(), c.getMessage());
        }
    }

    /**
     *
     * @param actorsN
     * @return the name of the actors needed in query
     */
    private ArrayList<String> convertActorToString(final List<Actor> actorsN) {
        ArrayList<String> names = new ArrayList<>();

        for (Actor a
                : actorsN) {
            names.add(a.getName());
        }

        return names;
    }

    /**
     * @param videos
     * @return only the names of the videos needed in query
     */
    private ArrayList<String> convertVideoToString(final List<Video> videos) {
        ArrayList<String> names = new ArrayList<>();

        for (Video v
                : videos) {
            names.add(v.getName());
        }

        return names;
    }

    /**
     *
     * @param usersN
     * @return the name of the users needed in query
     */
    private ArrayList<String> convertUserToString(final List<User> usersN) {
        ArrayList<String> names = new ArrayList<>();

        for (User u
                : usersN) {
            names.add(u.getUsername());
        }
        return names;
    }

    /**
     * get the results of the needed query for actors
     * @param q
     */
    private void executeActorQuery(final Query q) {

        ActorsQuery actorsQuery = new ActorsQuery(
                q.getId(),
                q.getNumber(),
                q.getObjectType(),
                q.getSortCriteria(),
                q.getCriteria(),
                q.getFilter()
        );

        List<Actor> result;

        switch (actorsQuery.getCriteria()) {
            case Constants.AVERAGE -> {
                List<Video> videos = new ArrayList<>();
                videos.addAll(this.movies);
                videos.addAll(this.shows);
                result = actorsQuery.averageList(this.actors, videos);
                String message = Constants.QUERY_RESULT + convertActorToString(result);
                this.outMessage.put(q.getId(), message);
            }
            case Constants.AWARDS -> {
                result = actorsQuery.awardsList(this.actors);
                String message = Constants.QUERY_RESULT + convertActorToString(result);
                this.outMessage.put(q.getId(), message);
            }
            case Constants.FILTER_DESCRIPTIONS -> {
                result = actorsQuery.filterList(this.actors);
                String message = Constants.QUERY_RESULT + convertActorToString(result);
                this.outMessage.put(q.getId(), message);
            }
            default -> throw new IllegalStateException("Unexpected value: "
                    + actorsQuery.getCriteria());
        }
    }

    /**
     * get the results of the needed query for videos
     * @param q
     */
    private void executeVideoQuery(final Query q) {

        VideoQuery videoQuery = new VideoQuery(
                q.getId(),
                q.getNumber(),
                q.getObjectType(),
                q.getSortCriteria(),
                q.getCriteria(),
                q.getFilter()
        );

        List<Video> result = new ArrayList<>();

        switch (videoQuery.getCriteria()) {
            case Constants.RATINGS -> {

                if (q.getObjectType().equals(Constants.MOVIES)) {
                    result = videoQuery.ratingsList(this.movies);
                }
                if (q.getObjectType().equals(Constants.SHOWS)) {
                    result = videoQuery.ratingsList(this.shows);
                }
                String message = Constants.QUERY_RESULT + convertVideoToString(result);
                this.outMessage.put(q.getId(), message);

            }
            case Constants.FAVORITE -> {

                if (q.getObjectType().equals(Constants.MOVIES)) {
                    result = videoQuery.favoriteList(this.movies, this.users);
                }
                if (q.getObjectType().equals(Constants.SHOWS)) {
                    result = videoQuery.favoriteList(this.shows, this.users);
                }
                String message = Constants.QUERY_RESULT + convertVideoToString(result);
                this.outMessage.put(q.getId(), message);
            }
            case Constants.LONGEST -> {
                if (q.getObjectType().equals(Constants.MOVIES)) {
                    result = videoQuery.longestList(this.movies);
                }
                if (q.getObjectType().equals(Constants.SHOWS)) {
                    result = videoQuery.longestList(this.shows);
                }
                String message = Constants.QUERY_RESULT + convertVideoToString(result);
                this.outMessage.put(q.getId(), message);

            }
            case Constants.MOST -> {
                if (q.getObjectType().equals(Constants.MOVIES)) {
                    result = videoQuery.mostViewedList(this.movies, this.users);
                }
                if (q.getObjectType().equals(Constants.SHOWS)) {
                    result = videoQuery.mostViewedList(this.shows, this.users);
                }
                String message = Constants.QUERY_RESULT + convertVideoToString(result);
                this.outMessage.put(q.getId(), message);
            }
            default -> throw new IllegalStateException("Unexpected value: "
                    + videoQuery.getCriteria());
        }
    }

    /**
     * get the lists of users needed in query
     * @param q
     */
    private void executeUserQuery(final Query q) {
            UsersQuery usersQuery = new UsersQuery(
                    q.getId(),
                    q.getNumber(),
                    q.getObjectType(),
                    q.getSortCriteria(),
                    q.getCriteria(),
                    q.getFilter()
            );

            List<User> result;
            result = usersQuery.ratings(this.users);
            String message = Constants.QUERY_RESULT + convertUserToString(result);
            this.outMessage.put(q.getId(), message);

    }

    /**
     * parse the action to be executed
     * @param actions
     */
    public void executeActions(final List<ActionInputData> actions) {

        for (ActionInputData a
                : actions) {
            /**
             * command case
             */
            if (a.getActionType().equals(Constants.COMMAND)) {
                Command c = this.commands.get(0);
                executeCommand(c);
                this.commands.remove(c);
            }
            /**
             * query case
             */
            if (a.getActionType().equals(Constants.QUERY)) {
                Query q = queries.get(0);
                switch (q.getObjectType()) {
                    case Constants.ACTORS -> executeActorQuery(q);
                    case Constants.MOVIES, Constants.SHOWS -> executeVideoQuery(q);
                    case Constants.USERS -> executeUserQuery(q);
                    default -> throw new IllegalStateException("Unexpected value: "
                            + q.getObjectType());
                }
                queries.remove(q);
            }
        }
    }
}
