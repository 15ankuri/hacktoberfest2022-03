package IO;

import java.io.*;
import java.nio.file.Path;

public class Reader {
    public static String read(Path path) throws Exception {
        StringBuilder message = new StringBuilder();
        try {
            File file = new File(path.toString());

            if (!file.canRead())
                throw new Exception("Cannot read file!");

            FileReader fileReader = new FileReader(file);
            int line;
            while ((line = fileReader.read()) != -1) {
                message.append((char) line);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return message.toString();
    }
}
