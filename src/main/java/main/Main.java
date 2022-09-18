package main;

import fileinputoutput.ConsoleWriter;
import fileinputoutput.FileReader;
import operations.Result;

public class Main {

    public static void main(String[] args) {
        ConsoleWriter console = new ConsoleWriter(
                new Result(new FileReader(),"src/main/resources/rovidprogram.csv", "src/main/resources/donto.csv"));
        console.writeToConsole();
    }
}
