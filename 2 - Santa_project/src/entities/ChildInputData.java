package entities;

import java.util.ArrayList;

public class ChildInputData {
    protected int id;
    protected String lastName;
    protected String firstName;
    protected int age;
    protected String city;
    protected Double niceScore;
    protected ArrayList<String> giftsPreferences;

    public ChildInputData() {

    }

    public ChildInputData(final int id, final String lastName, final String firstName,
                              final int age, final String city, final Double niceScore,
                              final ArrayList<String> giftsPreferences) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
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
    public String getCity() {
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
    public ArrayList<String> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * for checkstyle
     * Setter used to set the gifts preferences value.
     */
    public void setGiftsPreferences(final ArrayList<String> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * for checkstyle
     * Method used for debug.
     */
    @Override
    public String toString() {
        return "Child{"
                + "id=" + id
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", age=" + age
                + ", city='" + city + '\''
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + giftsPreferences
                + '}' + '\n';
    }
}
