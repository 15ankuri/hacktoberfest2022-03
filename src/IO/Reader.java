package IO;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.file.Path;

public class Reader {
    public static @NotNull String read(Path path) {
        StringBuilder message = null;
        try (FileReader fileReader = new FileReader(path.toString())) {
            message = new StringBuilder();
            int line;
            while ((line = fileReader.read()) != -1) message.append((char) line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return message.toString();
    }
}
