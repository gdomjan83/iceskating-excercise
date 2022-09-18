package skater;

import lombok.Getter;

@Getter
public class Skater implements Comparable<Skater> {
    private String name;
    private String countryCode;
    private double technicalPoints;
    private double componentPoints;
    private double errors;
    private double totalpoints;

    public Skater(String name, String countryCode, double technicalPoints, double componentPoints, double errors) {
        this.name = name;
        this.countryCode = countryCode;
        this.technicalPoints = technicalPoints;
        this.componentPoints = componentPoints;
        this.errors = errors;
        this.totalpoints = technicalPoints + componentPoints - errors;
    }

    public Skater(String name, String countryCode, double totalpoints) {
        this.name = name;
        this.countryCode = countryCode;
        this.totalpoints = totalpoints;
    }

    @Override
    public int compareTo(Skater o) {
        if (o.totalpoints > this.totalpoints) {
            return 1;
        }
        if (o.totalpoints < this.totalpoints) {
            return -1;
        }
        return 0;
    }
}
