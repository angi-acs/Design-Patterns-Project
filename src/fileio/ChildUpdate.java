package fileio;

import common.Constants;
import lombok.Getter;

import java.util.ArrayList;

@Getter
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

    // constructor in case there is no update for niceScore
    public ChildUpdate(final int id, final ArrayList<String> newGiftsPreferences) {
        this.id = id;
        this.newNiceScore = Constants.NO_NEW_NICE_SCORE;
        this.newGiftsPreferences = newGiftsPreferences;
    }
}
