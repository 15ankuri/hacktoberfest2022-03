package Util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class IO {
    public static String read(String path) {
        return getString(path);
    }

    static String getString(String path) {
        StringBuilder message = null;
        try (FileReader fileReader = new FileReader(path)) {
            message = new StringBuilder();
            int line;
            while ((line = fileReader.read()) != -1) message.append((char) line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return message.toString();
    }

    public static void write(String message, String path) {
        getFile(message, path);
    }

    static void getFile(String message, String path) {
        File file = new File(path);
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (char ch : message.toCharArray()) {
                fileWriter.write(ch);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

}
