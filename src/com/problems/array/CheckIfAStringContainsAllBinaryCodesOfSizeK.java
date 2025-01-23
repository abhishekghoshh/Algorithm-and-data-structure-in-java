package com.problems.array;

/*
 * Problem link :
 * https://leetcode.com/problems/check-if-a-string-contains-all-binary-codes-of-size-k/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=qU32rTy_kOM
 *
 * https://neetcode.io/solutions/check-if-a-string-contains-all-binary-codes-of-size-k
 */

// tags: Arrays, String, Bit manipulation, hashing, sliding window
public class CheckIfAStringContainsAllBinaryCodesOfSizeK {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // first we will calculate the upper range of bits
    // it will be 0 to 2^k-1
    // we use a boolean set for 2^k numbers
    // we will use a sliding window and convert the binary array to the decimal number.
    // at last, we will check if all the index has a true or not in the set
    private static void type2() {
        String s = "00110110";
        int k = 2;
        boolean ans = hasAllCodes2(s, k);
        System.out.println(ans);
    }

    public static boolean hasAllCodes2(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        if (n < k) return false;
        // (1<<k) is the same as 2^k
        int N = 1 << k;
        boolean[] set = new boolean[N];

        // using a sliding window to compute the sum of first k length binary array
        int i = 0;
        int sum = 0;
        while (i < k) {
            int bit = arr[i++] - '0';
            sum = (sum * 2) + bit;
        }
        set[sum] = true;
        // now we will compute for the remaining array
        while (i < n) {
            int leftBit = arr[i - k] - '0';
            int bit = arr[i++] - '0';
            // removing the left most bit and adding the current bit
            sum = (sum * 2) - (leftBit * N) + bit;
            set[sum] = true;
        }
        // at last, we will check if all the index has a true or not in the set
        for (int j = 0; j < N; j++) {
            if (!set[j]) return false;
        }
        return true;
    }

    // brute force
    private static void type1() {
    }
}
