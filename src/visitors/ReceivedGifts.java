package visitors;

import child.Child;
import santa.Gift;
import santa.Santa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReceivedGifts implements ChildVisitor {
    private final Santa santa;

    public ReceivedGifts(final Santa santa) {
        this.santa = santa;
    }

    /**
     * a
     * @param child b
     */
    @Override
    public void visit(final Child child) {
        LinkedHashMap<String, ArrayList<Gift>> categories = new LinkedHashMap<>();
        ArrayList<Gift> possibleGifts;

        for (String category : child.getGiftsPreference()) {
            possibleGifts = new ArrayList<>();
            for (Gift gift : santa.getGifts()) {
                if (gift.getCategory().equals(category)) {
                    possibleGifts.add(gift);
                }
            }
            categories.put(category, possibleGifts);
        }

        ArrayList<Gift> receivedGifts = new ArrayList<>();

        for (Map.Entry<String, ArrayList<Gift>> entry : categories.entrySet()) {
            possibleGifts = entry.getValue();
            if (!possibleGifts.isEmpty()) {
                possibleGifts.sort(Comparator.comparingDouble(Gift::getPrice));
                Gift gift = possibleGifts.get(0);
                if (child.getAssignedBudget() > gift.getPrice()) {
                    receivedGifts.add(gift);
                    double budget = child.getAssignedBudget();
                    child.setAssignedBudget(budget - gift.getPrice());
                }
            }
        }
        child.setReceivedGifts(receivedGifts);
    }
}
