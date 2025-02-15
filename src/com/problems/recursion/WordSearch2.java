package com.problems.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/word-search-ii/description/
 * https://neetcode.io/problems/search-for-word-ii
 * https://www.naukri.com/code360/problems/word-search_630520
 *
 * Solution link
 * https://www.youtube.com/watch?v=asbcE9mZz_U
 * https://neetcode.io/solutions/word-search-ii
 *
 * https://takeuforward.org/data-structure/word-search-ii/
 * https://www.naukri.com/code360/problem-details/word-search_630520
 * */

// Tags : Recursion, Trie
public class WordSearch2 {

    // check it later again
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // TODO this is the most efficient approach
    //  we have gradually built the approach
    //  WordSearch(type1) -> WordSearch(type2) -> WordSearch2(type1) -> WordSearch2(type2) -> WordSearch3(type3)
    // check the comments of the type2
    // here we will also use word counter along with isEnd flag
    // to mark how many words are there with this path
    private static void type3() {
        char[][] board = {
                {'o', 'a', 'b', 'n'},
                {'o', 't', 'a', 'e'},
                {'a', 'h', 'k', 'r'},
                {'a', 'f', 'l', 'v'}
        };
        String[] words = {"oa", "oaa"};
        List<String> answer = findWords3(board, words);
        System.out.println(answer);
    }

    static class Node2 {
        int count = 0;
        boolean isEnd = false;
        Node2[] nodes = new Node2[26];
    }

