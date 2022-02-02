import Codec.Decoder;
import Codec.Encoder;
import Huffman.HuffmanTable;
import Util.CompressedFile;
import Util.IO;
import com.google.common.collect.BiMap;

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
            System.out.println("\nWelcome to Huffman Compressor by insane_banda:");
            System.out.println("Modes Available:");
            System.out.println("1: Encoding Mode");
            System.out.println("2: Decoding Mode");
            System.out.println("0: Exit");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 0) System.exit(0);

            Pattern fileValidator = Pattern.compile("([\\w_\\-.\\s/]+?)/([\\w_\\-.\\s]+?)\\.([\\w_.\\-\\s]+)");
            System.out.print("Enter path of the file: ");
            String path = sc.nextLine();
            System.out.print("\n");
            Matcher matcher = fileValidator.matcher(path);

            if (!matcher.matches()) {
                System.out.println("Invalid Path!");
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