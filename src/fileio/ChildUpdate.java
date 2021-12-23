package fileio;

import java.util.ArrayList;

public class ChildUpdate {
    private final int id;
    private final double newNiceScore;
    private final ArrayList<String> newGiftsPreferences;

    public ChildUpdate(final int id, final double newNiceScore,
                       final ArrayList<String> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
    }

    public ChildUpdate(final int id, final ArrayList<String> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = 0;
        this.newGiftsPreferences = newGiftsPreferences;
    }
}
