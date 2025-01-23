package com.problems.dp;

/*
 * Problem link:
 * https://leetcode.com/problems/champagne-tower/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=LQ8TuG_QADM
 *
 * https://neetcode.io/solutions/champagne-tower
 */

// Tags: Array, Dynamic programming
public class ChampagneTower {
    // this is a problem of dynamic programming, but it seems like an array problem
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // dynamic programming with space optimization
    private static void type3() {
    }

    // optimized approach using dynamic programming
    // here we will just mimic the tower
    // like if we put x amount of champagne and if it is greater than 1
    // then (amount-1)/2 will fall into its left and right
    // (0,0) => (1,0) (1,1)
    // (1,0) => (2,0) (2,1)
    // (1,1) => (2,1) (2,2)
    // so (i,j) will fall its extras into (i+1,j) and (i+1,j+1)
    // we will go till the last row or till there is any extra Champagne to be added in the next row
    private static void type2() {
        int poured = 100000009;
        int query_row = 33;
        int query_glass = 17;
    }

    public static double champagneTower2(int poured, int query_row, int query_glass) {
        if (poured == 0) return 0;
        int n = query_row + 1;
        // we will create a tower
        double[][] tower = new double[n][n];
        tower[0][0] = poured;
        boolean hasExtraChampagne;
        // we will traverse throw all the row
        for (int i = 0; i < n; i++) {
            hasExtraChampagne = false;
            // we will traverse through all the glasses of each row
            for (int j = 0; j <= i; j++) {
                // if the current glass is less, than equal to 1, then it cannot add extra to the next row
                if (tower[i][j] <= 1) continue;
                // has extra to add in the next row
                hasExtraChampagne = true;
                double extra = tower[i][j] - 1;
                tower[i][j] = 1;
                // if it is the last row, then we cannot update the next row
                if (i + 1 == n) continue;
                tower[i + 1][j] += extra / 2;
                tower[i + 1][j + 1] += extra / 2;
            }
            if (!hasExtraChampagne) break;
        }
        return tower[query_row][query_glass];
    }

    // using brute force
    private static void type1() {
    }
}
