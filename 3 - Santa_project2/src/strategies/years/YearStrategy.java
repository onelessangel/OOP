package strategies.years;

import databases.Database;
import entities.Child;
import entities.Gift;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public interface YearStrategy {
    /**
     * Assigns gifts to all the children in the database.
     */
    void assignGifts();

    /**
     * Assigns gifts to a given child.
     */
    static void assignGiftsToChild(Child child) {
        double budget = child.getAssignedBudget();

        for (Category category : child.getGiftsPreferences()) {
            Gift receivedGift = null;
            ArrayList<Gift> giftsList = Database.getDatabase().getGifts().stream()
                    .filter(g -> g.getCategory().equals(category))
                    .sorted(Comparator.comparing(Gift::getPrice))
                    .collect(Collectors.toCollection(ArrayList::new));

            if (giftsList.isEmpty()) {
                continue;
            }

            for (Gift gift : giftsList) {
                if (gift.getQuantity() == 0) {
                    continue;
                }

                receivedGift = gift;
                break;
            }

            if (receivedGift == null || receivedGift.getPrice() > budget) {
                continue;
            }

            child.getReceivedGifts().add(receivedGift);
            budget -= receivedGift.getPrice();
            receivedGift.decreaseQuantity();
        }
    }
}
