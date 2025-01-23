package com.problems.recursion;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/print-1-to-n-without-using-loops-1587115620/1
 * https://www.geeksforgeeks.org/problems/sum-of-first-n-terms5843/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=yVdKa8dnKiE
 * https://www.youtube.com/watch?v=69ZCDFy-OUo
 *
 * https://takeuforward.org/recursion/introduction-to-recursion-understand-recursion-by-printing-something-n-times/
 * https://takeuforward.org/data-structure/sum-of-first-n-natural-numbers/
 * */
public class RecursionTheory {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // Print 1 To N Without Loop
    private static void type1() {
        int N = 10;
        printNos(1, N);
        System.out.println();
    }

    public static void printNos(int i, int N) {
        if (i == N + 1) return;
        System.out.print(i + " ");
        printNos(i + 1, N);
    }

    // Sum of first n terms
    private static void type2() {
        long n = 7;
        long ans = sumOfSeries(n);
        System.out.println(ans);
    }

    static long sumOfSeries(long n) {
        if (n == 0) return 0;
        return (n * n * n) + sumOfSeries(n - 1);
    }

}
