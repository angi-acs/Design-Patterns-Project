package fileio;

import enums.Category;

import java.util.ArrayList;

public class ChildUpdate {
    private final int id;
    private final double newNiceScore;
    private final ArrayList<Category> newGiftsPreferences;

    public ChildUpdate(final int id, final double newNiceScore,
                       final ArrayList<Category> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
    }

    public ChildUpdate(final int id, final ArrayList<Category> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = 0;
        this.newGiftsPreferences = newGiftsPreferences;
    }
}
