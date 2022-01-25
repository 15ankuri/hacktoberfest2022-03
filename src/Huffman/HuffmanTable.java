package Huffman;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.PriorityQueue;

public class HuffmanTable {
    public static BiMap<Character, String> makeTable(String message, Path path) throws IOException {
        // Creating frequency table
        HashMap<Character, Integer> frequencyTable = new HashMap<>();
        for (char ch : message.toCharArray()) {
            frequencyTable.put(ch, frequencyTable.getOrDefault(ch, 0) + 1);
        }

        // Creating priority queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();

        // Filling priority(min-heap) queue with HuffmanNodes
        for (char key : frequencyTable.keySet()) {
            pq.offer(new HuffmanNode(key, frequencyTable.get(key)));
        }

        // Building Huffman tree
        while (pq.size() > 1) {
            HuffmanNode min1 = pq.poll();
            HuffmanNode min2 = pq.poll();
            assert min2 != null;
            pq.offer(new HuffmanNode('\0', min1.frequency + min2.frequency, min1, min2));
        }

        // Initializing the Root of Huffman tree
        HuffmanNode root = pq.poll();

        // Create and save huffman table
        BiMap<Character, String> huffmanTable = HashBiMap.create();
        assert root != null;
        traverseHuffmanTree(root, "", huffmanTable);

        // Save huffman table
        FileOutputStream fileOutputStream = new FileOutputStream(path.toString() + "/" + "HuffmanTable.tb");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(huffmanTable);
        objectOutputStream.flush();
        objectOutputStream.close();

        // return huffman table
        return huffmanTable;
    }

    public static void traverseHuffmanTree(HuffmanNode node, String characterSet, BiMap<Character, String> huffmanTable) {
        if (node.left == null && node.right == null) {
            huffmanTable.put(node.value, characterSet);
            return;
        }

        assert node.left != null;
        traverseHuffmanTree(node.left, characterSet + "0", huffmanTable);
        traverseHuffmanTree(node.right, characterSet + "1", huffmanTable);
    }


    public static BiMap<Character, String> retrieveTable(Path path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(path + "/" + "HuffmanTable.tb");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        @SuppressWarnings("unchecked")
        BiMap<Character, String> huffmanTable = (BiMap<Character, String>) objectInputStream.readObject();
        objectInputStream.close();
        return huffmanTable;
    }

}
