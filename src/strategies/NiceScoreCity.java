package strategies;

import child.Child;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class NiceScoreCity implements Strategy {

    /**
     * Sorts children by their city average score
     */
    @Override
    public void sort(final ArrayList<Child> children) {
        // key - city, value - list of children who live in that city
        LinkedHashMap<String, ArrayList<Child>> cities = new LinkedHashMap<>();
        for (Child child : children) {
            cities.put(child.getCity(), new ArrayList<>());
        }
        for (Child child : children) {
            ArrayList<Child> childrenByCity = cities.get(child.getCity());
            childrenByCity.add(child);
            cities.put(child.getCity(), childrenByCity);
        }

        // key - city, value - arithmetic mean of children's average score
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

        // sort cities in descending order by average score
        // or lexicographically in case of equality
        ArrayList<Map.Entry<String, Double>> sortedList = new ArrayList<>(cityScore.entrySet());
        sortedList.sort((o1, o2) -> {
            if (Objects.equals(o1.getValue(), o2.getValue())) {
                return o1.getKey().compareTo(o2.getKey());
            } else {
                return Double.compare(o2.getValue(), o1.getValue());
            }
        });

        // list containing the order in which the cities should be visited
        ArrayList<String> sortedCities = sortedList.stream().
                map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new));
        // sort children by their city's appearance in the previous list
        children.sort(Comparator.comparing(child -> sortedCities.indexOf(child.getCity())));
        // the children from a city are visited in the order of Ids
        children.sort((o1, o2) -> {
            if (o1.getCity().equals(o2.getCity())) {
                return Integer.compare(o1.getId(), o2.getId());
            }
            return 0;
        });
    }
}
