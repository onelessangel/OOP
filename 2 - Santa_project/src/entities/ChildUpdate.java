package entities;

import java.util.ArrayList;

public final class ChildUpdate {
    private final int id;
    private final Double niceScore;
    private final ArrayList<String> giftsPreferences;

    public ChildUpdate(final int id, final Double niceScore,
                           final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    @Override
    public String toString() {
        return "ChildUpdate{"
                + "id=" + id
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + '}' + '\n';
    }
}
