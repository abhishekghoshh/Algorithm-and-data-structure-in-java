package com.problems.dp;

import java.util.Arrays;


/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/ninja%E2%80%99s-training_3621003
 * https://www.geeksforgeeks.org/problems/geeks-training/1
 *
 * Solution link :
 * https://www.youtube.com/watch?v=AE39gJYuRog&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=8
 *
 * https://takeuforward.org/data-structure/dynamic-programming-ninjas-training-dp-7/
 * */
public class NinjaTraining {

    // TODO check the problem one more time
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }


    private static void type4() {
        int[][] points = {
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        };
        int n = points.length;

        // Initialize an array 'prev' to store the maximum points for the previous day
        // as for the last day, we need to find the max for all the days,
        // and we are representing that with 3
        int[] prev = new int[4];

        // Initialize the first day's maximum points based on the available choices
        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);
        prev[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day starting from the second day
        for (int day = 1; day < n; day++) {
            // Initialize an array 'temp' to store the maximum points for the current day
            int[] temp = new int[4];
            for (int last = 0; last < 4; last++) {
                temp[last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity and add it to the maximum points from the previous day
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]);
                    }
                }
            }
            // Update 'prev' to store the maximum points for the current day
            prev = temp;
        }

        // Return the maximum points achievable after all days (last activity is 3)
        System.out.println(prev[3]);
    }


    private static void type3() {
        int[][] points = {
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        };
        int n = points.length;

        // creating and initializing the dp with n*4
        // as for the last day, we need to find the max for all the days,
        // and we are representing that with 3
        int[][] dp = new int[n][4];

        // Initialize the first day's maximum points based on the available choices
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

        // Iterate through each day and each activity
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                dp[day][last] = 0; // Initialize the maximum points for the current day and last activity
                // Consider each possible task for the current day
                for (int task = 0; task <= 2; task++) {
                    if (task != last) { // Ensure that the current task is different from the last
                        // Calculate the points for the current activity
                        // and add it to the maximum points from the previous day
                        int activity = points[day][task] + dp[day - 1][task];
                        // Update the maximum points for the current day and last activity
                        dp[day][last] = Math.max(dp[day][last], activity);
                    }
                }
            }
        }
        // the maximum points achievable after all days (last activity is 3)
        System.out.println(dp[n - 1][3]);
    }


    private static void type2() {
        int[][] points = {
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        };
        int n = points.length;
        // creating and initializing the dp with n*4
        // as for the last day, we need to find the max for all the days,
        // and we are representing that with 3
        int[][] dp = new int[n][4];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        // task ids are ranging from 0,1,2
        // and 3 means we want check for all tasks and take the max
        int answer = ninjaTraining(n - 1, points, 3, dp);
        System.out.println(answer);
    }

    private static int ninjaTraining(int n, int[][] points, int last, int[][] dp) {
        int max = 0;
        // if we are on the first row, then we will return the max out of all tasks,
        // but the catch the task should not be the same as the last day's task
        if (n == 0) {
            for (int task = 0; task < 3; task++) {
                if (task != last) max = Math.max(max, points[n][task]);
            }
            return dp[n][last] = max;
        }
        // if the cell value is already calculated, then we will directly return from the dp
        if (dp[n][last] != -1) return dp[n][last];

        // else we will go to previous cell but before that we have to take 2 tasks that are not done on the last day
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[n][task] + ninjaTraining(n - 1, points, task, dp);
                max = Math.max(max, point);
            }
        }
        return dp[n][last] = max;
    }

    private static void type1() {
        int[][] points = {
                {1, 2, 5},
                {3, 1, 1},
                {3, 3, 3}
        };
        int n = points.length;
        // task ids are ranging from 0,1,2
        // and 3 means we want check for all tasks and take the max
        int answer = ninjaTraining(n - 1, points, 3);
        System.out.println(answer);
    }

    private static int ninjaTraining(int n, int[][] points, int last) {
        int max = 0;
        // if we are on the first row, then we will return the max out of all tasks,
        // but the catch the task should not be the same as the last day's task
        if (n == 0) {
            for (int task = 0; task < 3; task++) {
                if (task != last) max = Math.max(max, points[n][task]);
            }
            return max;
        }
        // else we will go to previous cell but before that we have to take 2 tasks that are not done on the last day
        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[n][task] + ninjaTraining(n - 1, points, task);
                max = Math.max(max, point);
            }
        }
        return max;
    }
}
