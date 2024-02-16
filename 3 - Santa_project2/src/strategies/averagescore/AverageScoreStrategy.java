package strategies.averagescore;

public interface AverageScoreStrategy<T> {
    /**
     * Computes the average score based on the scores in a given list.
     * @param list of scores
     * @return the average score
     */
    Double getAverageScore(T list);
}
