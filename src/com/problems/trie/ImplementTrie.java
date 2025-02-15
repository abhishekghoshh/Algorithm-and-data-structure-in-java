package com.problems.trie;

import java.util.ArrayList;
import java.util.List;
/*
 * Problem link :
 * https://leetcode.com/problems/implement-trie-prefix-tree/description/
 * https://neetcode.io/problems/implement-prefix-tree
 * https://www.naukri.com/code360/problems/implement-trie_631356
 * https://www.naukri.com/code360/problems/trie-implementation_1062581
 *
 * Solution link :
 * https://www.youtube.com/watch?v=dBGUmUQhjaM&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp
 * https://takeuforward.org/data-structure/implement-trie-1/
 *
 * https://www.youtube.com/watch?v=oobqoCJlHA0
 * https://neetcode.io/solutions/implement-trie-prefix-tree
 */


public class ImplementTrie {

    /*
     * A trie (pronounced as "try")
     * or prefix tree is a tree data structure
     * used to efficiently store and retrieve keys in a dataset of strings.
     * There are various applications of this data structure, such as autocomplete and spellchecker.
     *
     * It's a tree like structure where each node represents a single character of a string.
     * The root node represents an empty string.
     *
     * In the real world, we can create the Trie implementation with proper method names and implementations.
     * However, here or in a normal coding exam we can skip it,
     * in a coding interview it's worth mentioning everything with a proper structure and methods
     * */
    public static void main(String[] args) {
        // all the approaches at core are same, just the way of implementation is different
        type1();
        type2();
        type3();
    }

    private static void type3() {
        Trie3 trie = new Trie3();
        trie.insert("abhishek");
        trie.insert("abhishikta");
        trie.insert("nasim");
        System.out.println(trie.search("abhishek"));
        System.out.println(trie.startsWith("abhi"));
        System.out.println(trie.search("buddhu"));
    }

    static class Trie3 {
        Node root;

        public Trie3() {
            root = new Node();
        }

        public void insert(String word) {
            root.insert(word.toCharArray(), 0);
        }

        public boolean search(String word) {
            return root.search(word.toCharArray(), 0);
        }

        public boolean startsWith(String prefix) {
            return root.startsWith(prefix.toCharArray(), 0);
        }

        static class Node {
            Node[] nodes;
            boolean eow;

            Node() {
                nodes = new Node[26];
            }

            public void insert(char[] word, int i) {
                int n = word.length;
                int pos = word[i] - 'a';
                // if there is no child, then we will create a new node on that position
                if (nodes[pos] == null) nodes[pos] = new Node();
                // if it is the last char, then we will mark it as the end of the word and return
                if (i == n - 1) {
                    nodes[pos].eow = true;
                    return;
                }
                nodes[pos].insert(word, i + 1);
            }

            public boolean search(char[] word, int i) {
                int n = word.length;
                if (i >= n) return false;
                int pos = word[i] - 'a';
                Node node = nodes[pos];
                // if there is no child, then the current character is not present, so we will return false
                if (node == null) return false;
                // if it is the last char, and it is the end of the word, then return true
                if (i == n - 1) return node.eow;
                // else recursively go to the next char
                return node.search(word, i + 1);
            }

            public boolean startsWith(char[] prefix, int idx) {
                int n = prefix.length;
                if (idx >= n) return false;
                int pos = prefix[idx] - 'a';
                Node node = nodes[pos];
                // if there is no child, then the current character is not present, so we will return false
                if (node == null) return false;
                // if it is the last of the prefix, then return true
                if (idx == n - 1) return true;
                // else recursively go to the next char
                return node.startsWith(prefix, idx + 1);
            }
        }
    }

    private static void type2() {
        Trie2 trie = new Trie2();
        trie.insert("Abhishek");
        trie.insert("Abhishikta");
        trie.insert("Nasim");
        System.out.println(trie.search("Abhishek"));
        System.out.println(trie.startsWith("Abhi"));
        System.out.println(trie.search("Buddhu"));
    }

    static class Trie2 {
        Node root;

