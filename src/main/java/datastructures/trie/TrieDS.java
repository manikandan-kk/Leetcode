package datastructures.trie;

import java.util.*;

class TrieDictionary {

    static class Node {
        private char x;
        private Map<Character, Node> childrens;
        private boolean word;
        public Node(char x) {
            this.x = x;
            childrens = new HashMap<>();
        }


    }
    private Node root;

    public TrieDictionary() {
        this.root = new Node(' ');
    }

    public void insert(String word) {
        Node walker = this.root;
        for (int i = 0; i < word.length(); i++) {
            char x = word.charAt(i);
            Map<Character, Node> childrens = walker.childrens;
            Node childNode;
            if (!childrens.containsKey(x)) {
                childNode = new Node(x);
                childrens.put(x, childNode);
            } else {
                childNode = childrens.get(x);
            }
            walker = childNode;
        }
        if (!walker.word)
            walker.word = true;
    }

    public boolean isWord(String word) {
        Node walker = this.root;
        for (int i = 0; i < word.length(); i++) {
            char x = word.charAt(i);
            Map<Character, Node> childrens = walker.childrens;
            Node childNode;
            if (!childrens.containsKey(x)) {
                return false;
            } else {
                childNode = childrens.get(x);
            }
            walker = childNode;
        }
        return walker.word;
    }

    public boolean startsWith(String prefix) {
        Node walker = this.root;
        for (int i = 0; i < prefix.length(); i++) {
            char x = prefix.charAt(i);
            Map<Character, Node> childrens = walker.childrens;
            if (!childrens.containsKey(x)) {
                return false;
            }
            walker = childrens.get(x);
        }
        return true;
    }

    public String shortestPrefix(String word) {
        Node walker = this.root;
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            char x = word.charAt(i);
            Map<Character, Node> childrens = walker.childrens;
            if (!childrens.containsKey(x)) {
                return word;
            }
            walker = childrens.get(x);
            prefix.append(x);
            if (walker.word) {
                return prefix.toString();
            }
        }
        return word;
    }
}


public class TrieDS {
    public static void main(String[] args) {
        TrieDictionary trieDictionary = new TrieDictionary();
        trieDictionary.insert("hello");
        trieDictionary.insert("tellme");
        System.out.println("Is Word: Hello ? " + trieDictionary.isWord("hello"));
        System.out.println("Is Word: tellme ?" + trieDictionary.isWord("tellme"));
        System.out.println("Is Word: tellmee ?" + trieDictionary.isWord("tellmee"));
        System.out.println("Is Word: tellm ?" + trieDictionary.isWord("tellm"));
        System.out.println("Is Word: tom ?" + trieDictionary.isWord("tom"));
    }

    static class CustomComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 > o1 ? 1 : -1;
        }
    }
}