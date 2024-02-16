package action;

import fileio.ActionInputData;

import java.util.List;

/**
 * Information about an action.
 */

public final class Action {
    private final int actionId;
    private final String actionType;
    private final String type;
    private final String username;
    private final String objectType;
    private final String sortType;
    private final String criteria;
    private final String title;
    private final String genre;
    private final int number;
    private final double grade;
    private final int seasonNumber;
    private final List<List<String>> filters;

    public Action(final ActionInputData action) {
        actionId = action.getActionId();
        actionType = action.getActionType();
        type = action.getType();
        username = action.getUsername();
        objectType = action.getObjectType();
        sortType = action.getSortType();
        criteria = action.getCriteria();
        title = action.getTitle();
        genre = action.getGenre();
        number = action.getNumber();
        grade = action.getGrade();
        seasonNumber = action.getSeasonNumber();
        filters = action.getFilters();
    }

    public int getActionId() {
        return actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public String getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getSortType() {
        return sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getNumber() {
        return number;
    }

    public double getGrade() {
        return grade;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public List<List<String>> getFilters() {
        return filters;
    }
}
