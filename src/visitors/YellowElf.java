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
     * a
     * @param child object to be visited
     */
    @Override
    public void visit(final Child child) {
        if (child.getElf().equals(Constants.YELLOW) && child.getReceivedGifts().isEmpty()) {

            String category = child.getGiftsPreferences().get(0);
            ArrayList<Gift> possibleGifts = new ArrayList<>();
            for (Gift gift : santaClaus.getGifts()) {
                if (gift.getCategory().equals(category)) {
                    possibleGifts.add(gift);
                }
            }
            if (!possibleGifts.isEmpty()) {
                possibleGifts.sort(Comparator.comparingDouble(Gift::getPrice));
                child.getReceivedGifts().add(possibleGifts.get(0));
                Integer quantity = possibleGifts.get(0).getQuantity();
                possibleGifts.get(0).setQuantity(--quantity);
            }
        }
    }

    /**
     * a
     * @param santa object to be visited
     */
    @Override
    public void visit(final Santa santa) {
        this.santaClaus = santa;
        santa.getChildren().forEach(child -> child.accept(this));
    }
}
