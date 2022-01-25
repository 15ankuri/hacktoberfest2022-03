package IO;

import java.io.*;
import java.nio.file.Path;

public class Writer {
    public static void write(String message, Path path, String name) {
        try {
            File file = new File(path.toString() + "/" + name);
            FileWriter fileWriter = new FileWriter(file);
            for (char ch : message.toCharArray()) {
                fileWriter.write(ch);
            }
            fileWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}