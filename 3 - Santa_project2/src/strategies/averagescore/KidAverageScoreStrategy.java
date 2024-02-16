package strategies.averagescore;

import java.util.ArrayList;

public final class KidAverageScoreStrategy implements AverageScoreStrategy<ArrayList<Double>> {
    @Override
    public Double getAverageScore(final ArrayList<Double> list) {
        return list.stream().mapToDouble(e -> e).sum() / list.size();
    }
}
