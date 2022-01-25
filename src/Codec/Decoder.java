package Codec;

import Huffman.HuffmanTable;
import IO.Reader;
import IO.Writer;
import Util.PathFinder;
import com.google.common.collect.BiMap;
import java.nio.file.Path;

public class Decoder {
    public static void decode(Path filePath, String name) throws Exception {
        Path rootPath = PathFinder.rootPath(filePath);

        String encodedMessage = Reader.read(filePath);
        BiMap<Character, String> huffmanTable = HuffmanTable.retrieveTable(rootPath);
        StringBuilder decodedMessage = new StringBuilder();
        String characterSet = "";
        for (char ch : encodedMessage.toCharArray()) {
            characterSet += ch;
            if (huffmanTable.inverse().containsKey(characterSet)) {
                decodedMessage.append(huffmanTable.inverse().get(characterSet));
                characterSet = "";
            }
        }

        Writer.write(decodedMessage.toString(), rootPath, name);
    }
}