    private static List<String> findWords3(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        Node2 trie = new Node2();
        // it will build the trie
        for (String word : words) {
            Node2 node = trie;
            for (char ch : word.toCharArray()) {
                int pos = ch - 'a';
                if (node.nodes[pos] == null)
                    node.nodes[pos] = new Node2();
                node = node.nodes[pos];
                // we will also increment the word counter
                node.count++;
            }
            // we will also mark that the word ends here
            node.isEnd = true;
        }

        int m = board.length, n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pos = board[i][j] - 'a';
                // not null means there might be a word in the trie starting with the cell character
                // additionally we will also check if the word count is greater than 0 or not
                if (trie.nodes[pos] != null && trie.nodes[pos].count > 0)
                    findWord3(board, i, j, trie, new StringBuilder(), answer);
            }
        }
        return answer;
    }

    private static boolean findWord3(char[][] board, int i, int j,
                                     Node2 node, StringBuilder word, List<String> answer) {
        // if the cell out of boundary or the cell is already visited or character is not found in the trie
        // additionally, we will check the word count is greater than 0 or not
        if (isOutOfBound(i, j, board)
                || board[i][j] == '-'
                || node.nodes[board[i][j] - 'a'] == null
                || node.nodes[board[i][j] - 'a'].count <= 0)
            return false;
        char ch = board[i][j];
        board[i][j] = '-'; // we will change the cell value to - to mark it as visited
        // we will maintain one flag, if on this node or any future node there is a word,
        // then we will decrement the count variable
        boolean hasWordEnding = false;

        // we will append the letter to the string
        word.append(ch);
        // we will go to the node, and we know it is not null
        node = node.nodes[ch - 'a'];

        // if isEnd flag is true then there is one word ending here obviously,
        // so we will add the word into the answer, but we will not stop here.
        // here we will additionally do some other things
        // we will set the flag to true, decrement the count and also unset the variable.
        // else, the word might get added in some other word like
        // if the current word is abc, and there is another word abcd
        if (node.isEnd) {
            answer.add(word.toString());
            hasWordEnding = true;
            node.count--;
            node.isEnd = false;
        }
        // we will traverse all four sides, and if there is any word found, then
        // we will set the flag value to true, and also we will decrement the count value
        if (findWord3(board, i + 1, j, node, word, answer)) {
            hasWordEnding = true;
            node.count--;
        }
        if (findWord3(board, i - 1, j, node, word, answer)) {
            hasWordEnding = true;
            node.count--;
        }
        if (findWord3(board, i, j + 1, node, word, answer)) {
            hasWordEnding = true;
            node.count--;
        }
        if (findWord3(board, i, j - 1, node, word, answer)) {
            hasWordEnding = true;
            node.count--;
        }
        // after all the computation, we will delete the character
        word.deleteCharAt(word.length() - 1);
        // also we will mark the cell unvisited
        board[i][j] = ch;
        // now we will return the flag
        return hasWordEnding;
    }

    // first see the previous type comments,
    // we will first save the words into the trie
    // then we will use the same trie and traverse the board rather traversing an individual word
    // there is one catch here.
    // if there is duplicate adjacent cells like in one row, there is 'o' ,'a'
    // and in another row there is the same, then our code will detect both.
    // so we can do one thing, we can use a counter to mark there is a word
    // we can use one counter in trie node.
    // while adding the words, we will add the counter, and while traversing the board, we will decrease the counter
    private static void type2() {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> answer = findWords2(board, words);
        System.out.println(answer);
    }

    public static List<String> findWords2(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        TrieNode trieNode = new TrieNode();
        // it will build the trie with all the words given in it or not
        for (String word : words) {
            TrieNode node = trieNode;
            for (char ch : word.toCharArray()) {
                int pos = ch - 'a';
                if (node.nodes[pos] == null)
                    node.nodes[pos] = new TrieNode();
                node = node.nodes[pos];
            }
            // we will also mark that the word ends here
            node.isEnd = true;
        }

        int m = board.length, n = board[0].length;
        // now for every cell, we will search that if that character is there in the root node or not
        // if it is present, then we will start traversing
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int pos = board[i][j] - 'a';
                // not null means there might be a word in the trie starting with the cell character
                if (trieNode.nodes[pos] != null)
                    findWord2(board, i, j, trieNode, new StringBuilder(), answer);
            }
        }
        // the answer might have some duplicate results.
        // as there might be a set of adjacent cells having the save value.
        // like if in the 1st row, there is [o a]
        // and in the third row there is [a o]
        // trie will start from both o then add the word "ao" into the answer.
        // that is why we will use a hashset to add remove duplicate words
        return new ArrayList<>(new HashSet<>(answer));
    }


    private static void findWord2(char[][] board, int i, int j,
                                  TrieNode node, StringBuilder word, List<String> answer) {
        // if the cell out of boundary or the cell is already visited or character is not found in the trie
        if (isOutOfBound(i, j, board)
                || board[i][j] == '-'
                || node.nodes[board[i][j] - 'a'] == null
        ) return;
        // we will change the cell value to an arbitrary character to mark it as visited
        char ch = board[i][j];
        board[i][j] = '-';

        // we will append the letter to the string
        word.append(ch);
        // we will go to the node, and we know it is not null
        node = node.nodes[ch - 'a'];

        // if isEnd flag is true then there is one word ending here obviously,
        // so we will add the word into the answer, but we will not stop here.
        // we will continue traversing as there might be some words like "aab" and "aabb"
        if (node.isEnd) answer.add(word.toString());

        // now will traverse all four sides
        findWord2(board, i + 1, j, node, word, answer);
        findWord2(board, i - 1, j, node, word, answer);
        findWord2(board, i, j + 1, node, word, answer);
        findWord2(board, i, j - 1, node, word, answer);

        // after all the computation, we will delete the character
        word.deleteCharAt(word.length() - 1);

        // also we will mark the cell unvisited
        board[i][j] = ch;
    }


    static class TrieNode {
        boolean isEnd = false;
        TrieNode[] nodes = new TrieNode[26];
    }

    // it is taken from word search 1 type 2.
    // this solution is very efficient, but this will not work for this problem.
    // if there are some words like aaaaax, aaaaaab
    // we are not using the fact that they have the same prefix,
    // we can easily save some computation using trie
    private static void type1() {
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> answer = findWords1(board, words);
        System.out.println(answer);
    }

    public static List<String> findWords1(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        int m = board.length, n = board[0].length;
        for (String word : words) {
            // for every word, we will start searching into the board
            // that it is there or not
            if (findWord1(board, word)) answer.add(word);
        }
        return answer;
    }


    private static boolean findWord1(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // we will start the traversal for every cell if its value is the word's first character
                // if the traversal returns true, then we will also return true
                if (board[i][j] == word.charAt(0)
                        && hasWord1(board, i, j, 0, word.toCharArray()))
                    return true;
            }
        }
        return false;
    }


    private static boolean hasWord1(char[][] board, int i, int j, int idx, char[] arr) {
        // if we go to nth index, that means we have already found 0..n-1 indexes
        // we can return true from here
        if (idx == arr.length) return true;
        // if the coordinate is out of bound or the cell value is not arr[i] or the cell is already visited,
        // then we will return false
        if (isOutOfBound(i, j, board)
                || board[i][j] != arr[idx]
                || board[i][j] == '-')
            return false;

        // we will change the cell value to an arbitrary character to mark it as visited
        char ch = board[i][j];
        board[i][j] = '-';

        // we will explore all directions
        if (hasWord1(board, i + 1, j, idx + 1, arr)
                || hasWord1(board, i - 1, j, idx + 1, arr)
                || hasWord1(board, i, j + 1, idx + 1, arr)
                || hasWord1(board, i, j - 1, idx + 1, arr)) {
            // either true or false, we will reset the previous cells
            board[i][j] = ch;
            return true;
        }
        // at last, we will again set the cell value to its original char
        board[i][j] = ch;
        return false;
    }

    private static boolean isOutOfBound(int i, int j, char[][] board) {
        return i < 0 || i >= board.length || j < 0 || j >= board[0].length;
    }
}
