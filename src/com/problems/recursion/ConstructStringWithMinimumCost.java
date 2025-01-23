package com.problems.recursion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/construct-string-with-minimum-cost
 *
 * Solution link :
 *
 */
public class ConstructStringWithMinimumCost {

    // todo solve the problem later
    public static void main(String[] args) {
        type1();
    }

    // this approach is right, but getting TLE for this approach
    // here, we are creating one list of same length words and then store it length wise map
    //also we will sort the lists.
    // then we will use an index to track and match the current string and target
    private static void type1() {
        String target = "rrhrzfmk";
        String[] words = {"r", "rz", "k", "rhrzfmk"};
        int[] costs = {11, 3, 7, 8};
        int ans = minimumCost(target, words, costs);
        System.out.println(ans);
    }

    public static int minimumCost(String target, String[] words, int[] costs) {
        char[] arr = target.toCharArray();
        boolean[] has = new boolean[26];
        int n = words.length;

        int n1 = arr.length;
        char[] bucket = new char[n1];
        Pairs[] pairs = new Pairs[n1 + 1];

        for (int i = 0; i < n; i++) {
            char[] word = words[i].toCharArray();
            for (char ch : word) has[ch - 'a'] = true;
            if (pairs[word.length] == null) pairs[word.length] = new Pairs();
            pairs[word.length].add(new Pair(word, costs[i]));
        }
        for (char ch : arr) if (!has[ch - 'a']) return -1;

        Comparator<Pair> c = Comparator.comparingInt(p -> p.word[0]);
        for (Pairs p : pairs) if (p != null && p.list != null) p.list.sort(c);
        return traverse(0, bucket, arr, pairs);
    }

    private static int traverse(int curr, char[] bucket, char[] target, Pairs[] pairs) {
        int n = target.length;
        if (curr == n) {
            for (int i = 0; i < n; i++) if (target[i] != bucket[i]) return -1;
            return 0;
        }
        int rem = n - curr;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= rem; i++) {
            if (pairs[i] == null || pairs[i].list == null) continue;
            for (Pair p : pairs[i].list) {
                if (target[curr] > p.word[0]) continue;
                if (target[curr] < p.word[0]) break;
                for (int pos = 0; pos < i; pos++) bucket[pos + curr] = p.word[pos];
                int remCost = traverse(curr + i, bucket, target, pairs);
                if (remCost >= 0) min = Math.min(min, p.cost + remCost);
            }
        }
        return min != Integer.MAX_VALUE ? min : -1;
    }

    static class Pairs {
        List<Pair> list;

        void add(Pair pair) {
            if (list == null) list = new ArrayList<>();
            list.add(pair);
        }
    }

    static class Pair {
        char[] word;
        int cost;

        Pair(char[] word, int cost) {
            this.word = word;
            this.cost = cost;
        }
    }
}
