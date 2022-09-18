package fileinputoutput;

import operations.Result;

import java.util.Map;

public class ConsoleWriter {
    private Result result;

    public ConsoleWriter(Result result) {
        this.result = result;
    }

    public void writeToConsole() {
        System.out.printf("2. feladat\nA rövidprogramban %d induló volt.\n", result.getNumberOfSkatersInShortProgram());
        System.out.println("3. feladat\n" + checkHungarian());
        System.out.println("5. feladat\n");
        getSkaterName();
        System.out.println("7. feladat");
        writeStatistics();
        new FileWriter().writeFile("vegeredmeny.csv", result.createFinalResultInString(result.getFinals()));
    }

    private String checkHungarian() {
        if (result.checkForHungarianInFinal()) {
            return "A magyar versenyző bejutott a kűrbe.";
        } else {
            return "A magyar versenyző nem jutott be a kűrbe.";
        }
    }

    private void getSkaterName() {
        String inputName = result.inputSkaterName();
        if (!"Ilyen nevű induló nem volt!".equals(inputName)) {
            System.out.println("6. feladat\nA versenyző összpontszáma: " + result.getTotalPointsForSkater(inputName));
        } else {
            System.out.println("Ilyen nevű induló nem volt!");
        }
    }

    private void writeStatistics() {
        Map<String, Integer> statistics = result.createStatistics();
        for (Map.Entry<String, Integer> actual : statistics.entrySet()) {
            if (actual.getValue() > 1) {
                System.out.println(actual.getKey() + ": " + actual.getValue() + " versenyző");
            }
        }
    }
}
