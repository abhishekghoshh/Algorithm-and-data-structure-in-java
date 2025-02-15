package com.problems.binarysearch;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/lower-bound_8165382
 *
 *
 * Solution link :
 * https://www.youtube.com/watch?v=6zhGS79oQ4k
 *
 *
 * https://takeuforward.org/arrays/implement-lower-bound-bs-2/
 * */
public class LowerBound {
    public static void main(String[] args) {
        type1();
        type2();
    }

    private static void type2() {
        int[] arr = {3, 5, 8, 15, 19};
        int x = 9;
        int answer = lowerBound(arr, x);
        System.out.println(answer);
    }

    public static int lowerBound(int[] arr, int x) {
        int n = arr.length;
        int low = 0, high = n - 1, mid;
        int answer = n;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // This condition means that the index mid may be an answer.
            // So, we will update the ‘ans’ variable with mid and search
            // in the left half if there is any smaller index that satisfies
            // the same condition. Here, we are eliminating the right half
            if (arr[mid] >= x) {
                answer = mid;
                //look for smaller index on the left
                high = mid - 1;
            } else {
                // In this case, mid cannot be our answer, and
                // we need to find some bigger element. So,
                // we will eliminate the left half and search in the right half for the answer.
                low = mid + 1; // look on the right
            }
        }
        return answer;
    }

    // brute force approach
    private static void type1() {
        int[] arr = {3, 5, 8, 15, 19};
        int n = arr.length, x = 9;
        int answer = n;
        for (int i = 0; i < n; i++)
            // lower bound found
            if (arr[i] >= x) {
                answer = i;
                break;
            }

        System.out.println(answer);
    }
}
