package main.Util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/*
Contains methods to interact with files.
*/

public class IO {
    // Inputs path of the file and returns the content of the file as string
    public static String read(String path) {
        StringBuilder message = null;
        try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_16)) {
            message = new StringBuilder();
            int line;
            while ((line = fileReader.read()) != -1) message.append((char) line);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return message.toString();
    }

    // Inputs string message that needs to be written in the file and the path where file need to be saved.
    public static void write(String message, String path) {
        File file = new File(path);
        try (OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_16)) {
            for (char ch : message.toCharArray()) {
                fileWriter.write(ch);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
