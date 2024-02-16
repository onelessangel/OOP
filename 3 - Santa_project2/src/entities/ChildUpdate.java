package entities;

import enums.Category;
import enums.ElvesType;

import java.util.ArrayList;

public final class ChildUpdate {
    private final int id;
    private final Double niceScore;
    private final ArrayList<Category> giftsPreferences;
    private ElvesType elf;

    public ChildUpdate(final int id, final Double niceScore,
                           final ArrayList<Category> giftsPreferences, final ElvesType elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
    }

    public int getId() {
        return id;
    }

    public Double getNiceScore() {
        return niceScore;
    }

    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    public ElvesType getElf() {
        return elf;
    }

    @Override
    public String toString() {
        return "ChildUpdate{"
                + "id=" + id
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + ", elf=" + elf
                + '}' + '\n';
    }
}
