package visitors;

import child.Child;
import santa.Gift;
import santa.Santa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReceivedGifts implements Visitor {
    private Santa santaClaus;

    /**
     * Computes what gifts should the child receive
     */
    @Override
    public void visit(final Child child) {
        LinkedHashMap<String, ArrayList<Gift>> categories = new LinkedHashMap<>();
        ArrayList<Gift> possibleGifts;

        for (String category : child.getGiftsPreferences()) {
            possibleGifts = new ArrayList<>();
            for (Gift gift : santaClaus.getGifts()) {
                if (gift.getCategory().equals(category)) {
                    possibleGifts.add(gift);
                }
            }
            categories.put(category, possibleGifts);
        }

        ArrayList<Gift> receivedGifts = new ArrayList<>();
        double budget = child.getAssignedBudget();

        for (Map.Entry<String, ArrayList<Gift>> entry : categories.entrySet()) {
            possibleGifts = entry.getValue();
            if (!possibleGifts.isEmpty()) {
                possibleGifts.sort(Comparator.comparingDouble(Gift::getPrice));
                // only one gift per category
                // after sorting, its position also indicates that it's the cheapest
                Gift gift = possibleGifts.get(0);
                Integer quantity = gift.getQuantity();
                if (budget > gift.getPrice()) {
                    receivedGifts.add(gift);
                    gift.setQuantity(--quantity);
                    budget -= gift.getPrice();
                }
                if (quantity == 0) {
                    santaClaus.getGifts().remove(gift);
                }
            }
        }
        child.setReceivedGifts(receivedGifts);
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
