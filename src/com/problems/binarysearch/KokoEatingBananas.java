package com.problems.binarysearch;

/*
 *
 * problem links :
 * https://leetcode.com/problems/koko-eating-bananas/description/
 * https://www.codingninjas.com/studio/problems/minimum-rate-to-eat-bananas_7449064
 *
 * Solution link :
 * https://www.youtube.com/watch?v=qyfekrNni90
 *
 * https://takeuforward.org/binary-search/koko-eating-bananas/
 * */
public class KokoEatingBananas {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // binary search on answer
    // at a minimum, the monkey has take least 1 banana
    // if the monkey is bounded by k
    // then at max monkey can take max(pile)
    // so if we start binary search from 0 -> k
    // such that if we check if the monkey can eat at most k bananas
    // in one iteration and the monkey can finish
    // all the bananas
    private static void type2() {
        int[] piles = {30, 11, 23, 4, 20};
        int h = 5;
//        if (piles.length == 1) return divide(piles[0], h);
        int max = Integer.MIN_VALUE;
        for (int pile : piles) max = Math.max(max, pile);
        int answer = -1;
        int low = 1, high = max, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (canEat(piles, mid, h)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean canEat(int[] piles, int mid, int h) {
        int hourNeeded;
        for (int pile : piles) {
            hourNeeded = pile / mid + (pile % mid > 0 ? 1 : 0);
            h = h - hourNeeded;
        }
        return h >= 0;
    }

    // brute force approach
    private static void type1() {

    }
}
