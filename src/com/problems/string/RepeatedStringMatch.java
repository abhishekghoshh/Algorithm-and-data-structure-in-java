package com.problems.string;

/*
 * Problem link :
 * https://leetcode.com/problems/repeated-string-match/description/
 *
 * Solution link :
 * SURAJ KUMAR :https://www.youtube.com/watch?v=1WGtcErMPrQ
 */
public class RepeatedStringMatch {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }


    // TODO complete this method
    // same as previous
    // here we will user custom append and custom contains metho
    private static void type4() {
        String a = "aaac", b = "aac";
        boolean[] bucket = new boolean[26];
        char[] arr1 = a.toCharArray();
        for (char c : arr1) bucket[c - 'a'] = true;
//        for (char c : b.toCharArray()) if (!bucket[c - 'a']) return -1;
    }

    // same as previous just
    // we are trying to reduce the time complexity by creating our custom contains array
    private static void type3() {
        String a = "aaac", b = "aac";
        boolean[] bucket = new boolean[26];
        for (char c : a.toCharArray()) bucket[c - 'a'] = true;
//        for (char c : b.toCharArray()) if (!bucket[c - 'a']) return -1;
        int req = b.length() / a.length();
        int answer = -1;
        StringBuilder res = new StringBuilder(a.repeat(req));
        for (int i = 0; i <= 2; i++) {
            if (contains(res, b)) {
                answer = i;
                break;
            }
            res.append(a);
        }
        System.out.println(answer);
    }

    // we can create this contains method with either KMP or RabinKarp
    private static boolean contains(StringBuilder res, String b) {
        char[] st = (b + "&" + res).toCharArray();
        int n1 = st.length;
        int n2 = b.length();
        int[] lps = new int[n1];
        int i = 1, j = 0;
        while (i < n1) {
            if (st[i] == st[j]) {
                lps[i] = j + 1;
                i++;
                j++;
                if (j == n2) return true;
            } else if (j > 0) {
                j = lps[j - 1];
            } else {
                i++;
            }
        }
        return false;
    }


    // same as previous a little optimized
    private static void type2() {
        String a = "abcd", b = "cdabcdab";
        boolean[] bucket = new boolean[26];
        for (char c : a.toCharArray()) bucket[c - 'a'] = true;
//        for (char c : b.toCharArray()) if (!bucket[c - 'a']) return -1;
        int req = b.length() / a.length();
        int answer = -1;
        StringBuilder res = new StringBuilder(a.repeat(req));
        for (int i = 0; i <= 2; i++) {
            if (res.toString().contains(b)) {
                answer = i;
                break;
            }
            res.append(a);
        }
        System.out.println(answer);
    }

    // brute force approach
    // if the pattern is abcd and the text is cdabcdab
    // the text can be thought as => cd + abcd + ab
    // or in general prefix + n*pattern + suffix
    // if it has to match then
    // so if we can divide the length of text by the
    // length of pattern then we will the max possible
    // repetition of the pattern
    // we will add +2 for prefix and suffix
    private static void type1() {
        String a = "abcd", b = "cdabcdab";
        int req = b.length() / a.length();
        int answer = -1;
        StringBuilder res = new StringBuilder(a.repeat(req));
        for (int i = 0; i <= 2; i++) {
            if (res.toString().contains(b)) {
                answer = i;
                break;
            }
            res.append(a);
        }
        System.out.println(answer);
    }


}
