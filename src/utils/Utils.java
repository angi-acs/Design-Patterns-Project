package utils;

import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public final class Utils {

    private Utils() {
    }

    /**
     * Transforms an array of JSONs into an array of strings
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            LinkedHashSet<String> noDuplicates = new LinkedHashSet<>();
            for (Object object : array) {
                noDuplicates.add((String) object);
            }
            return new ArrayList<>(noDuplicates);
        } else {
            return null;
        }
    }
}
