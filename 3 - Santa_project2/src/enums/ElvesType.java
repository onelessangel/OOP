package enums;

import com.fasterxml.jackson.annotation.JsonProperty;


public enum ElvesType {

    @JsonProperty("yellow")
    YELLOW("yellow"),

    @JsonProperty("black")
    BLACK("black"),

    @JsonProperty("pink")
    PINK("pink"),

    @JsonProperty("white")
    WHITE("white");

    private String value;

    ElvesType(final String value) {
        this.value = value;
    }

    /**
     * Retrieves the Enum by value
     * @param value of the Enum
     * @return the Enum with the given value
     */
    public static ElvesType getEnumByString(final String value) {
        for (ElvesType e : ElvesType.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }

        return null;
    }
}
