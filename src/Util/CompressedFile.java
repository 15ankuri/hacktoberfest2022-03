package Util;

import com.google.common.collect.BiMap;

import java.io.*;

/*
This class binds encoded message, its huffman table as well as length of the original
message together,so that everything needed in order to decompress can be easily retrieved
from a single file.

Object of this class is serialized and saved as a compressed file.
 */

public class CompressedFile implements Serializable {
    BiMap<Character, String> huffmanTable; // Stores huffman table which can be used to decode the message later
    String message; // Stores encoded message
    int length; // Stores size(characters) of the original message
    String extension; // Stores the extension of the original file

    public CompressedFile(BiMap<Character, String> huffmanTable, String message, int length, String extension) {
        this.huffmanTable = huffmanTable;
        this.message = compress(message);
        this.length = length;
        this.extension = extension;
    }

    // Converts binary string to bit stream as string(sequence of 2-bytes character)
    private static String compress(String message) {
        StringBuilder compressedMessage = new StringBuilder();
        StringBuilder bitStream = new StringBuilder();
        for (char ch : message.toCharArray()) {
            bitStream.append(ch);
            if (bitStream.length() == 16) {
                compressedMessage.append(bitstreamToCharacter(bitStream.toString()));
                bitStream = new StringBuilder();
            }
        }
        if (bitStream.length() != 0) compressedMessage.append(bitstreamToCharacter(bitStream.toString()));
        return compressedMessage.toString();
    }

    // Converts binary string of length 16 to integer and returns it as a character
    private static char bitstreamToCharacter(String bitStream) {
        int res = Integer.valueOf(bitStream, 2) << (16 - bitStream.length());
        return (char) res;
    }

    // Converts string representation(sequence of 2-bytes character) of bit stream to binary string
    private static String decompress(String compressedMessage) {
        StringBuilder decompressedMessage = new StringBuilder();
        for (char ch : compressedMessage.toCharArray()) {
            decompressedMessage.append(characterToBitstream(ch));
        }
        return decompressedMessage.toString();
    }

    // Converts 16-bit character to its binary representation
    private static String characterToBitstream(char ch) {
        StringBuilder res = new StringBuilder(Integer.toBinaryString(ch));
        while (res.length() < 16) {
            res.insert(0, "0");
        }
        return res.toString();
    }

    // Deserializes file from the specified path and returns object of CompressedFile type
    public static CompressedFile deserializeFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (CompressedFile) objectInputStream.readObject();
    }

    public BiMap<Character, String> getHuffmanTable() {
        return this.huffmanTable;
    }

    public String getMessage() {
        return decompress(this.message);
    }

    public int getLength() {
        return this.length;
    }

    public String getExtension() {
        return this.extension;
    }

    // Serializes object of CompressedFile and saves it to the specified path
    public void serializeFile(String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }
}
