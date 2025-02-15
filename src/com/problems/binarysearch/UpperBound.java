package com.problems.binarysearch;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/implement-upper-bound_8165383
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=6zhGS79oQ4k
 *
 * https://takeuforward.org/arrays/implement-upper-bound/
 * */
public class UpperBound {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] arr = {3, 5, 8, 9, 15, 19};
        int x = 9;
        int answer = upperBound(arr, x);
        System.out.println(answer);
    }

    public static int upperBound(int[] arr, int x) {
        int n = arr.length;
        int low = 0, high = n - 1;
        int ans = n;
        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }

    // brute force approach
    private static void type1() {
        int[] arr = {3, 5, 8, 9, 15, 19};
        int n = arr.length, x = 9;
        int answer = n;
        for (int i = 0; i < n; i++) {
            if (arr[i] > x) {
                // upper-bound found
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }
}
