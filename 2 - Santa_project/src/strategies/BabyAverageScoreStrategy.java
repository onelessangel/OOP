package strategies;

import common.Constants;

import java.util.ArrayList;

public final class BabyAverageScoreStrategy implements AverageScoreStrategy<ArrayList<Double>> {
    @Override
    public Double getAverageScore(final ArrayList<Double> list) {
        return Constants.MAX_AVG_SCORE;
    }
}
