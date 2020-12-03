package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.LinkedList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {
    public static final int MIN_LENGTH = 2;

    private Trie trie;

    public PrefixMatches(Trie wordsTrie) {
        trie = wordsTrie;
    }

    public int load(String... strings) {
        int words = 0;
        for (String str: strings) {
            for (String word: str.split(" ")) {
                if (word.length() > MIN_LENGTH) {
                    trie.add(new Tuple(word.toLowerCase(), word.length()));
                    words += 1;
                }

            }
        }
        return words;
    }

    public boolean contains(String word) {
        return trie.contains(word.toLowerCase());
    }

    public boolean delete(String word) {
        return trie.delete(word.toLowerCase());
    }

    public Iterable<String> wordsWithPrefix(String pre) {
        String pref = pre.toLowerCase();
        if (pref.length() < MIN_LENGTH) {
            throw new IllegalArgumentException();
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        LinkedList<String> words = new LinkedList<>();
        for (String word: wordsWithPrefix(pref)) {
            if (word.length() < (pref.length() + k)) {
                words.add(word);
            }
        }
        return words;

    }

    public int size() {
        return trie.size();
    }

}
