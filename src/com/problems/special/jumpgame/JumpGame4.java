package com.problems.special.jumpgame;


import java.util.*;

/*
 *
 * problem links :
 * https://leetcode.com/problems/jump-game-iv/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=JYbU8RH1OSQ&list=PLJtzaiEpVo2yaP5v5bq0-QJgU0lO3TrEi&index=4
 *
 * */
public class JumpGame4 {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo the current problem is showing very inefficient in the leetcode if we use ArrayList in place of
    //  linkedlist, as the size of the input is very large
    //  but if the input is quite small then we can also use arraylist and that will be much more efficient
    // using bfs
    // use a queue and add nodes level wise
    // use linked list in place of normal array list
    private static void type2() {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        int step = minJumps2(arr);
        System.out.println(step);
    }

    public static int minJumps2(int[] arr) {
        // we will use a map for grouping
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], new LinkedList<>());
            map.get(arr[i]).add(i);
        }
        // we will use a visited array and a queue for the bfs
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        // we will initialise with index 0 and step 0
        visited[0] = true;
        queue.offer(0);
        int step = 0;
        // we will iterate till the queue is not empty
        while (!queue.isEmpty()) {
            // we will check all the nodes for the same level
            int size = queue.size();
            while (size-- > 0) {
                int node = queue.poll();
                // if it is the last node then we will return the step
                if (node == n - 1) return step;
                // we will check both left and right and add steps+1
                int left = node - 1, right = node + 1;
                if (left >= 0 && !visited[left]) {
                    visited[left] = true;
                    queue.add(left);
                }
                if (right < n && !visited[right]) {
                    visited[right] = true;
                    queue.add(right);
                }
                // we will also check the indices which save the same value
                List<Integer> group = map.get(arr[node]);
                // after retrieving the list we will remove the group from the map
                map.remove(arr[node]);
                // size is 1 means it is only having one index, so we can skip the current group
                if (group == null || group.size() == 1) continue;
                // we will check for all the nodes, we will just skip the parent node, for that we will check the visited array
                for (int idx : group) {
                    if (visited[idx]) continue;
                    visited[idx] = true;
                    queue.add(idx);
                }
            }
            step++;
        }
        return -1;
    }

    // this is quite simple problem we can solve this with bfs and dfs both
    private static void type1() {
        int[] arr = {100, -23, -23, 404, 100, 23, 23, 23, 3, 404};
        int step = minJumps1(arr);
        System.out.println(step);
    }

    // the main thing here is to group all the indices which have the same values
    private static int minJumps1(int[] arr) {
        // we will use a map for grouping
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i]))
                map.put(arr[i], new LinkedList<>());
            map.get(arr[i]).add(i);
        }
        // we will use a visited array and a queue for the bfs
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>();
        // we will initialise with index 0 and step 0
        visited[0] = true;
        queue.offer(new int[]{0, 0});
        // we will iterate till the queue is not empty
        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int node = pair[0];
            int step = pair[1];
            // if it is the last node then we will return the step
            if (node == n - 1) return step;
            // we will check both left and right and add steps+1
            int left = node - 1, right = node + 1;
            if (left >= 0 && !visited[left]) {
                visited[left] = true;
                queue.add(new int[]{left, step + 1});
            }
            if (right < n && !visited[right]) {
                visited[right] = true;
                queue.add(new int[]{right, step + 1});
            }
            // we will also check the indices which save the same value
            List<Integer> group = map.get(arr[node]);
            // after retrieving the list we will remove the group from the map
            map.remove(arr[node]);
            // size is 1 means it is only having one index, so we can skip the current group
            if (group == null || group.size() == 1) continue;
            // we will check for all the nodes, we will just skip the parent node, for that we will check the visited array
            for (int idx : group) {
                if (visited[idx]) continue;
                visited[idx] = true;
                queue.add(new int[]{idx, step + 1});
            }
        }
        return -1;
    }

}
