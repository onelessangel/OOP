package database;

import action.Action;
import actor.Actor;
import entertainment.Movie;
import entertainment.Show;
import fileio.Input;
import user.User;

import java.util.ArrayList;

/**
 * Contains the database and static methods for creating and resetting it.
 */

public final class Database {
    private ArrayList<Actor> actors;
    private ArrayList<Movie> movies;
    private ArrayList<Show> shows;
    private ArrayList<User> users;
    private ArrayList<Action> actions;

    // Singleton - Lazy Implementation
    private static Database instance = null;

    private Database() {

    }

    /**
     * Used to access the database. Creates it when called for the first time.
     * @return the database instance
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * Empties the database
     */
    public static void resetDatabase() {
        Database.getDatabase().actors = new ArrayList<>();
        Database.getDatabase().movies = new ArrayList<>();
        Database.getDatabase().shows = new ArrayList<>();
        Database.getDatabase().users = new ArrayList<>();
        Database.getDatabase().actions = new ArrayList<>();
    }

    /**
     * Makes a copy of the given input and stores it in the database
     * @param input containing the data and the actions performed by the users
     */
    public static void makeDatabase(final Input input) {
        input.getActors().forEach(actor -> Database.getDatabase().actors.add(new Actor(actor)));
        input.getMovies().forEach(movie -> Database.getDatabase().movies.add(new Movie(movie)));
        input.getSerials().forEach(serial -> Database.getDatabase().shows.add(new Show(serial)));
        input.getUsers().forEach(user -> Database.getDatabase().users.add(new User(user)));
        input.getCommands().forEach(action -> Database.getDatabase().actions
                                                                         .add(new Action(action)));
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }
}
