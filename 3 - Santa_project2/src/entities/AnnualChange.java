package entities;

import enums.Strategy;

import java.util.ArrayList;
import java.util.Observable;

public final class AnnualChange extends Observable {
    private final Double newSantaBudget;
    private final ArrayList<Gift> newGifts;
    private final ArrayList<ChildInputData> newChildren;
    private final ArrayList<ChildUpdate> childrenUpdates;
    private final Strategy strategy;

    public static final class Builder {
        private final Double newSantaBudget;                                        // mandatory
        private ArrayList<Gift> newGifts = new ArrayList<>();                       // optional
        private ArrayList<ChildInputData> newChildren = new ArrayList<>();          // optional
        private ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();         // optional
        private final Strategy strategy;                                            // mandatory

        public Builder(final Double newSantaBudget, final Strategy strategy) {
            this.newSantaBudget = newSantaBudget;
            this.strategy = strategy;
        }

        public Builder(final AnnualChange annualChange) {
            this(annualChange.newSantaBudget, annualChange.strategy);
            this.newGifts = annualChange.newGifts;
            this.newChildren = annualChange.newChildren;
            this.childrenUpdates = annualChange.childrenUpdates;
        }

        /**
         * Adds optional newGifts list.
         * @param newGiftsList to be added
         * @return current Builder instance
         */
        public Builder newGifts(final ArrayList<Gift> newGiftsList) {
            newGifts = newGiftsList;
            return this;
        }

        /**
         * Adds optional newChildren list.
         * @param newChildrenList to be added
         * @return current Builder instance
         */
        public Builder newChildren(final ArrayList<ChildInputData> newChildrenList) {
            newChildren = newChildrenList;
            return this;
        }

        /**
         * Adds optional childrenUpdates list.
         * @param childrenUpdatesList to be added
         * @return current Builder instance
         */
        public Builder childrenUpdates(final ArrayList<ChildUpdate> childrenUpdatesList) {
            childrenUpdates = childrenUpdatesList;
            return this;
        }

        /**
         * Creates the current AnnualChange instance.
         */
        public AnnualChange build() {
            return new AnnualChange(this);
        }
    }

    public AnnualChange(final Builder builder) {
        this.newSantaBudget = builder.newSantaBudget;
        this.newGifts = builder.newGifts;
        this.newChildren = builder.newChildren;
        this.childrenUpdates = builder.childrenUpdates;
        this.strategy = builder.strategy;
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

    public Strategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "\nAnnualChange{"
                + "\nnewSantaBudget=" + newSantaBudget
                + ",\nnewGifts=" + newGifts
                + ",\nnewChildren=" + newChildren
                + ",\nchildrenUpdates=" + childrenUpdates
                + ",\nstrategy=" + strategy
                + '}' + '\n';
    }
}
