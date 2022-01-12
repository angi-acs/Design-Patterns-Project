package visitors;

import child.Child;
import common.Constants;
import santa.Gift;
import santa.Santa;
import strategies.Strategy;
import strategies.StrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReceivedGifts implements Visitor {
    private Santa santaClaus;
    private final String annualStrategy;

    public ReceivedGifts(final String annualStrategy) {
        this.annualStrategy = annualStrategy;
    }

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
            // cannot assign gift if the quantity is 0
            possibleGifts.removeIf(gift -> gift.getQuantity() == 0);
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
            }
        }
        child.setReceivedGifts(receivedGifts);
    }

    /**
     * Creates a strategy factory in order to instantiate a
     * concrete strategy based on a string received in an input test
     * Calls the visitor for every child in Santa's list
     */
    @Override
    public void visit(final Santa santa) {
        this.santaClaus = santa;

        StrategyFactory strategyFactory = new StrategyFactory();
        Strategy strategy = strategyFactory.createStrategy(annualStrategy);
        strategy.sort(santa.getChildren());

        santa.getChildren().forEach(child -> child.accept(this));

        // reorder children by Id towards matching the ref output
        Strategy idStrategy = strategyFactory.createStrategy(Constants.ID);
        idStrategy.sort(santa.getChildren());
    }
}
