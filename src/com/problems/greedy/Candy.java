package com.problems.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 *
 * problem links :
 * https://leetcode.com/problems/candy/description/
 * https://www.codingninjas.com/studio/problems/candies_893290
 *
 * Solution link :
 *
 *
 *
 * */
public class Candy {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // greedy approach
    private static void type4() {
        int[] ratings = {1, 2, 2};
        int candies = candy4(ratings);
        System.out.println(candies);
    }

    private static int candy4(int[] ratings) {
        int n = ratings.length;
        int total = 0;
        int[] candies = new int[n];
        // we are assigning 1 candy to everyone
        Arrays.fill(candies, 1);
        // first, we will go from left to right,
        // and every time we are checking the next rating is greater than the current or not
        // if it is then we are adding one candies to the next
        for (int i = 0; i < n - 1; i++)
            if (ratings[i] < ratings[i + 1])
                candies[i + 1] = candies[i] + 1;
        // now we will go from the last to first
        // every time we will check if the previous rating is greater than the current rating or not
        // and also if the previous candy assignment is already greater than the current or not
        for (int j = n - 1; j > 0; j--)
            if (ratings[j - 1] > ratings[j] && candies[j - 1] <= candies[j])
                candies[j - 1] = candies[j] + 1;
        for (int candy : candies) total += candy;
        return total;
    }

    // using a normal array to sort ratings
    private static void type3() {
        int[] ratings = {1, 2, 2};
        int candies = candy3(ratings);
        System.out.println(candies);
    }

    public static int candy3(int[] ratings) {
        int n = ratings.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = i;
            arr[i][1] = ratings[i];
        }
        // sort the array by the ratings
        Arrays.sort(arr, Comparator.comparingInt(p -> p[1]));
        int[] candies = new int[n];
        int i, sum = 0, candy;
        // picking the least rating
        for (int[] pair : arr) {
            i = pair[0];
            // we are giving at least one candy to everyone then we are checking its left and right
            candy = 1;
            if (i > 0 && ratings[i] > ratings[i - 1]) candy = candies[i - 1] + 1;
            if (i < n - 1 && ratings[i] > ratings[i + 1]) candy = Math.max(candy, candies[i + 1] + 1);
            candies[i] = candy;
            sum += candies[i];
        }
        return sum;
    }

    // optimized approach
    // using heap finds the least rating and then assigns the candies
    private static void type2() {
        int[] ratings = {1, 2, 2};
        int candies = candy2(ratings);
        System.out.println(candies);
    }

    public static int candy2(int[] ratings) {
        int n = ratings.length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(p -> p[0]));
        for (int i = 0; i < n; i++) minHeap.offer(new int[]{ratings[i], i});
        int[] candies = new int[n];
        int i, sum = 0, candy;
        int[] pair;
        // picking the least rating
        while (!minHeap.isEmpty()) {
            pair = minHeap.poll();
            i = pair[1];
            // we are giving at least one candy to everyone then we are checking its left and right
            candy = 1;
            if (i > 0 && ratings[i] > ratings[i - 1]) candy = candies[i - 1] + 1;
            if (i < n - 1 && ratings[i] > ratings[i + 1]) candy = Math.max(candy, candies[i + 1] + 1);
            candies[i] = candy;
            sum += candies[i];
        }
        return sum;
    }

    // brute force approach
    private static void type1() {

    }
}
