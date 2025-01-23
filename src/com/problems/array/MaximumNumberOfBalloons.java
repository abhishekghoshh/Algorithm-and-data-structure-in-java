package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/maximum-number-of-balloons/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=G9xeB2-7PqY
 * */
public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String text = "nlaebolko";
        int ans = maxNumberOfBalloons(text);
        System.out.println(ans);
    }

    public static int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];
        // first we will store the freq for the text
        for (char ch : text.toCharArray()) {
            freq[index(ch)]++;
        }
        // in balloon frequencies a -> 1, b -> 1, l -> 2, o -> 2, n -> 1
        // so in the freq of the text we will check
        // how many 1 times 'a', 1 times 'b',2 times 'l', 2 times 'o', 1 times 'n' are present
        // so their minimum will be total number of times we can make "balloon" word
        return min(
                freq[index('a')],
                freq[index('b')],
                freq[index('l')] / 2,
                freq[index('o')] / 2,
                freq[index('n')]
        );
    }

    static int min(int... freq) {
        int min = Integer.MAX_VALUE;
        for (int f : freq) min = Math.min(min, f);
        return min;
    }

    static int index(char ch) {
        return ch - 'a';
    }
}
