package com.problems.slidingwindow;

/*
 * Problem link :
 *
 * Solution link :
 *
 */
public class CountSubstringsWithKFrequencyCharacters1 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // sliding window approach
    private static void type2() {
        String s = "abacb";
        int k = 2;

    }

    public static int numberOfSubstrings2(String s, int k) {
        int count = 0;
        char[] arr = s.toCharArray();
        int n = arr.length, l = 0, r = 0;
        int[] freq = new int[26];
        while (r < n) {
            int pos = arr[r++] - 'a';
            freq[pos]++;
            // we will continue till freq of any character is less than k
            if (freq[pos] < k) continue;
            // count will be increased by (n - r + 1) not 1 because if the current
            // string we are considering is <abc> and there is x letters more in the string in right
            // so starting from <abc> we can make len(<abc>) + x number of strings
            count += (n - r + 1);
            // once it is k we will try to shift the left side of the window
            // and try to maintain the freq of the character
            while (l <= r) {
                if (l == n) break;
                int delPos = arr[l++] - 'a';
                freq[delPos]--;
                if (freq[pos] < k) break;
                // same reason as previous
                count += (n - r + 1);
            }
        }
        return count;
    }

    // brute force approach
    private static void type1() {
    }
}
