package fileio;

import common.Constants;

import java.util.ArrayList;

public class ChildUpdate {
    private final int id;
    private final double newNiceScore;
    private final ArrayList<String> newGiftsPreferences;
    private final String elf;

    public ChildUpdate(final int id, final double newNiceScore,
                       final ArrayList<String> newGiftsPreferences,
                       final String elf) {
        this.id = id;
        this.newNiceScore = newNiceScore;
        this.newGiftsPreferences = newGiftsPreferences;
        this.elf = elf;
    }

    // constructor in case there is no update for niceScore
    public ChildUpdate(final int id, final ArrayList<String> newGiftsPreferences,
                       final String elf) {
        this.id = id;
        this.newNiceScore = Constants.NO_NEW_NICE_SCORE;
        this.newGiftsPreferences = newGiftsPreferences;
        this.elf = elf;
    }

    /**
     * checkstyle
     */
    public int getId() {
        return id;
    }

    /**
     * checkstyle
     */
    public double getNewNiceScore() {
        return newNiceScore;
    }

    /**
     * checkstyle
     */
    public ArrayList<String> getNewGiftsPreferences() {
        return newGiftsPreferences;
    }

    /**
     * checkstyle
     */
    public String getElf() {
        return elf;
    }
}
