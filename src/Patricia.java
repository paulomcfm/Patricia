public class Patricia {
    private Node root;

    public int getLetterPosition(char letter) {
        return Character.toLowerCase(letter) - 'a';
    }

    public void insert(String word) {
        Node node, parent, nextWord, newNode, newWord;
        int pos, dif;
        if (root == null) { //1º caso: árvore vazia
            root = new Node();
            root.setWord(word);
            pos = getLetterPosition(word.charAt(0));
            root.setLetters(word.charAt(0), pos);
            root.setIndex(1);
            root.setChildren(new Node(word), pos);
        } else {
            node = getNode(word);
            if (node.getIndex() > 0 && node.getChildren(getLetterPosition(word.charAt(node.getIndex()))) == null) { // 2º caso, não tem nada pendurado no posição da letra, abacate
                newNode = new Node();
                newNode.setWord(word);
                node.setChildren(newNode, getLetterPosition(word.charAt(node.getIndex() - 1)));
            } else if (node.getIndex() > 0 && node.getChildren(getLetterPosition(word.charAt(node.getIndex()))) != null) { //3º caso, tem filho e não tem prefixo, sabão
                //ve a diferença com a proxima palavra
                nextWord = getNextWord(node);
                dif = differentLetter(nextWord, word);
                if (dif < node.getIndex()) { // se diferença ta antes do node, entao tem que criar um novo nó intermediario e apontar pra nova palavra e  node
                    parent = getParentNode(node, word);
                    newNode = new Node();
                    newNode.setLetters(word.charAt(dif - 1), getLetterPosition(word.charAt(dif - 1)));
                    newNode.setLetters(nextWord.getWord().charAt(dif - 1), getLetterPosition(nextWord.getWord().charAt(dif - 1)));
                    newNode.setIndex(dif);
                    newWord = new Node(word);
                    newNode.setChildren(newWord, getLetterPosition(word.charAt(dif - 1)));
                    newNode.setChildren(node, getLetterPosition(nextWord.getWord().charAt(dif - 1)));
                    parent.setChildren(newNode, getLetterPosition(word.charAt(node.getIndex() - 1)));
                } else { // diferença está no final da palavra nova, então apenas cria um nó intermediario antes e coloca a nova palavra no node
                    node.setWord(word);
                    parent = getParentNode(node, word);
                    newNode = new Node();
                    newNode.setChildren(node, getLetterPosition(word.charAt(dif - 1)));
                    newNode.setLetters(word.charAt(dif - 1), getLetterPosition(word.charAt(dif - 1)));
                    newNode.setIndex(dif);
                    parent.setChildren(newNode, getLetterPosition(word.charAt(node.getIndex() - 1)));
                }
            } else { // 4º caso, tem prefixo, solado
                node.setIndex(node.getWord().length()+1);
                newNode = new Node();
                newNode.setWord(word);
                newNode.setIndex(-1);
                node.setChildren(newNode, getLetterPosition(word.charAt(node.getIndex() - 1)));
                node.setLetters(word.charAt(node.getIndex() - 1), getLetterPosition(word.charAt(node.getIndex() - 1)));
            }
        }
    }

    private Node getNextWord(Node node) {
        Node current = node;
        Node nextWord = null;
        nextWord = current.getImmediateWord();
        while (nextWord == null && current.getIndex() > 0) {
            current = current.getChildren(current.firstChild());
            nextWord = current.getImmediateWord();
        }
        return nextWord;
    }

    private Node getParentNode(Node node, String word) {
        Node current = root;
        int i = 1;
        while (i < word.length() && current.getChildren(getLetterPosition(word.charAt(i))) != node) {
            current = current.getChildren(getLetterPosition(word.charAt(i)));
            if (current.getIndex() > 0)
                i = current.getIndex() - 1;
            else
                i = word.length();
        }
        return current;
    }

    private Node getNode(String word) {
        Node current = root;
        int i = 0;
        Node next = current.getChildren(getLetterPosition(word.charAt(i)));
        while (current.getIndex() != -1 && i < word.length() && next != null) {
            Node nextWord = getNextWord(current);
            if (next.getWord() == "" && differentLetter(nextWord, word) <= next.getIndex())
                i = word.length();
            else if (next.getWord() != "" && next.getWord().length() > word.length()) {
                i = word.length();
            } else {
                current = next;
                i = current.getIndex() - 1;
                if (current.getIndex() > 0 && i < word.length()) {
                    next = current.getChildren(getLetterPosition(word.charAt(i)));
                }
            }
        }
        return current;
    }

    private int differentLetter(Node nextWord, String word) {
        int i = 0;
        while (i + 1 < nextWord.getWord().length() && i + 1 < word.length() && nextWord.getWord().charAt(i) == word.charAt(i)) {
            i++;
        }
        return i + 1;
    }
}
