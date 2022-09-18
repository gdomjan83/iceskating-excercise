package operations;

import fileinputoutput.FileReader;
import lombok.Getter;
import skater.Skater;

import java.util.*;

@Getter
public class Result {
    private FileReader reader;
    private List<Skater> shortProgram;
    private List<Skater> finals;


    public Result(FileReader reader, String shortProgramPath, String finalsPath) {
        this.reader = reader;
        this.shortProgram = reader.readFile(shortProgramPath);
        this.finals = reader.readFile(finalsPath);
    }

    public int getNumberOfSkatersInShortProgram() {
        return shortProgram.size();
    }

    public boolean checkForHungarianInFinal() {
        return finals.stream().anyMatch(s -> "HUN".equals(s.getCountryCode()));
    }

    public double getTotalPointsForSkater(String name) {
        Optional<Skater> skater = findSkaterByName(shortProgram, name);
        Optional<Skater> skaterInFinal = findSkaterByName(finals, name);
        double shortProgramPoints = skater.isPresent() ? (skater.get().getTotalpoints()) : 0;
        double finalsProgramPoints = skaterInFinal.isPresent() ? (skaterInFinal.get().getTotalpoints()) : 0;
        return shortProgramPoints + finalsProgramPoints;
    }

    public String inputSkaterName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Kérem a versenyző nevét: ");
        String input = sc.nextLine();
        sc.close();
        if (findSkaterByName(shortProgram, input).isEmpty()) {
            return "Ilyen nevű induló nem volt!";
        }
        return input;
    }

    public Map<String, Integer> createStatistics() {
        Map<String, Integer> statistics = new LinkedHashMap<>();
        for (Skater actual : finals) {
            statistics.compute(actual.getCountryCode(), (key, value) -> value == null ? 1 : value + 1);
        }
        return statistics;
    }

    public List<String> createFinalResultInString(List<Skater> finalists) {
        List<Skater> finalistsInOrder = getFinalResultInOrder(finalists);
        return getFinalistsInStringInOrder(finalistsInOrder);
    }

    private List<String> getFinalistsInStringInOrder(List<Skater> finalists) {
        List<String> result = new ArrayList<>();
        int position = 1;
        for (Skater actual : finalists) {
            StringBuilder sb = new StringBuilder();
            sb.append(position + ";");
            sb.append(actual.getName() + ";" + actual.getCountryCode() + ";" + getTotalPointsForSkater(actual.getName()));
            result.add(sb.toString());
            position++;
        }
        return result;
    }

    private List<Skater> getFinalResultInOrder(List<Skater> finalistSkaters) {
        List<Skater> result = new ArrayList<>();
        for (Skater actual : finalistSkaters) {
            double totalPoints = getTotalPointsForSkater(actual.getName());
            result.add(new Skater(actual.getName(), actual.getCountryCode(), totalPoints));
        }
        return result.stream().sorted().toList();

    }

    private Optional<Skater> findSkaterByName(List<Skater> skaters, String name) {
        return skaters.stream()
                .filter(s -> name.equals(s.getName()))
                .findFirst();
    }
}
