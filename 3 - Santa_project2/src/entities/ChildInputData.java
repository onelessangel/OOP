package entities;

import enums.Category;
import enums.City;
import enums.ElvesType;

import java.util.ArrayList;

public class ChildInputData {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected int age;
    protected City city;
    protected Double niceScore;
    protected ArrayList<Category> giftsPreferences;
    protected int niceScoreBonus;
    protected ElvesType elf;

    public ChildInputData() {
    }

    public static final class Builder {
        //same properties
        private final int id;                               // mandatory
        private final String lastName;                      // mandatory
        private final String firstName;                     // mandatory
        private final int age;                              // mandatory
        private final City city;                            // mandatory
        private final Double niceScore;                     // mandatory
        private final ArrayList<Category> giftsPreferences; // mandatory
        private int niceScoreBonus = 0;                     // optional
        private final ElvesType elf;                        // mandatory

        // constructors have only the mandatory properties\
        public Builder(final int id, final String lastName, final String firstName, final int age,
                           final City city, final Double niceScore,
                           final ArrayList<Category> giftsPreferences, final ElvesType elf) {
            this.id = id;
            this.lastName = lastName;
            this.firstName = firstName;
            this.age = age;
            this.city = city;
            this.niceScore = niceScore;
            this.giftsPreferences = giftsPreferences;
            this.elf = elf;
        }

        public Builder(final ChildInputData child) {
            this(child.id, child.lastName, child.firstName, child.age, child.city, child.niceScore,
                    child.giftsPreferences, child.elf);
            this.niceScoreBonus = child.niceScoreBonus;
        }

        /**
         * Adds optional niceScoreBonus element.
         * @param bonus to be added
         * @return current Builder instance
         */
        public Builder niceScoreBonus(final int bonus) {
            niceScoreBonus = bonus;
            return this;
        }

        /**
         * Creates the current AnnualChange instance.
         */
        public ChildInputData build() {
            return new ChildInputData(this);
        }
    }

    private ChildInputData(final Builder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.age = builder.age;
        this.city = builder.city;
        this.niceScore = builder.niceScore;
        this.giftsPreferences = builder.giftsPreferences;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.elf = builder.elf;
    }

    /**
     * for checkstyle
     * Getter used for accessing the id.
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * for checkstyle
     * Getter used for accessing the last name.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * for checkstyle
     * Getter used for accessing the first name.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * for checkstyle
     * Getter used for accessing the age.
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * for checkstyle
     * Getter used for accessing the city.
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * for checkstyle
     * Getter used for accessing the niceness score.
     * @return the niceness score
     */
    public double getNiceScore() {
        return niceScore;
    }

    /**
     * for checkstyle
     * Getter used for accessing the gifts preferences.
     * @return the gifts preferences
     */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * for checkstyle
     * Getter used for accessing the nice score bonus.
     * @return the nice score bonus
     */
    public int getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * for checkstyle
     * Getter used for accessing the elf.
     * @return the elf
     */
    public ElvesType getElf() {
        return elf;
    }

    /**
     * for checkstyle
     * Setter used to set the gifts preferences value.
     */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * for checkstyle
     * Setter used to set the elf type.
     */
    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }

    /**
     * for checkstyle
     * Method used for debug.
     */
    @Override
    public String toString() {
        return "Child{"
                + "id=" + id
                + ", lastName=" + lastName
                + ", firstName=" + firstName
                + ", age=" + age
                + ", city=" + city
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + ", niceScoreBonus=" + niceScoreBonus
                + ", elf=" + elf
                + '}' + '\n';
    }
}
