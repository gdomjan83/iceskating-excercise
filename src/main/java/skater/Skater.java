package skater;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Skater {
    private String name;
    private String countryCode;
    private double technicalPoints;
    private double componentPoints;
    private double errors;
}
