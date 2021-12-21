package fileio;

import child.Child;
import common.Constants;
import enums.Category;
import lombok.Getter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import santa.Gift;
import santa.Santa;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Getter
public class InputReader {
    private final String inputPath;
    private int numberOfYears;

    public InputReader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * a
     * @return b
     */
    public Santa initialData() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser
                .parse(new FileReader(inputPath));
        numberOfYears =  Integer.parseInt(jsonObject.get(Constants.YEARS)
                .toString());
        double santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTA_BUDGET)
                .toString());
        JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
        JSONArray jsonChildren = (JSONArray)
                initialData.get(Constants.CHILDREN);
        JSONArray jsonGifts = (JSONArray)
                initialData.get(Constants.SANTA_GIFTS);
        ArrayList<Child> children = readChildren(jsonChildren);
        ArrayList<Gift> gifts = readGifts(jsonGifts);

        return new Santa(santaBudget, children, gifts);
    }

    /**
     * a
     * @throws IOException b
     * @throws ParseException c
     */
    @SuppressWarnings("unchecked")
    public ArrayList<AnnualChange> annualChanges() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser
                .parse(new FileReader(inputPath));
        JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
        ArrayList<AnnualChange> annualChanges = new ArrayList<>();
        if (jsonAnnualChanges != null) {
            for (Object change : jsonAnnualChanges) {

                double newSantaBudget = Double.parseDouble(((JSONObject) change)
                        .get(Constants.NEW_SANTA_BUDGET).toString());
                JSONArray jsonNewGifts = (JSONArray) ((JSONObject) change).get(Constants.NEW_GIFTS);
                JSONArray jsonNewChildren = (JSONArray) ((JSONObject) change)
                        .get(Constants.NEW_CHILDREN);
                ArrayList<Gift> newGifts = readGifts(jsonNewGifts);
                ArrayList<Child> newChildren = readChildren(jsonNewChildren);

                JSONArray jsonChildrenUpdates = (JSONArray) ((JSONObject) change)
                        .get(Constants.UPDATES);
                ArrayList<ChildUpdate> childrenUpdates = new ArrayList<>();
                if (jsonChildrenUpdates != null) {
                    for (Object update : jsonChildrenUpdates) {
                        if (((JSONObject) update).get(Constants.NICE_SCORE) != null) {
                            childrenUpdates.add(new ChildUpdate(
                                    Integer.parseInt(((JSONObject) update).get(Constants.ID)
                                            .toString()),
                                    Double.parseDouble(((JSONObject) update)
                                            .get(Constants.NICE_SCORE).toString()),
                                    (ArrayList<Category>) ((JSONObject)
                                            update).get(Constants.GIFTS_PREFERENCES)
                            ));
                        } else {
                            childrenUpdates.add(new ChildUpdate(
                                    Integer.parseInt(((JSONObject) update).get(Constants.ID)
                                            .toString()),
                                    (ArrayList<Category>) ((JSONObject)
                                            update).get(Constants.GIFTS_PREFERENCES)
                            ));
                        }
                    }
                }
                annualChanges.add(new AnnualChange(
                        newSantaBudget, newGifts, newChildren, childrenUpdates));
            }
        }
        return annualChanges;
    }

    /**
     *
     * @param jsonChildren a
     * @return b
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<Child> readChildren(final JSONArray jsonChildren) {
        ArrayList<Child> children = new ArrayList<>();
        if (jsonChildren != null) {
            for (Object jsonChild : jsonChildren) {
                children.add(new Child(
                        Integer.parseInt(((JSONObject) jsonChild).get(Constants.ID)
                                .toString()),
                        (String) ((JSONObject) jsonChild).get(Constants.LAST_NAME),
                        (String) ((JSONObject) jsonChild).get(Constants.FIRST_NAME),
                        (String) ((JSONObject) jsonChild).get(Constants.CITY),
                        Integer.parseInt(((JSONObject) jsonChild).get(Constants.AGE)
                                .toString()),
                        (ArrayList<Category>) ((JSONObject)
                                jsonChild).get(Constants.GIFTS_PREFERENCES),
                        Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE)
                                .toString())
                ));
            }
        }
        return children;
    }

    /**
     * a
     * @param jsonGifts b
     * @return c
     */
    public static ArrayList<Gift> readGifts(final JSONArray jsonGifts) {
        ArrayList<Gift> gifts = new ArrayList<>();
        if (jsonGifts != null) {
            for (Object jsonGift : jsonGifts) {
                gifts.add(new Gift(
                        (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                        Double.parseDouble(((JSONObject) jsonGift).get(Constants.PRICE)
                                .toString()),
                        (String) ((JSONObject) jsonGift).get(Constants.CATEGORY)
                ));
            }
        }
        return gifts;
    }
}
