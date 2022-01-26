import Codec.Decoder;
import Codec.Encoder;
import Huffman.HuffmanTable;
import Util.CompressedFile;
import Util.IO;
import Util.Util;
import com.google.common.collect.BiMap;

import java.io.IOException;
import java.util.Scanner;

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

            System.out.print("Enter path of the file: ");
            String path = sc.nextLine();
            System.out.print("\n");

            switch (choice) {
                case 1 -> {
                    String message = IO.read(path);
                    int length = message.length();
                    BiMap<Character, String> huffmanTable = HuffmanTable.makeTable(message);
                    String encodedMessage = Encoder.encode(message, huffmanTable);
                    String compressedMessage = Util.compress(encodedMessage);
                    CompressedFile file = new CompressedFile(huffmanTable, compressedMessage, length);
                    Util.serializeFile("./Encoded.cmp", file);
                }
                case 2 -> {
                    CompressedFile file = Util.deserializeFile(path);
                    String message = file.getMessage();
                    BiMap<Character, String> huffmanTable = file.getHuffmanTable();
                    int length = file.getLength();
                    String decodedMessage = Decoder.decode(Util.decompress(message), huffmanTable);
                    IO.write(decodedMessage.substring(0, length), "./Decoded.txt");
                }
                default -> System.exit(0);
            }
        }
    }
}