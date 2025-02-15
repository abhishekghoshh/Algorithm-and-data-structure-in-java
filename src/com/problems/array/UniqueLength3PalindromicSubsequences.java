package com.problems.array;
/*
 * Problem link:
 * https://leetcode.com/problems/unique-length-3-palindromic-subsequences/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=3THUt0vAFLU
 *
 * https://neetcode.io/solutions/unique-length-3-palindromic-subsequences
 * */

// Tags: Arrays, hashing, prefix sum
public class UniqueLength3PalindromicSubsequences {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // it is also optimized
    // it uses the min and the max index of any character
    // then for every character we will check how many distinct characters are present in the min and max
    private static void type4() {
        String s = "bbcbaba";
        int ans = countPalindromicSubsequence4(s);
        System.out.println(ans);
    }

    public static int countPalindromicSubsequence4(String s) {
        int n = s.length();
        int[] max = new int[26];
        int[] min = new int[26];
        // setting the default value
        for (int i = 0; i < 26; i++) {
            max[i] = -1;
            min[i] = n;
        }
        // finding the min max array
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            min[j] = Math.min(min[j], i);
            max[j] = Math.max(max[j], i);
        }
        int c = 0;
        for (int i = 0; i < 26; i++) {
            int maxI = max[i], minI = min[i];
            // if the value is -1, then that is not present,
            // or the max and min value are same then the character is present only for one time
            if (maxI == -1 || maxI == minI) continue;
            // now we will check for all the character if they are present
            // in between of the first and the last occurrence of the current character or not
            for (int j = 0; j < 26; j++) {
                // if the pivot character's min is greater than the max of the character
                // or the max is lesser than the min of the character, then we could skip
                if (max[j] < minI || maxI < min[j]) continue;
                // checking the first index of pivot character from the (minI + 1)
                int pivotI = s.indexOf('a' + j, minI + 1);
                // if pivot characters first index lesser than the maxI then the character is present
                if (pivotI < maxI) c++;
            }
        }
        return c;
    }

    // todo explain this in the interview
    // same as type 2, but here we have used array as set everywhere
    // optimized approach using hashing and prefix sum
    // we will maintain first and last occurrence of a character
    // if we see there is a character which has the value in first array,
    // that means we have already seen it
    // so there might be some characters from the first[i] to the current index
    // which we can find from the last array, as it holds the latest indices of the characters
    // so from a to z we will check the last value of each character if the value/index falls
    // in between of first[ch] and curr index that mean we could use that letter
    // we also have to maintain a set to remove duplicates
    private static void type3() {
        String s = "bbcbaba";
        int ans = countPalindromicSubsequence3(s);
        System.out.println(ans);
    }

    public static int countPalindromicSubsequence3(String s) {
        int N = (31 * 31 * 26) + (31 * 26) + 26;
        boolean[] set = new boolean[N + 1];
        char[] arr = s.toCharArray();
        int n = arr.length;
        int[] first = new int[26];
        int[] last = new int[26];
        int count = 0;
        for (int i = 0; i < n; i++) {
            int pos = arr[i] - 'a';
            // the first time we are seeing the character
            // setting first and last as i+1
            if (first[pos] == 0) {
                first[pos] = last[pos] = i + 1;
                continue;
            }
            // we have already seen the character firstI is the first occurrence
            // lastI is the current index
            int firstI = first[pos], lastI = i + 1;
            for (int mid = 0; mid < 26; mid++) {
                int midI = last[mid];
                if (firstI < midI && midI < lastI) {
                    // string was char[pos] char[mid] char[pos], we will create the hash like it
                    int hash = (31 * 31 * pos) + (31 * mid) + pos;
                    // if string hash is already present in the set then we do not need to increment
                    if (!set[hash]) count++;
                    // adding string to the
                    set[hash] = true;
                }
            }
            last[pos] = lastI;
        }
        return count;
    }


    private static void type2() {
        String s = "bbcbaba";
        int ans = countPalindromicSubsequence2(s);
        System.out.println(ans);
    }

    private static int countPalindromicSubsequence2(String s) {
        return 0;
    }

    // brute force approach
    private static void type1() {
        String s = "bbcbaba";
        int ans = countPalindromicSubsequence1(s);
        System.out.println(ans);
    }

    private static int countPalindromicSubsequence1(String s) {
        return 0;
    }
}
