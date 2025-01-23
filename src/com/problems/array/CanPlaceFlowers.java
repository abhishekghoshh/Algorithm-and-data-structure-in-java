package com.problems.array;
/*
 * Problem link:
 * https://leetcode.com/problems/can-place-flowers/description/
 *
 * Solution link:
 *
 * https://neetcode.io/solutions/can-place-flowers
 */

public class CanPlaceFlowers {
    public static void main(String[] args) {
        type1();
    }

    // optimized approach
    // for every 0th cell we will check its previous and next cell
    // if in both places there is not 1 then we will place 1 to that
    private static void type1() {
        int[] flowerbed = {1, 0, 0, 0, 1};
        int n = 1;
        boolean ans = canPlaceFlowers1(flowerbed, n);
        System.out.println(ans);
    }

    public static boolean canPlaceFlowers1(int[] flowerbed, int n) {
        int len = flowerbed.length;
        int total = 0;
        for (int i = 0; i < len; i++) {
            // if the cell is 1 then we will continue
            if (flowerbed[i] == 1) continue;
            boolean hasPrevOne = (i != 0) && (flowerbed[i - 1] == 1);
            boolean hasNextOne = (i != len - 1) && (flowerbed[i + 1] == 1);
            // if both places are not 1 then we will place 1 to the current cell and increment the total
            if (!hasPrevOne && !hasNextOne) {
                flowerbed[i] = 1;
                total++;
            }
        }
        return n <= total;
    }
}
