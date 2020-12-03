package ua.edu.ucu.tries;

import lombok.Getter;
import lombok.Setter;
import ua.edu.ucu.collections.Queue;

import java.util.LinkedList;

public class RWayTrie implements Trie {
    private Node root;
    private int size;

    private static class Node {
        private static final int NOT_LAST = -1;
        private static final int CHILDREN = 26;
        private static final int STARTING_CODE = 97;

        @Setter @Getter
        private char data;

        private Node[] children;

        @Setter @Getter
        private int value;

        @Getter @Setter
        private Node parent;

        public Node() {
            children = new Node[CHILDREN];
            value = NOT_LAST;
        }

        public Node(char letter) {
            data = letter;
            children = new Node[CHILDREN];
            value = NOT_LAST;
        }

        public void checkIndex(int index) {
            if (index < 0 || index >= CHILDREN ) {
                throw new IndexOutOfBoundsException();
            }
        }

        public Node setChild(char letter) {
            int index = letter - STARTING_CODE;
            checkIndex(index);
            children[index] = new Node(letter);
            return children[index];
        }

        public Node getChild(char letter) {
            int index = letter - STARTING_CODE;
            return getChild(index);
        }

        public Node getChild(int index) {
            checkIndex(index);
            return children[index];
        }

        public boolean notLast() {
            return value == NOT_LAST;
        }
    }

    public RWayTrie() {
        root = new Node();
        size = 0;

    }

    public void add(Tuple t) {
        Node current = root;
        for (Character letter: t.term.toCharArray()) {
            if (current.getChild(letter) == null){
                current.setChild(letter);
            }
            current = current.getChild(letter);

        }
        if (current.notLast()) {
            size += 1;
        }
        current.setValue(t.weight);
    }

    public boolean contains(String word) {
        return searchLast(word) != null;

    }

    private Node searchLast(String word) {
        Node current = root;
        for (Character letter: word.toCharArray()) {
            if (current.getChild(letter) == null) {
                return null;
            }
            current = current.getChild(letter);

        }
        return current;
    }


    public boolean delete(String word) {
        Node last = searchLast(word);
        if (last == null) {
            return false;
        }
        last.setValue(Node.NOT_LAST);
        return true;
    }

    public Iterable<String> wordsWithPrefix(String pre) {
        Queue q = new Queue();
        collect(searchLast(pre), pre, q);
        LinkedList<String> strings = new LinkedList();
        while (!q.isEmpty()) {
            strings.add(q.dequeue().toString());
        }
        return strings;
    }
    private void collect(Node current, String pre, Queue q)  {
        if (current == null) return;
        if (!current.notLast()) q.enqueue(pre);
        for (int i = 0; i < Node.CHILDREN; i++) {
            collect(current.getChild(i),
                    pre + (char) (i + Node.STARTING_CODE),
                    q);
        }
    }

    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    public int size() {
        return size;
    }

}
