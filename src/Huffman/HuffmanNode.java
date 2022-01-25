package Huffman;

class HuffmanNode implements Comparable<HuffmanNode> {
    char value;
    int frequency;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(char value, int frequency) {
        this.value = value;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public HuffmanNode(char value, int frequency, HuffmanNode left, HuffmanNode right) {
        this.value = value;
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.frequency - other.frequency;
    }
}
