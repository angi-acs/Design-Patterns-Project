package fileio;

import child.Child;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import santa.Gift;
import santa.Santa;
import utils.Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputReader {
    private final String inputPath;
    private int numberOfYears;

    public InputReader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Method that parses the JSON files' initialData object
     * @return new instance of Santa whose fields are initialized
     * by the test file (received as inputPath)
     */
    public Santa initialData() throws IOException, ParseException {
        double santaBudget = 0;
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Gift> gifts = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));
            numberOfYears = Integer.parseInt(jsonObject.get(Constants.YEARS).toString());
            santaBudget = Double.parseDouble(jsonObject.get(Constants.SANTA_BUDGET).toString());

            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);
            children = readChildren(jsonChildren);
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.SANTA_GIFTS);
            gifts = readGifts(jsonGifts);

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return new Santa(santaBudget, children, gifts);
    }

    /**
     * Method that parses the JSON files' annualChanges array
     * @return a list containing all annual changes written in the test file
     */
    public ArrayList<AnnualChange> annualChanges() throws IOException, ParseException {
        ArrayList<AnnualChange> annualChanges = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));

            JSONArray jsonAnnualChanges = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
            if (jsonAnnualChanges != null) {
                for (Object change : jsonAnnualChanges) {

                    double newSantaBudget = Double.parseDouble(((JSONObject) change)
                            .get(Constants.NEW_SANTA_BUDGET).toString());
                    JSONArray jsonNewGifts = (JSONArray) ((JSONObject) change)
                            .get(Constants.NEW_GIFTS);
                    ArrayList<Gift> newGifts = readGifts(jsonNewGifts);
                    JSONArray jsonNewChildren = (JSONArray) ((JSONObject) change)
                            .get(Constants.NEW_CHILDREN);
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
                                        Utils.convertJSONArray((JSONArray) ((JSONObject) update)
                                                .get(Constants.GIFTS_PREFERENCES)),
                                        (String) ((JSONObject) update).get(Constants.ELF)
                                ));
                            } else {
                                childrenUpdates.add(new ChildUpdate(
                                        Integer.parseInt(((JSONObject) update).get(Constants.ID)
                                                .toString()),
                                        Utils.convertJSONArray((JSONArray) ((JSONObject) update)
                                                .get(Constants.GIFTS_PREFERENCES)),
                                        (String) ((JSONObject) update).get(Constants.ELF)
                                ));
                            }
                        }
                    }
                    annualChanges.add(new AnnualChange(
                            newSantaBudget, newGifts, newChildren, childrenUpdates));
                }
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return annualChanges;
    }

    /**
     * Method that parses the JSON files' children array
     * (used in initialData and annualChanges)
     * @return a list of children (each child's fields are
     * initialized by the test file)
     */
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
                        Utils.convertJSONArray((JSONArray) ((JSONObject) jsonChild)
                                .get(Constants.GIFTS_PREFERENCES)),
                        Double.parseDouble(((JSONObject) jsonChild).get(Constants.NICE_SCORE)
                                .toString()),
                        Integer.parseInt(((JSONObject) jsonChild).get(Constants.NICE_SCORE_BONUS)
                                .toString()),
                        (String) ((JSONObject) jsonChild).get(Constants.ELF)
                ));
            }
        }
        return children;
    }

    /**
     * Method that parses the JSON files' gifts array
     * (used in initialData and annualChanges)
     * @return a list of gifts (each gift's fields are
     * initialized by the test file)
     */
    public static ArrayList<Gift> readGifts(final JSONArray jsonGifts) {
        ArrayList<Gift> gifts = new ArrayList<>();
        if (jsonGifts != null) {
            for (Object jsonGift : jsonGifts) {

                gifts.add(new Gift(
                        (String) ((JSONObject) jsonGift).get(Constants.PRODUCT_NAME),
                        Double.parseDouble(((JSONObject) jsonGift).get(Constants.PRICE)
                                .toString()),
                        (String) ((JSONObject) jsonGift).get(Constants.CATEGORY),
                        Integer.parseInt(((JSONObject) jsonGift).get(Constants.QUANTITY)
                                .toString())
                ));
            }
        }
        return gifts;
    }

    /**
     * checkstyle
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }
}
