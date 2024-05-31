public class Patricia {
    private Node root;

    private int getLetterPosition(char letter) {
        return Character.toLowerCase(letter) - 'a';
    }

    public void insert(String word) {
        Node current, next, nextWord = root, node, newWord;
        int pos, dif = 0;
        boolean flag = true;
        if (root == null) { //1º caso: árvore vazia
            root = new Node();
            pos = getLetterPosition(word.charAt(0));
            root.setLetters(word.charAt(0), pos);
            root.setIndex(1);
            root.setChildren(new Node(word), pos);
        } else {
            current = root;
            next = current.getChildren(getLetterPosition(word.charAt(current.getIndex() - 1)));
            while (flag && next != null && next.getIndex() > 0 && word.length() > current.getIndex()) {
                if (next.getIndex() > current.getIndex() + 1) {
                    nextWord = getNextWord(next);
                    dif = differentLetterPosition(nextWord, word);
                    if (dif < next.getIndex() || dif - 1 == word.length())
                        flag = false;
                }
                if (flag) {
                    current = next;
                    next = current.getChildren(getLetterPosition(word.charAt(current.getIndex() - 1)));
                }
            }
            if (next == null) { //2º caso
                node = new Node(word);
                current.setChildren(node, getLetterPosition(word.charAt(current.getIndex() - 1)));
                current.setLetters(word.charAt(current.getIndex() - 1), getLetterPosition(word.charAt(current.getIndex() - 1)));
            } else {
                if (current.getIndex() > word.length()) //(5) caso, chegou no nó que a palavra pertence
                    current.setWord(word);
                else {
                    nextWord = getNextWord(next);
                    dif = differentLetterPosition(nextWord, word);
                    if (nextWord.getIndex() < 0 && dif > nextWord.getWord().length()) { //3º caso, tem prefixo
                        newWord = new Node(word);
                        nextWord.setIndex(dif);
                        nextWord.setChildren(newWord, getLetterPosition(word.charAt(dif - 1)));
                        nextWord.setLetters(word.charAt(dif - 1), getLetterPosition(word.charAt(dif - 1)));
                    } else { //4º caso, cria nó intermediario
                        if (dif == next.getIndex() && dif > word.length()) {
                            next.setWord(word);
                        } else {
                            node = new Node();
                            node.setIndex(dif);
                            node.setChildren(next, getLetterPosition(nextWord.getWord().charAt(dif - 1)));
                            node.setLetters(nextWord.getWord().charAt(dif - 1), getLetterPosition(nextWord.getWord().charAt(dif - 1)));
                            newWord = new Node(word);
                            if (dif - 1 >= word.length()) {
                                node.setWord(word);
                            } else {
                                node.setChildren(newWord, getLetterPosition(word.charAt(dif - 1)));
                                node.setLetters(word.charAt(dif - 1), getLetterPosition(word.charAt(dif - 1)));
                            }
                            current.setChildren(node, getLetterPosition(nextWord.getWord().charAt(current.getIndex() - 1)));
                        }
                    }
                }
            }
        }
    }

    private Node getNextWord(Node node) {
        Node current = node;
        Node nextWord = null;
        nextWord = current.getImmediateWord();
        while (nextWord == null && current.getIndex() > 0) {
            current = current.getChildren(current.firstChildPosition());
            nextWord = current.getImmediateWord();
        }
        return nextWord;
    }

    private int differentLetterPosition(Node nextWord, String word) {
        int i = 0;
        while (i < nextWord.getWord().length() && i < word.length() && nextWord.getWord().charAt(i) == word.charAt(i)) {
            i++;
        }
        return i + 1;
    }

    public void showWords() {
        Stack stack = new Stack();
        Node current = root;
        stack.push(current);
        while (!stack.isEmpty()) {
            current = stack.pop();
            if (!current.getWord().isEmpty()) {
                System.out.println(current.getWord());
            }
            for (int i = 0; i < 26; i++) {
                if (current.getChildren(i) != null) {
                    stack.push(current.getChildren(i));
                }
            }
        }
    }

    public Node getWord(String word) {
        Node current = root;
        if (current!=null){
            while(current!=null && !current.getWord().equals(word)){
                current = current.getChildren(getLetterPosition(word.charAt(current.getIndex()-1)));
            }
        }
        return current;
    }

    public void showNodesByLevel() {
        Queue queue = new Queue();
        Node current = root;
        queue.enqueue(current);
        while (!queue.isEmpty()) {
            current = queue.dequeue();
            for (int i = 0; i < 26; i++) {
                if(current.getLetters(i)!=('\0')){
                    System.out.print(current.getLetters(i)+" ");
                }
            }
            if (!current.getWord().isEmpty()) {
                System.out.println(current.getWord());
            }
            else{
                System.out.println();
            }
            for (int i = 0; i < 26; i++) {
                if (current.getChildren(i) != null) {
                    queue.enqueue(current.getChildren(i));
                }
            }
        }
    }
}
