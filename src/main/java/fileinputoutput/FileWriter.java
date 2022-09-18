package fileinputoutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileWriter {

    public void writeFile(String fileName, List<String> input) {
        try {
            Files.write(Path.of("src/main/resources/" + fileName), input);
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not write file.", ioe);
        }
    }
}
