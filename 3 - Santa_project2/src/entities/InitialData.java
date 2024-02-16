package entities;

import java.util.ArrayList;

public final class InitialData {
    private final ArrayList<ChildInputData> children;
    private final ArrayList<Gift> gifts;

    public InitialData() {
        children = new ArrayList<>();
        gifts = new ArrayList<>();
    }

    public InitialData(final ArrayList<ChildInputData> children, final ArrayList<Gift> gifts) {
        this.children = children;
        this.gifts = gifts;
    }

    public ArrayList<ChildInputData> getChildren() {
        return children;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    @Override
    public String toString() {
        return "\nInitialData{"
                + "\nchildren=" + children
                + ",\ngifts=" + gifts
                + '}' + '\n';
    }
}
