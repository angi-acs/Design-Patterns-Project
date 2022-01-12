package visitors;

import child.Child;
import common.Constants;
import santa.Gift;
import santa.Santa;

import java.util.ArrayList;
import java.util.Comparator;

public class YellowElf implements Visitor {
    private Santa santaClaus;

    /**
     * Checks if the yellow elf is assigned to the
     * child and if his/hers received gifts list is empty
     * If so, computes what gift should the child receive
     */
    @Override
    public void visit(final Child child) {
        if (child.getElf().equals(Constants.YELLOW) && child.getReceivedGifts().isEmpty()) {

            // the most preferred category
            String category = child.getGiftsPreferences().get(0);
            ArrayList<Gift> possibleGifts = new ArrayList<>();
            for (Gift gift : santaClaus.getGifts()) {
                if (gift.getCategory().equals(category)) {
                    possibleGifts.add(gift);
                }
            }
            if (!possibleGifts.isEmpty()) {
                possibleGifts.sort(Comparator.comparingDouble(Gift::getPrice));
                // only one gift
                // after sorting, its position also indicates that it's the cheapest
                Gift gift = possibleGifts.get(0);
                Integer quantity = gift.getQuantity();
                // if the cheapest gift is not available,
                // the elf will not assign a gift
                if (quantity == 0) {
                    return;
                }
                child.getReceivedGifts().add(gift);
                possibleGifts.get(0).setQuantity(--quantity);
            }
        }
    }

    /**
     * Calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        this.santaClaus = santa;
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
