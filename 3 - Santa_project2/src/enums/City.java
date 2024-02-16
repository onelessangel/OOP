package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum City {
    @JsonProperty("Bucuresti")
    BUCURESTI("Bucuresti"),

    @JsonProperty("Constanta")
    CONSTANTA("Constanta"),

    @JsonProperty("Buzau")
    BUZAU("Buzau"),

    @JsonProperty("Timisoara")
    TIMISOARA("Timisoara"),

    @JsonProperty("Cluj-Napoca")
    CLUJ("Cluj-Napoca"),

    @JsonProperty("Iasi")
    IASI("Iasi"),

    @JsonProperty("Craiova")
    CRAIOVA("Craiova"),

    @JsonProperty("Brasov")
    BRASOV("Brasov"),

    @JsonProperty("Braila")
    BRAILA("Braila"),

    @JsonProperty("Oradea")
    ORADEA("Oradea");

    private String value;

    City(final String value) {
        this.value = value;
    }

    /**
     * Retrieves the Enum by value
     * @param value of the Enum
     * @return the Enum with the given value
     */
    public static City getEnumByString(final String value) {
        for (City e : City.values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }

        return null;
    }
}
