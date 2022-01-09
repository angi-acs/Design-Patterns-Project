package strategies;

import common.Constants;

public class StrategyFactory {

    /**
     * a
     * @param strategy b
     * @return c
     */
    public Strategy createStrategy(final String strategy) {
        switch (strategy) {
            case Constants.ID -> {
                return new Id();
            }
            case Constants.NICE_SCORE -> {
                return new NiceScore();
            }
            case Constants.NICE_SCORE_CITY -> {
                return new NiceScoreCity();
            }
            default -> throw new IllegalStateException("Unexpected value: " + strategy);
        }
    }
}
