package Codec;

import com.google.common.collect.BiMap;

public class Decoder {
    // Inputs encoded message, decodes it using huffman table then return the decoded message
    public static String decode(String message, BiMap<Character, String> huffmanTable) {
        StringBuilder decodedMessage = new StringBuilder();
        String characterSet = "";
        for (char ch : message.toCharArray()) {
            characterSet += ch;
            if (huffmanTable.inverse().containsKey(characterSet)) {
                decodedMessage.append(huffmanTable.inverse().get(characterSet));
                characterSet = "";
            }
        }
        if (!"".equals(characterSet)) {
            int prev = 0;
            int i = 1;
            int n = characterSet.length();
            while (i <= n) {
                String substring = characterSet.substring(prev, i);
                if (huffmanTable.inverse().containsKey(substring)) {
                    decodedMessage.append(huffmanTable.inverse().get(substring));
                    prev = i + 1;
                }
                i++;
            }
        }
        return decodedMessage.toString();
    }
}