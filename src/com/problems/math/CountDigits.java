package com.problems.math;

/*
 *
 * problem links :
 * https://www.geeksforgeeks.org/problems/count-digits5716/1
 * Solution link :
 * https://www.youtube.com/watch?v=1xNbjMdbjug
 *
 * https://takeuforward.org/data-structure/count-digits-in-a-number/
 * */
public class CountDigits {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int n = 2446;
        int ans = evenlyDivides(n);
        System.out.println(ans);
    }

    static int evenlyDivides(int N) {
        // code here
        int i = 0;
        int num = N;
        while (N > 0) {
            int digit = N % 10;
            N = N / 10;
            if (digit != 0 && num % digit == 0) i++;
        }
        return i;
    }
}
