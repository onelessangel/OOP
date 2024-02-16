package entities;

import java.util.ArrayList;

public final class AnnualChange {
    private final double newSantaBudget;
    private final ArrayList<Gift> newGifts;
    private final ArrayList<ChildInputData> newChildren;
    private final ArrayList<ChildUpdate> childrenUpdates;

    public AnnualChange(final double newSantaBudget, final ArrayList<Gift> newGifts,
                            final ArrayList<ChildInputData> newChildren,
                            final ArrayList<ChildUpdate> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    public ArrayList<ChildInputData> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    @Override
    public String toString() {
        return "\nAnnualChange{"
                + "\nnewSantaBudget=" + newSantaBudget
                + ",\nnewGifts=" + newGifts
                + ",\nnewChildren=" + newChildren
                + ",\nchildrenUpdates=" + childrenUpdates
                + '}' + '\n';
    }
}
