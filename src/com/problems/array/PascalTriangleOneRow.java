package com.problems.array;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * problem links:
 * https://leetcode.com/problems/pascals-triangle-ii/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=k1DNTyal77I
 *
 * https://neetcode.io/solutions/pascals-triangle-ii
 * */
public class PascalTriangleOneRow {
    public static void main(String[] args) {
        type1();
    }

    // Create all rows of pascal triangle
    //    1
    //   1 1
    //  1 2 1
    // 1 3 3 1
    // if we consider one row then the coefficient are something like (a+b) ^ (n-1)
    // for row 1st row 1
    // 2nd row a+b
    // 3rd row a^2 + 2ab + b^2
    // 4th row a^3 + 3a^2b + 3ab^2 + b^3
    // for a particular ith cell it is (n-1-i) C i
    // for the first row it is 1 then (n/i) then n*(n-1) / i*(i+1) and so on,
    // so we will start with n and i and decrease n and increase i
    private static void type1() {
        int numRow = 2;
        List<Integer> row = getRow1(numRow);
        System.out.println("5th row is " + row);
    }

    // this code is 0 indexed the rows are (a+b) ^ (n-1)
    private static List<Integer> getRow1(int row) {
        List<Integer> ans = new ArrayList<>();
        ans.add(1);
        for (double upper = row, lower = 1; lower <= row; lower++, upper--) {
            double last = ans.get(ans.size() - 1);
            double curr = Math.round(last * upper / lower);
            ans.add((int) curr);
        }
        return ans;
    }
}
