package fileinputoutput;

import skater.Skater;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Skater> readFile(String path) {
        try (BufferedReader br = Files.newBufferedReader(Path.of(path))) {
            String line;
            List<Skater> result = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                result.add(createSkater(line));
            }
            return result;
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }
    }

    private Skater createSkater(String line) {
        String[] components = line.split(";");
        return new Skater(
                components[0],
                components[1],
                Double.parseDouble(components[2]),
                Double.parseDouble(components[3]),
                Double.parseDouble(components[4]));
    }
}
