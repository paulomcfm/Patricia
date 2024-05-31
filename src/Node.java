import java.util.Objects;

public class Node {
    private char[] letters;
    private Node[] children;
    private int index;
    private String word;

    public Node(String string) {
        this.letters = new char[26];
        this.children = new Node[26];
        this.index = -1;
        this.word = string;
    }

    public Node() {
        this.letters = new char[26];
        this.children = new Node[26];
        this.index = -1;
        this.word = "";
    }

    public char getLetters(int pos) {
        return letters[pos];
    }


    public void setLetters(char letter, int pos) {
        this.letters[pos] = letter;
    }


    public Node getChildren(int pos) {
        return children[pos];
    }

    public void setChildren(Node children, int pos) {
        this.children[pos] = children;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }


    public Node getImmediateWord() {
        Node node = null;
        if (Objects.equals(this.word, "")) {
            for (int i = 0; i < 26 && node==null; i++) {
                if (this.children[i] != null && !Objects.equals(this.children[i].getWord(), "")) {
                    node = this.children[i];
                }
            }
            return node;
        }
        return this;
    }

    public int firstChildPosition() {
        boolean found = false;
        int i = 0;
        while (i < 26 && !found) {
            if (this.children[i] != null) {
                found = true;
            } else {
                i++;
            }
        }
        return i;
    }
}
