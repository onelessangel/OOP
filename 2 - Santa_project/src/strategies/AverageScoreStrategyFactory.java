package strategies;

import enums.ChildType;

import java.util.ArrayList;

public final class AverageScoreStrategyFactory {
    private AverageScoreStrategyFactory() {
        // constructor for checkstyle
    }

    /**
     * Creates a new strategy based on the child's type.
     * @param type of the child, based on its age
     * @return the correct strategy to be used
     */
    public static AverageScoreStrategy<ArrayList<Double>> createStrategy(final ChildType type) {
        switch (type) {
            case BABY:        return new BabyAverageScoreStrategy();
            case KID:         return new KidAverageScoreStrategy();
            case TEEN:        return new TeenAverageScoreStrategy();
            case YOUNG_ADULT: return new YAAverageScoreStrategy();
            default:          throw  new IllegalArgumentException("The child type is not valid.");
        }
    }
}
