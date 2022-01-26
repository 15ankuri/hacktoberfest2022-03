package Util;

import java.io.*;

public class Util {

    public static void serializeFile(String path, CompressedFile file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(file);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    public static CompressedFile deserializeFile(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (CompressedFile) objectInputStream.readObject();
    }

    public static String compress(String message) {
        StringBuilder compressedMessage = new StringBuilder();
        StringBuilder byteStream = new StringBuilder();
        for (char ch : message.toCharArray()) {
            if (byteStream.length() == 16) {
                compressedMessage.append(bitstreamToCharacter(byteStream));
                byteStream = new StringBuilder();
            }
            byteStream.append(ch);
        }
        if (byteStream.length() != 0) compressedMessage.append(bitstreamToCharacter(byteStream));
        return compressedMessage.toString();
    }

    private static char bitstreamToCharacter(StringBuilder bitStream) {
        int res = Integer.valueOf(bitStream.toString(), 2) << (16 - bitStream.length());
        return (char) res;
    }

    public static String decompress(String message) {
        StringBuilder decompressedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            decompressedMessage.append(characterToBitstream(ch));
        }
        return decompressedMessage.toString();
    }

    public static String characterToBitstream(char ch) {
        StringBuilder res = new StringBuilder(Integer.toBinaryString(ch));
        while (res.length() < 16) {
            res.insert(0, "0");
        }
        return res.toString();
    }

}
