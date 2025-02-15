package com.problems.array;

import java.util.Arrays;

/*
 *
 * problem links:
 * https://leetcode.com/problems/bag-of-tokens/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=prI82maTivg
 *
 * https://neetcode.io/solutions/bag-of-tokens
 * */

public class BagOfTokens {

    /*
     *
     * You start with an initial power of power, an initial score of 0, and a bag of tokens given as an integer array tokens,
     * Your goal is to maximize the total score by strategically playing these tokens. In one move, you can play an 'unplayed'
     * token in one of the two ways (but not both for the same token)
     *
     * Face-up: If your current power is at least tokens[i], you may and loose tokens[i] power and gaining 1 score.
     * Face-down: If your current score is at least 1, you may play and gain tokens[i] power and losing 1 score.
     *
     * Return the maximum possible score you can achieve after playing any number of tokens.
     * */
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo classic example of 2 pointers
    // we will be little greedy here
    // first we will sort the array, if we need to score we will hit the lowest token
    // and if we need the power we will try to hit the maximum token possible
    // as score will either increase or decrease by 1 only
    private static void type2() {
        int[] tokens = {100, 200, 300, 400};
        int power = 200;
        int ans = bagOfTokensScore2(tokens, power);
        System.out.println(ans);
    }

    public static int bagOfTokensScore2(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length;
        int i = 0, j = n - 1;
        int max = 0, score = 0;
        while (i <= j) {
            // if we have the power we will try to score as much as possible
            while (i <= j && power >= tokens[i]) {
                score++;
                power -= tokens[i++];
            }
            max = Math.max(max, score);
            // if the score has to be at least 1 then we can not hit any token, if 0 then we will break from here
            if (score == 0) break;
            // if there is any token available on the right side then we will hit that and grab the power
            if (i <= j) {
                score--;
                power += tokens[j--];
            }
        }
        return max;
    }

    private static void type1() {
    }
}
