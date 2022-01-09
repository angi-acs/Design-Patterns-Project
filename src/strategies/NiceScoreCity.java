package strategies;

import child.Child;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class NiceScoreCity implements Strategy {

    /**
     * a
     * @param children b
     */
    @Override
    public void sort(final ArrayList<Child> children) {
        LinkedHashMap<String, ArrayList<Child>> cities = new LinkedHashMap<>();
        for (Child child : children) {
            cities.put(child.getCity(), new ArrayList<>());
        }
        for (Child child : children) {
            ArrayList<Child> childrenByCity = cities.get(child.getCity());
            childrenByCity.add(child);
            cities.put(child.getCity(), childrenByCity);
        }

        LinkedHashMap<String, Double> cityScore = new LinkedHashMap<>();
        for (Map.Entry<String, ArrayList<Child>> entry : cities.entrySet()) {
            ArrayList<Child> childrenByCity = entry.getValue();
            double score = 0;
            for (Child child : childrenByCity) {
                score += child.getAverageScore();
            }
            score /= childrenByCity.size();
            cityScore.put(entry.getKey(), score);
        }

        ArrayList<Map.Entry<String, Double>> sortedList = new ArrayList<>(cityScore.entrySet());
        sortedList.sort((o1, o2) -> {
            if (Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return Double.compare(o2.getValue(), o1.getValue());
            }
        });

        ArrayList<String> sortedCities = new ArrayList<>();
        for (Map.Entry<String, Double> entry : sortedList) {
            sortedCities.add(entry.getKey());
        }

        children.sort(Comparator.comparing(child -> sortedCities.indexOf(child.getCity())));
        children.sort((o1, o2) -> {
            if (o1.getCity().equals(o2.getCity())) {
                return Integer.compare(o1.getId(), o2.getId());
            }
            return 0;
        });
    }
}