        public Trie2() {
            root = new Node();
        }

        public void insert(String word) {
            root.insert(word.toLowerCase(), 0);
        }

        public boolean search(String word) {
            return root.search(word.toLowerCase(), 0);
        }

        public boolean startsWith(String prefix) {
            return root.startsWith(prefix.toLowerCase(), 0);
        }

        static class Node {
            Node[] nodes;
            boolean eow;

            Node() {
                nodes = new Node[26];
            }

            public void insert(String word, int idx) {
                if (idx >= word.length()) return;
                int pos = word.charAt(idx) - 'a';
                if (nodes[pos] == null)
                    nodes[pos] = new Node();
                if (idx == word.length() - 1)
                    nodes[pos].eow = true;
                nodes[pos].insert(word, idx + 1);
            }

            public boolean search(String word, int idx) {
                if (idx >= word.length()) return false;
                Node node = nodes[word.charAt(idx) - 'a'];
                if (node == null) return false;
                if (idx == word.length() - 1 && node.eow) return true;
                return node.search(word, idx + 1);
            }

            public boolean startsWith(String prefix, int idx) {
                if (idx >= prefix.length()) return false;
                Node node = nodes[prefix.charAt(idx) - 'a'];
                if (node == null) return false;
                if (idx == prefix.length() - 1) return true;
                return node.startsWith(prefix, idx + 1);
            }
        }
    }

    private static void type1() {
        Trie1 trie = new Trie1();
        trie.insert("Abhishek");
        trie.insert("Abhishikta");
        trie.insert("Nasim");
        System.out.println(trie.search("Abhishek"));
        System.out.println(trie.startsWith("Abhi"));
        System.out.println(trie.search("Buddhu"));
        System.out.println(trie.prefixSearch("Ab"));
    }

    static class Trie1 {
        static public class Node {
            private final Node[] nodes;
            private boolean isEnd;

            Node() {
                this.nodes = new Node[ALPHABET_SIZE];
            }

            public boolean isEnd() {
                return this.isEnd;
            }

            public void setEnd() {
                this.isEnd = true;
            }

            public Node get(char ch) {
                return nodes[index(ch)];
            }

            public boolean isNull(char ch) {
                return null == nodes[index(ch)];
            }

            public Node set(char ch) {
                Node node = new Node();
                nodes[index(ch)] = node;
                return node;
            }

            private int index(char ch) {
                return ch - 'a';
            }
        }
        private static final int ALPHABET_SIZE = 26;
        private final Node head;

        public Trie1() {
            head = new Node();
        }

        public void insert(String word) {
            if (null == word || word.isBlank()) return;
            Node node = head;
            char[] arr = word.toLowerCase().toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch))
                    node = node.set(ch);
                else
                    node = node.get(ch);
            }
            node.setEnd();
        }

        public boolean search(String word) {
            if (null == word || word.isBlank()) return false;
            Node node = head;
            char[] arr = word.toLowerCase().toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) return false;
                node = node.get(ch);
            }
            return node.isEnd();
        }

        public boolean startsWith(String prefix) {
            if (null == prefix || prefix.isBlank()) return false;
            Node node = head;
            char[] arr = prefix.toLowerCase().toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) return false;
                node = node.get(ch);
            }
            return true;
        }

        public List<String> prefixSearch(String prefix) {
            List<String> words = new ArrayList<>();
            if (null == prefix || prefix.isBlank()) return words;
            Node node = this.head;
            char[] arr = prefix.toLowerCase().toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) return words;
                node = node.get(ch);
            }
            return prefixSearch(words, node, new StringBuilder(new String(arr)));
        }

        private List<String> prefixSearch(List<String> words, Node node, StringBuilder word) {
            if (node.isEnd()) {
                words.add(word.toString());
            }
            for (char ch = 'a'; ch <= 'z'; ch++) {
                if (!node.isNull(ch)) {
                    word.append(ch);
                    prefixSearch(words, node.get(ch), word);
                    word.deleteCharAt(word.length() - 1);
                }
            }
            return words;
        }

    }
}
