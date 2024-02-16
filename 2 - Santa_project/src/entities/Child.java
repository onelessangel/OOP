package entities;

import common.Constants;
import enums.ChildType;

import java.util.ArrayList;

public final class Child extends ChildInputData {
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;
    private ChildType childType;

    public Child() {

    }

    public Child(final ChildInputData child) {
        super(child.id, child.lastName, child.firstName, child.age, child.city, child.niceScore,
                child.giftsPreferences);
        averageScore = 0d;
        assignedBudget = 0d;
        receivedGifts = new ArrayList<>();

        niceScoreHistory = new ArrayList<>();
        if (niceScore != null) {
            niceScoreHistory.add(niceScore);
        }

        setChildType();
    }

    /**
     * Sets the child type.
     */
    public void setChildType() {
        if (age < Constants.BABY_AGE_MAX) {
            childType = ChildType.BABY;
        } else if (age < Constants.KID_AGE_MAX) {
            childType = ChildType.KID;
        } else if (age < Constants.TEEN_AGE_MAX) {
            childType = ChildType.TEEN;
        } else {
            childType = ChildType.YOUNG_ADULT;
        }
    }

    /**
     * Increases age by 1 year.
     */
    public void increaseAge() {
        age++;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    public Double getAssignedBudget() {
        return assignedBudget;
    }

    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    public ChildType getChildType() {
        return childType;
    }

    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    @Override
    public String toString() {
        return "\nChild{"
                + ",\nid=" + id
                + ",\nlast+ Name='" + lastName + '\''
                + ",\nfirstName='" + firstName + '\''
                + ",\nage=" + age
                + ",\ncity='" + city + '\''
                + ",\ngiftsPreferences=" + giftsPreferences
                + "\naverageScore=" + averageScore
                + ",\nniceScoreHistory=" + niceScoreHistory
                + ",\nassignedBudget=" + assignedBudget
                + ",\nreceivedGifts=" + receivedGifts
                + ",\nchildType=" + childType
                + '}' + '\n';
    }
}
