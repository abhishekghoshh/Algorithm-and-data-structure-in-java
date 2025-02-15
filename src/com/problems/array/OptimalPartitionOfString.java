package com.problems.array;

/*
 * Problem link:
 * https://leetcode.com/problems/optimal-partition-of-string/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=CKZPdiXiQf0
 *
 * https://neetcode.io/solutions/optimal-partition-of-string
 */

// Tags : String, Hashing, Greedy, Sliding window
public class OptimalPartitionOfString {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach using sliding window approach
    // we will use a set to mark the character which we have seen already.
    // once there is a character that is already seen, we will reset the set and increment counter
    // as that is the start of a new string
    private static void type2() {
        String s = "abacaba";
        int count = partitionString2(s);
        System.out.println(count);
    }

    public static int partitionString2(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[] set = new boolean[26];
        int count = 1;
        int prevStart = 0;
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            // the character is already present
            if (set[pos]) {
                count++;
                // resetting the set, unmarking the characters
                for (int j = prevStart; j < i; j++)
                    set[arr[j] - 'a'] = false;
                // 'i' is the start of the new string
                prevStart = i;
            }
            set[pos] = true;
        }
        return count;
    }

    // brute force
    private static void type1() {
    }
}
