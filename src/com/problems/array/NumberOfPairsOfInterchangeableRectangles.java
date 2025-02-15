package com.problems.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
 *
 * problem links :
 * https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=lEQ8ZlLOuyQ
 *
 * https://neetcode.io/solutions/number-of-pairs-of-interchangeable-rectangles
 * */
public class NumberOfPairsOfInterchangeableRectangles {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // not the most optimized approach
    // as an array is easier and less time-consuming to handle, so we will use an array to store the ratios
    // we will create an array of double as the same size of the rectangles.
    // then sort that array, so same ratios will come in together
    // we will count that then apply the f*(f-1)/2
    private static void type2() {
        int[][] rectangles = {{4, 8}, {3, 6}, {10, 20}, {5, 30}};
        long ans = interchangeableRectangles2(rectangles);
        System.out.println(ans);
    }

    private static long interchangeableRectangles2(int[][] rectangles) {
        int n = rectangles.length;
        // storing all the ratios
        double[] ratios = new double[n];
        int i = 0;
        for (int[] rec : rectangles) {
            ratios[i++] = (double) rec[0] / (double) rec[1];
        }
        Arrays.sort(ratios);
        // now counting for all the ratios
        double prev = 0;
        long count = 0, f = 0;
        for (i = 0; i <= n; i++) {
            if (i == n || prev != ratios[i]) {
                count += (f * (f - 1)) / 2;
                if (i != n) {
                    prev = ratios[i];
                    f = 1;
                }
            } else {
                f++;
            }
        }
        return count;
    }

    // brute force approach using a hashmap
    // we will store the ratios and their freq in a map
    // if there are f number of rectangles with the same ratios.
    // if 2 rectangles can interchange, so the problem is to choose 2 among f options
    // which will be fC2 ⇒ f*(f-1)/2
    private static void type1() {
        int[][] rectangles = {{4, 8}, {3, 6}, {10, 20}, {5, 30}};
        long ans = interchangeableRectangles1(rectangles);
        System.out.println(ans);
    }

    public static long interchangeableRectangles1(int[][] rectangles) {
        // now counting for all the ratios
        Map<Double, Long> freq = new HashMap<>();
        for (int[] rec : rectangles) {
            double ratio = (double) rec[0] / (double) rec[1];
            freq.put(ratio, 1L + freq.getOrDefault(ratio, 0L));
        }
        long count = 0;
        for (Map.Entry<Double, Long> entry : freq.entrySet()) {
            long f = entry.getValue();
            if (f == 1) continue;
            count += (f * (f - 1)) / 2;
        }
        return count;
    }
}
