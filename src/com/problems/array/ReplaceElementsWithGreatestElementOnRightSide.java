package com.problems.array;

import static com.util.PrintUtl.print;

/*
 * Problem link :
 * https://leetcode.com/problems/replace-elements-with-greatest-element-on-right-side/description/
 *
 * Solution link :
 *
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // optimized approach
    // we can do it in one pass, we just need to store the prev max result in a variable
    // time complexity O(n)
    // space complexity O(1)
    private static void type2() {
        int[] arr = {17, 18, 5, 4, 6, 1};
        int[] ans = replaceElements2(arr);
        print(ans);
    }

    public static int[] replaceElements2(int[] arr) {
        int n = arr.length;
        int max = -1;
        for (int i = n - 1; i >= 0; i--) {
            int num = arr[i];
            arr[i] = max;
            max = Math.max(max, num);
        }
        return arr;
    }

    // simple brute force
    // time complexity O(n^2)
    // space complexity O(n)
    private static void type1() {
        int[] arr = {17, 18, 5, 4, 6, 1};
        int[] ans = replaceElements1(arr);
        print(ans);
    }

    public static int[] replaceElements1(int[] arr) {
        int n = arr.length;
        int[] copy = arr.clone();
        for (int i = n - 1; i >= 0; i--) {
            int max = -1;
            // checking the max on the right side
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, copy[j]);
            }
            arr[i] = max;
        }
        return arr;
    }
}
