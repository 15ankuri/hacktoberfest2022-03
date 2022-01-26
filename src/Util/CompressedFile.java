package Util;

import com.google.common.collect.BiMap;

import java.io.Serializable;

public class CompressedFile implements Serializable {
    BiMap<Character, String> huffmanTable;
    String message;
    int length;

    public CompressedFile(BiMap<Character, String> huffmanTable, String message, int length) {
        this.huffmanTable = huffmanTable;
        this.message = message;
        this.length = length;
    }

    public BiMap<Character, String> getHuffmanTable() {
        return this.huffmanTable;
    }

    public String getMessage() {
        return this.message;
    }

    public int getLength() {
        return this.length;
    }
}
