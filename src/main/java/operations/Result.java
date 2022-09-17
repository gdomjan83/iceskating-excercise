package operations;

import fileinputoutput.FileReader;
import skater.Skater;

import java.util.*;

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
        double shortProgramPoints = skater.isPresent() ? calculatePoints(skater.get()) : 0;
        double finalsProgramPoints = skaterInFinal.isPresent() ? calculatePoints(skaterInFinal.get()) : 0;
        return shortProgramPoints + finalsProgramPoints;
    }

    public String inputSkaterName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Kérem a versenyző nevét: ");
        String input = sc.nextLine();
        System.out.println(input);
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


    private double calculatePoints(Skater skater) {
        return skater.getTechnicalPoints() + skater.getComponentPoints() - skater.getErrors();
    }

    private Optional<Skater> findSkaterByName(List<Skater> skaters, String name) {
        return skaters.stream()
                .filter(s -> name.equals(s.getName()))
                .findFirst();
    }
}
