import Codec.Decoder;
import Codec.Encoder;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Huffman Compressor by insane_banda:");
        System.out.println("Modes Available:");
        System.out.println("1: Encoding Mode");
        System.out.println("2: Decoding Mode");
        System.out.println("0: Exit");
        int choice = sc.nextInt();
        sc.nextLine();

        if (choice == 0) System.exit(0);

        System.out.print("Enter path of the file: ");
        Path filePath = Path.of(sc.nextLine());
        System.out.print("\n");

        switch (choice) {
            case 1 -> Encoder.encode(filePath, "Encoded.txt");
            case 2 -> Decoder.decode(filePath, "Decoded.txt");
            default -> System.exit(0);
        }

        sc.close();
    }
}