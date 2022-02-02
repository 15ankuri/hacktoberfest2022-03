package main;

import com.google.common.collect.BiMap;
import main.Codec.Decoder;
import main.Codec.Encoder;
import main.Huffman.HuffmanTable;
import main.Util.CompressedFile;
import main.Util.ConsoleColors;
import main.Util.IO;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Driver class which is the entry point of the application.
This class directly interacts with the user.
 */

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(ConsoleColors.PURPLE_BOLD_BRIGHT + "Welcome to Huffman Compressor by " + ConsoleColors.YELLOW_BOLD_BRIGHT + "insane_banda" + ConsoleColors.PURPLE_BOLD_BRIGHT + ":" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "Modes Available:" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "1: Encoding Mode" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "2: Decoding Mode" + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE_BOLD_BRIGHT + "0: Exit" + ConsoleColors.RESET);
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) System.exit(0);

            Pattern fileValidator = Pattern.compile("([\\w_\\-.\\s/]+?)/([\\w_\\-.\\s]+?)\\.([\\w_.\\-\\s]+)");
            System.out.print(ConsoleColors.PURPLE_BOLD_BRIGHT + "Enter path of the file:" + ConsoleColors.RESET);
            String path = sc.nextLine();
            Matcher matcher = fileValidator.matcher(path);

            if (!matcher.matches()) {
                System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Invalid Path!\n" + ConsoleColors.RESET);
                continue;
            }

            switch (choice) {
                case 1 -> {
                    String message = IO.read(path);
                    int length = message.length();
                    BiMap<Character, String> huffmanTable = HuffmanTable.makeTable(message);
                    String encodedMessage = Encoder.encode(message, huffmanTable);
                    CompressedFile file = new CompressedFile(huffmanTable, encodedMessage, length, matcher.group(3));
                    file.serializeFile(matcher.group(1) + "/" + matcher.group(2) + ".cmp");
                }
                case 2 -> {
                    CompressedFile file = CompressedFile.deserializeFile(path);
                    String message = file.getMessage();
                    BiMap<Character, String> huffmanTable = file.getHuffmanTable();
                    int length = file.getLength();
                    String extension = file.getExtension();
                    String decodedMessage = Decoder.decode(message, huffmanTable);
                    IO.write(decodedMessage.substring(0, length), matcher.group(1) + "/Decoded." + extension);
                }
                default -> System.exit(0);
            }
        }
    }
}