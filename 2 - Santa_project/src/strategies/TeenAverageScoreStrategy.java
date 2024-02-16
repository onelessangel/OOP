package strategies;

import java.util.ArrayList;

public final class TeenAverageScoreStrategy implements AverageScoreStrategy<ArrayList<Double>> {
    @Override
    public Double getAverageScore(final ArrayList<Double> list) {
        double averageScore = 0d;
        double sumIndexRange;
        int index = 1;

        for (double score : list) {
            averageScore += score * index;
            index++;
        }

        sumIndexRange = ((double) list.size() * (list.size() + 1)) / 2;
        averageScore /= sumIndexRange;

        return averageScore;
    }
}
