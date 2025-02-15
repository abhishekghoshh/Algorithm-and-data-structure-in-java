package com.problems.trie;
/*
 * Problem link :
 * https://www.naukri.com/code360/problems/implement-trie_1387095
 * https://www.naukri.com/code360/problems/trie-delete-operation_1062663
 *
 * Solution link :
 * https://www.youtube.com/watch?v=K5pcpkEMCN0&list=PLgUwDviBIf0pcIDCZnxhv0LkHf5KzG9zp&index=2
 *
 * https://takeuforward.org/data-structure/implement-trie-ii/
 */

public class ImplementTrie2 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        Trie2 trie = new Trie2();
        trie.insert("samsung");
        trie.insert("samsung");
        System.out.println(trie.countWordsEqualTo("samsung"));
        System.out.println(trie.countWordsStartingWith("samsung"));
        trie.erase("samsung");
        System.out.println(trie.countWordsEqualTo("samsung"));
        System.out.println(trie.countWordsStartingWith("sams"));
    }

    public static class Trie2 {
        private static final int ALPHABET_SIZE = 26;
        private final Node head;

        public Trie2() {
            head = new Node();
        }

        public void insert(String word) {
            Node node = head;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                int pos = ch - 'a';
                if (node.nodes[pos] == null)
                    node.nodes[pos] = new Node();
                node = node.nodes[pos];
                node.countPrefix++;
            }
            node.endsWith++;
        }

        public int countWordsEqualTo(String word) {
            Node node = head;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                int pos = ch - 'a';
                if (node.nodes[pos] == null) return 0;
                node = node.nodes[pos];
            }
            return node.endsWith;
        }

        public int countWordsStartingWith(String word) {
            Node node = head;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                int pos = ch - 'a';
                if (node.nodes[pos] == null) return 0;
                node = node.nodes[pos];
            }
            return node.countPrefix;
        }

        public void erase(String word) {
            // we will only erase a word if it is present
            erase(head, word.toCharArray(), 0);
        }

        private boolean erase(Node node, char[] arr, int i) {
            // at the last if there is any word which is ending
            // then only we will delete endsWith variable and return true
            if (i == arr.length) {
                if (node.endsWith > 0) {
                    node.endsWith--;
                    return true;
                } else
                    return false;
            }
            int pos = arr[i] - 'a';
            // as we are not deleting the links, so we have to check if the count prefix is
            // greater than 0 or not
            if (node.nodes[pos] != null && node.nodes[pos].countPrefix > 0 && erase(node.nodes[pos], arr, i + 1)) {
                // erase will only return true if the there is the word found
                // if the word is found then we will decrement count prefix and return true
                node.nodes[pos].countPrefix--;
                return true;
            }
            return false;
        }

        public static class Node {
            public final Node[] nodes = new Node[ALPHABET_SIZE];
            public int countPrefix = 0;
            public int endsWith = 0;
        }
    }

    private static void type1() {
        Trie1 trie = new Trie1();
        trie.insert("samsung");
        trie.insert("samsung");
        System.out.println(trie.countWordsEqualTo("samsung"));
        System.out.println(trie.countWordsStartingWith("samsung"));
        trie.erase("samsung");
        System.out.println(trie.countWordsEqualTo("samsung"));
        System.out.println(trie.countWordsStartingWith("sams"));
    }

    public static class Trie1 {
        private static final int ALPHABET_SIZE = 26;
        private final Node head;

        public Trie1() {
            head = new Node();
        }

        public void insert(String word) {
            if (null == word) return;
            Node node = head;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) node = node.set(ch);
                else node = node.get(ch);
                node.incrementCountPrefix();
            }
            node.incrementEndsWith();
        }

        public int countWordsEqualTo(String word) {
            if (null == word || word.isBlank()) return 0;
            Node node = head;
            char[] arr = word.toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) return 0;
                node = node.get(ch);
            }
            return node.getEndsWith();
        }

        public int countWordsStartingWith(String word) {
            if (null == word || word.isBlank())
                return 0;
            Node node = head;
            char[] arr = word.toLowerCase().toCharArray();
            for (char ch : arr) {
                if (node.isNull(ch)) return 0;
                node = node.get(ch);
            }
            return node.countPrefix();
        }

        public void erase(String word) {
            if (null == word || word.isBlank()) return;
            // we will only erase a word if it is present
            erase(head, word.toCharArray(), 0);
        }

        private boolean erase(Node node, char[] arr, int i) {
            if (i == arr.length) {
                // at the last if there is any word which is ending
                // then only we will delete endsWith variable and return true
                if (node.getEndsWith() > 0) {
                    node.decrementEndsWith();
                    return true;
                } else {
                    return false;
                }
            }
            char ch = arr[i];
            // as we are not deleting the links so we have to check if the count prefix is
            // greater than 0 or not
            if (!node.isNull(ch) && node.get(ch).countPrefix() > 0) {
                // erase will only return true if the there is the word found
                if (erase(node.get(ch), arr, i + 1)) {
                    // if the word is found then we will decrement count prefix and return true
                    node.get(ch).decrementCountPrefix();
                    return true;
                }
            }
            return false;
        }

        public static class Node {
            private final Node[] nodes;
            private int countPrefix = 0;
            private int endsWith = 0;

            Node() {
                this.nodes = new Node[ALPHABET_SIZE];
            }

            public void incrementCountPrefix() {
                this.countPrefix++;
            }

            public void decrementCountPrefix() {
                this.countPrefix--;
            }

            public int countPrefix() {
                return countPrefix;
            }

            public void incrementEndsWith() {
                this.endsWith++;
            }

            public void decrementEndsWith() {
                this.endsWith--;
            }

            public int getEndsWith() {
                return endsWith;
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
    }

}
