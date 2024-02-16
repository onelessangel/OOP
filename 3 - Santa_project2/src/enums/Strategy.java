package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Strategy {
    @JsonProperty("niceScoreCity")
    NICE_SCORE_CITY("niceScoreCity"),

    @JsonProperty("id")
    ID("id"),

    @JsonProperty("niceScore")
    NICE_SCORE("niceScore");

    private String value;

    Strategy(final String value) {
        this.value = value;
    }

    /**
     * Retrieves the Enum by value
     * @param value of the Enum
     * @return the Enum with the given value
     */
    public static Strategy getEnumByString(final String value) {
        for (Strategy e : Strategy.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }

        return null;
    }
}
