package com.problems.special.jumpgame;


import java.util.LinkedList;
import java.util.Queue;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-iii/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=7Cz91Uj0VCU&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=3
 *
 * */
public class JumpGame3 {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo exactly same as the previous
    // we can do some optimizations on the bfs solution
    // we can check all the nodes in the same level of queue at a time
    // we can negate the point just to mark it as visited
    private static void type4() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        boolean ans = canReach4(arr, start);
        System.out.println(ans);
    }

    // we will use a queue for tracking the nodes, and we will go nodes one by one
    // and once we reach the final index we will return true
    public static boolean canReach4(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // checking all nodes at a level
            while (size-- > 0) {
                int curr = queue.poll();
                // if the array value is 0 that means we have reached the final position, so we will return true
                if (arr[curr] == 0) return true;
                // we will find the left and the right indices
                int left = curr - arr[curr], right = curr + arr[curr];
                // checking if the left index is not out of bounds and not visited
                if (left >= 0 && !visited[left]) {
                    visited[left] = true;
                    queue.add(left);
                }
                // checking if the right index is not out of bounds and not visited
                if (right < n && !visited[right]) {
                    visited[right] = true;
                    queue.add(right);
                }
            }
        }
        return false;
    }


    // todo it is not fast as the dfs
    // using BFS with Queue
    // but this is quite slow as we are using queue
    // recursion is faster than this
    private static void type3() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        boolean ans = canReach3(arr, start);
        System.out.println(ans);
    }

    // we will use a queue for tracking the nodes, and we will go nodes one by one
    // and once we reach the final index we will return true
    public static boolean canReach3(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            // if the array value is 0 that means we have reached the final position, so we will return true
            if (arr[curr] == 0) return true;
            // we will find the left and the right indices
            int left = curr - arr[curr], right = curr + arr[curr];
            // checking if the left index is not out of bounds and not visited
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.add(left);
            }
            // checking if the right index is not out of bounds and not visited
            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.add(right);
            }
        }
        return false;
    }

    // same as type 1 recursion
    // we are not using any extra visited array to mark
    // here we will change the value to negative to mark it as visited
    private static void type2() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        boolean ans = canReach2(start, arr, arr.length);
        System.out.println(ans);
    }

    public static boolean canReach2(int start, int[] arr, int n) {
        // if the index is out of boundary then we will return false
        if (start < 0 || start >= n) return false;
        // if the index is already visited then we will return false
        if (arr[start] < 0) return false;
        // if the array value is 0 that means we have reached the final position, so we will return true
        if (arr[start] == 0) return true;
        // we will find the left and the right indices
        int left = start - arr[start], right = start + arr[start];
        // we will set the visited to true as currently we will be operating on that
        arr[start] = -arr[start];
        // Try jumping left and right recursively
        return canReach2(left, arr, n)
                || canReach2(right, arr, n);
    }

    // using recursion
    private static void type1() {
        int[] arr = {4, 2, 3, 0, 3, 1, 2};
        int start = 5;
        boolean ans = canReach1(arr, start);
        System.out.println(ans);
    }

    public static boolean canReach1(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return canReach1(start, arr, visited, n);
    }

    private static boolean canReach1(int start, int[] arr, boolean[] visited, int n) {
        // if the index is out of boundary then we will return false
        if (start < 0 || start >= n) return false;
        // if the index is already visited then we will return false
        if (visited[start]) return false;
        // if the array value is 0 that means we have reached the final position, so we will return true
        if (arr[start] == 0) return true;
        // we will set the visited to true as currently we will be operating on that
        visited[start] = true;
        // we will find the left and the right indices
        int left = start - arr[start], right = start + arr[start];
        // and recursively visit both
        return canReach1(left, arr, visited, n)
                || canReach1(right, arr, visited, n);
    }
}
