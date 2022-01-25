package IO;

import java.io.*;
import java.nio.file.Path;

public class Writer {
    public static void write(String message, Path path, String name) {
        try (FileWriter fileWriter = new FileWriter(path.toString() + "/" + name)) {
            for (char ch : message.toCharArray()) {
                fileWriter.write(ch);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}