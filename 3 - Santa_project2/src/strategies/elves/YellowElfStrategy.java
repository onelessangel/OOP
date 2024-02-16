package strategies.elves;

import databases.Database;
import entities.Child;
import entities.Gift;
import enums.Category;

import java.util.Comparator;

public final class YellowElfStrategy implements ElfStrategy {
    @Override
    public void makeChanges(final Child child) {
        Category favoriteCategory = child.getGiftsPreferences().get(0);
        Gift extraGift = null;
        Database.getDatabase().getGifts().sort(Comparator.comparing(Gift::getPrice));

        for (Gift gift : Database.getDatabase().getGifts()) {
            if (gift.getCategory().equals(favoriteCategory)) {
                extraGift = gift;
                break;
            }
        }

        if (extraGift == null || extraGift.getQuantity() == 0) {
            return;
        }

        child.getReceivedGifts().add(extraGift);
        extraGift.decreaseQuantity();
    }
}
