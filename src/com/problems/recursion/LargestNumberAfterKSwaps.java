package com.problems.recursion;

/*
 * Problem link :
 * https://www.geeksforgeeks.org/problems/largest-number-in-k-swaps-1587115620/1
 *
 * Solution link:
 * https://www.youtube.com/watch?v=HAWAG7nil9o&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=9
 * https://www.youtube.com/watch?v=DOXoQfHyc7A&list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP&index=10
 *
 * https://www.geeksforgeeks.org/find-maximum-number-possible-by-doing-at-most-k-swaps/
 * */
public class LargestNumberAfterKSwaps {

    // TODO check the videos one more time
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int k = 3;
        String str = "4551711527";

        char[] arr = str.toCharArray();
        Data data = new Data(arr);
        findMaximumNum2(arr, k, data);
        String answer = new String(data.num);
        System.out.println(answer);
    }

    // Intuition is
    // The findMax function takes the original string, the remaining number of swaps (k),
    // and a reference to the maximum number found so far.
    //It uses nested loops to compare and swap each digit with every digit that follows it.
    //If a swap is performed, it recursively calls itself with the updated string and k-1 swaps.
    //The maximum number found so far is updated if the current number is greater.
    public static void findMaximumNum2(char[] num, int k, Data data) {
        // Return if no swaps left
        if (k == 0) return;
        int n = num.length;
        // Consider every digit
        for (int i = 0; i < n - 1; i++) {
            // Compare it with all digits after it
            for (int j = i + 1; j < n; j++) {
                // If digit at position i is less than digit at position j, swap it
                // and check for maximum number so far, then recurse for remaining swaps
                if (num[j] > num[i]) {
                    // Swap str[i] with str[j]
                    swap(num, i, j);

                    // If the current num is more than the maximum so far
                    if (data.isLesserThan(num))
                        System.arraycopy(num, 0, data.num, 0, n);

                    // Recurse for the other k - 1 swaps
                    findMaximumNum2(num, k - 1, data);

                    // Backtrack: Undo the swap for backtracking
                    swap(num, i, j);
                }
            }
        }
    }

    private static void swap(char[] num, int i, int start) {
        char temp = num[i];
        num[i] = num[start];
        num[start] = temp;
    }

    static class Data {
        char[] num;

        Data(char[] num) {
            this.num = new char[num.length];
            System.arraycopy(num, 0, this.num, 0, num.length);
        }

        public boolean isLesserThan(char[] other) {
            int i = 0;
            while (i != other.length && other[i] == num[i]) i++;
            return i != other.length && other[i] > num[i];
        }
    }
}
