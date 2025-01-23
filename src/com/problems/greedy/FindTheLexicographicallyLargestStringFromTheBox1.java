package com.problems.greedy;
/*
 * Problem link :
 * https://leetcode.com/problems/find-the-lexicographically-largest-string-from-the-box-i/description/
 *
 * Solution link :
 *
 */

// Tags: Array, Greedy, String
public class FindTheLexicographicallyLargestStringFromTheBox1 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous but here we will use character array
    // we will use start and end pointer
    private static void type3() {
        String word = "dbca";
        int numFriends = 2;
        String ans = answerString3(word, numFriends);
        System.out.println(ans);
    }

    private static String answerString3(String word, int numFriends) {
        if (numFriends == 1) return word;
        char[] arr = word.toCharArray();
        int n = arr.length;
        int maxLen = n - (numFriends - 1);
        // initializing for the first window
        int start = 0, end = maxLen - 1;
        // we will start from 1
        int i = 1;
        // sliding window
        while (i + maxLen <= n) {
            if (isLess(arr, start, end, i, i + maxLen - 1)) {
                start = i;
                end = i + maxLen - 1;
            }
            i++;
        }
        // for the sub string at the ending
        while (i < n) {
            if (isLess(arr, start, end, i, n - 1)) {
                start = i;
                end = n - 1;
            }
            i++;
        }
        return word.substring(start, end + 1);
    }

    private static boolean isLess(char[] arr, int i1, int j1, int i2, int j2) {
        int n1 = j1 - i1 + 1;
        int n2 = j2 - i2 + 1;
        while (i1 <= j1 && i2 <= j2) {
            char a1 = arr[i1++], b1 = arr[i2++];
            if (a1 != b1) return (a1 < b1);
        }
        return n1 < n2;
    }


    // previous type is not optimal by any chance
    // because we are creating every possible string
    // so lets see the criteria of the question
    // we have to split the string into numFriends
    // todo we can be greedy here
    // we can give 1 letter to all the friends and give the max len string to one friend
    // if the string len is n and number of friends is k then so we will give 1 letter to everyone except 1 friend
    // so max len would be n - (k-1)
    // so if we do a sliding window of this size then we will get string max which has the max count and also lexicographically bigger
    // but there might be some lesser length string which is at the ending side
    // like aaaabbb and maxLen is 4
    // so in the sliding window we will get abbb but the ans would be bbb
    // so we will use another loop which will test (i,n) and we will increment i
    // it will test all string in the ending
    private static void type2() {
        String word = "dbca";
        int numFriends = 2;
        String ans = answerString2(word, numFriends);
        System.out.println(ans);
    }

    public static String answerString2(String word, int numFriends) {
        if (numFriends == 1) return word;
        int n = word.length();
        int maxLen = n - (numFriends - 1);
        String maxStr = word.substring(0, maxLen);
        int i = 1;
        // sliding window
        while (i + maxLen <= n) {
            String str = word.substring(i, i + maxLen);
            if (isLess(maxStr, str)) {
                maxStr = str;
            }
            i++;
        }
        // for the sub string at the ending
        while (i < n) {
            String str = word.substring(i, n);
            if (isLess(maxStr, str)) {
                maxStr = str;
            }
            i++;
        }
        return maxStr;
    }

    static boolean isLess(String a, String b) {
        int n1 = a.length(), n2 = b.length();
        for (int i = 0; i < n1 && i < n2; i++) {
            char a1 = a.charAt(i), b1 = b.charAt(i);
            if (a1 != b1) return (a1 < b1);
        }
        return (n1 < n2);
    }

    // brute force
    // split string every possible way then return the max
    private static void type1() {

    }


}
