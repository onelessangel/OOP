package strategies.years;

import databases.Database;
import entities.Child;
import enums.City;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;

public final class NiceScoreCityYearStrategy implements YearStrategy {
    @Override
    public void assignGifts() {
        // map consisting of <city,cityAverageScore> elements
        HashMap<City, Double> citiesMap = new HashMap<>();

        // computes total average score for each city
        for (City city : City.values()) {
            Double cityAverageScore = 0d;
            double childrenCounter = 0;

            for (Child child : Database.getDatabase().getChildren()) {
                if (child.getCity().equals(city)) {
                    cityAverageScore += child.getAverageScore();
                    childrenCounter++;
                }
            }

            if (childrenCounter != 0) {
                cityAverageScore /= childrenCounter;
            }

            citiesMap.put(city, cityAverageScore);
        }

        List<City> sortedCityList = new LinkedList<>();

        // sorts cities by their average score and lexicographically
        citiesMap.entrySet().stream()
                    .sorted((e1, e2) -> {
                        if (Objects.equals(e1.getValue(), e2.getValue())) {
                            return e1.getKey().toString().compareTo(e2.getKey().toString());
                        } else {
                            return -e1.getValue().compareTo(e2.getValue());
                        }
                    })
                    .forEach(e -> sortedCityList.add(e.getKey()));

        // sorts children list by id
        Database.getDatabase().getChildren().sort(Comparator.comparing(Child::getId));

        // assign a gift to every child from the city list
        for (City city : sortedCityList) {
            for (Child child : Database.getDatabase().getChildren()) {
                if (!child.getCity().equals(city)) {
                    continue;
                }

                YearStrategy.assignGiftsToChild(child);
            }
        }
    }
}
