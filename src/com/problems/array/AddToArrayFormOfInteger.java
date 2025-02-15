package com.problems.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Problem link :
 * https://leetcode.com/problems/add-to-array-form-of-integer/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=eBTZQt1TWfk
 * https://neetcode.io/solutions/add-to-array-form-of-integer
 */
public class AddToArrayFormOfInteger {
    public static void main(String[] args) {
        type1();
    }

    // todo optimized approach
    private static void type1() {
        int[] num = {2, 1, 5};
        int k = 806;
        List<Integer> ans = addToArrayForm(num, k);
        System.out.println(ans);
    }


    private static List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        List<Integer> res = new ArrayList<>(n + 1);
        int i = n - 1;
        int carry = 0;
        while (i >= 0 || k > 0 || carry == 1) {
            int digit1 = k % 10;
            k /= 10;
            int digit2 = (i >= 0) ? num[i--] : 0;
            int sum = digit1 + digit2 + carry;
            // adding the last digit of the sum to the result
            res.add(sum % 10);
            // if the sum is greater than 9, then carry will be 1, otherwise 0
            carry = sum / 10;
        }
        // reversing again as we had added from the last
        Collections.reverse(res);
        return res;
    }
}
