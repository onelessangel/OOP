package strategies.elves;

import entities.Child;

public final class PinkElfStrategy implements ElfStrategy {
    @Override
    public void makeChanges(final Child child) {
        child.setAssignedBudget(child.getAssignedBudget() + child.getAssignedBudget() * 30 / 100);
    }
}
