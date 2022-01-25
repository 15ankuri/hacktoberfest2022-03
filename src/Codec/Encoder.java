package Codec;

import Huffman.HuffmanTable;
import IO.Reader;
import IO.Writer;
import Util.PathFinder;
import com.google.common.collect.BiMap;
import java.nio.file.Path;

public class Encoder {
    public static void encode(Path filePath, String name) throws Exception {
        Path rootPath = PathFinder.rootPath(filePath);

        String message = Reader.read(filePath);
        BiMap<Character, String> huffmanTable = HuffmanTable.makeTable(message, rootPath);
        StringBuilder encodedMessage = new StringBuilder();
        for (char ch : message.toCharArray()) {
            encodedMessage.append(huffmanTable.get(ch));
        }

        Writer.write(encodedMessage.toString(), rootPath, name);
    }
}