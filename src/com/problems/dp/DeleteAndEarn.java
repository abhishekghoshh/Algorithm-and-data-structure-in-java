package com.problems.dp;

import java.util.*;

/*
 * Problem link:
 * https://leetcode.com/problems/delete-and-earn/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=7FCemBxvGw0 => Too complex
 *
 * https://neetcode.io/solutions/delete-and-earn => Too complex
 */


// Tags: Array, hashing, Dynamic Programming
public class DeleteAndEarn {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }


    // same as type 3
    // we will use prev and prev2 instead a dp array
    // on each iteration we will update that
    private static void type4() {
        int[] nums = {1, 2, 3, 15, 16, 17, 18};
        int ans = deleteAndEarn4(nums);
        System.out.println(ans);
    }

    private static int deleteAndEarn4(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int[] freq = new int[max + 1];
        int n = 0;
        for (int num : nums) {
            freq[num]++;
            if (freq[num] == 1) n++;
        }
        int[] list = new int[n];
        int i = 0;
        for (int num = 1; num <= max; num++) {
            if (freq[num] > 0) list[i++] = num;
        }
        int prev = 0, prev2 = 0;
        for (i = 0; i < n; i++) {
            int num = list[i];
            int points = num * freq[num];
            // collect prev and prev2
            int p1 = (i >= 1) ? prev : 0;
            int p2 = (i >= 2) ? prev2 : 0;
            int curr;
            // if nums[i-1] == num-1
            // then we have 2 choices either to take the prev2 + current point or take the prev point
            if (i > 0 && (list[i - 1] == num - 1)) {
                curr = Math.max(points + p2, p1);
            } else {
                curr = points + p1;
            }
            // update prev2 and prev
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    // iterative approach
    // same as type2, here we will go from 0 to n
    // and collect value for (i-1) and (i-2) and compute the value with the same condition
    private static void type3() {
        int[] nums = {1, 2, 3, 15, 16, 17, 18};
        int ans = deleteAndEarn3(nums);
        System.out.println(ans);
    }

    private static int deleteAndEarn3(int[] nums) {
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int[] freq = new int[max + 1];
        int n = 0;
        for (int num : nums) {
            freq[num]++;
            if (freq[num] == 1) n++;
        }
        int[] list = new int[n];
        int i = 0;
        for (int num = 1; num <= max; num++) {
            if (freq[num] > 0) list[i++] = num;
        }
        int[] dp = new int[n];
        for (i = 0; i < n; i++) {
            int num = list[i];
            int points = num * freq[num];
            // collect prev and prev2
            int prev = (i >= 1) ? dp[i - 1] : 0;
            int prev2 = (i >= 2) ? dp[i - 2] : 0;
            // if nums[i-1] == num-1
            // then we have 2 choices either to take the prev2 + current point or take the prev point
            if (i > 0 && (list[i - 1] == num - 1)) {
                dp[i] = Math.max(points + prev2, prev);
            } else {
                dp[i] = points + prev;
            }
        }
        return dp[n - 1];
    }

    // same as previous type
    // but here we are using array rather list or map
    private static void type2() {
        int[] nums = {1, 2, 3, 15, 16, 17, 18};
        int ans = deleteAndEarn2(nums);
        System.out.println(ans);
    }

    private static int deleteAndEarn2(int[] nums) {
        // calculating the frequency array
        int max = 0;
        for (int num : nums) max = Math.max(max, num);
        int[] freq = new int[max + 1];
        int n = 0;
        for (int num : nums) {
            freq[num]++;
            if (freq[num] == 1) n++;
        }
        // collecting all the distinct elements
        int[] list = new int[n];
        int i = 0;
        for (int num = 1; num <= max; num++) {
            if (freq[num] > 0) list[i++] = num;
        }
        int[] dp = new int[n];
        return deleteAndEarn2(n - 1, list, dp, freq);
    }

    private static int deleteAndEarn2(int i, int[] list, int[] dp, int[] freq) {
        if (i < 0) return 0;
        if (dp[i] != 0) return dp[i];
        int num = list[i];
        int points = num * freq[num];
        int prev = deleteAndEarn2(i - 1, list, dp, freq);
        int prev2 = deleteAndEarn2(i - 2, list, dp, freq);
        // if nums[i-1] == num-1
        // then we have 2 choices either to take the prev2 + current point or take the prev point
        if (i > 0 && (list[i - 1] == num - 1)) {
            dp[i] = Math.max(points + prev2, prev);
        } else {
            // else we can include the prev with the current point
            dp[i] = points + prev;
        }
        return dp[i];
    }

    // using dynamic programming
    // before doing anything lets make the intuition
    // if are choosing num then we could delete num-1 and num+1,
    // and we have multiple occurrences of the same numbers,
    // so we can just group the elements using a map
    // because if we consider num then we have to delete num-1 and nums+1,
    // and after deleting all the occurrences of num-1 and num+1, there might be some remaining num
    // and we will not have any other choice other than taking num, so why to take late
    // we can just take all of num at the first place
    // the keys of the map will be distinct elements of the array
    // we can just sort that in order to get all the elements arranged
    // todo now here is the actual intuition
    //  we can think of the problem as collect points for n elements
    //  we will start with 1 elements then go for 2,3,4,5...n
    //  if we are currently on i we can think of only nums[i]-1
    //  we do not need to think about nums[i]+1 as that will treated in future
    //  so now the problem boils down to a simple condition either to take num-1 and do not take it
    //  if i-1 th element is num-1 then we will not take that
    //  if we have to think it otherwise we can think dp[i] as max point till index i which may or may not include i
    //  we have also added dynamic programming in this
    private static void type1() {
        int[] nums = {1, 2, 3, 15, 16, 17, 18};
        int ans = deleteAndEarn1(nums);
        System.out.println(ans);
    }

    public static int deleteAndEarn1(int[] nums) {
        // calculating the frequency array
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
            freq.put(num, 1 + freq.getOrDefault(num, 0));
        // collecting the distinct elements and sort that
        List<Integer> list = new ArrayList<>(freq.keySet());
        Collections.sort(list);
        int n = list.size();
        // by default the dp will have 0 value, we will treat that as uninitialized
        int[] dp = new int[n];
        return deleteAndEarn1(n - 1, list, dp, freq);
    }

    private static int deleteAndEarn1(int i, List<Integer> list, int[] dp, Map<Integer, Integer> freq) {
        if (i < 0) return 0;
        if (dp[i] != 0) return dp[i];
        // calculating the point for current i
        int num = list.get(i);
        int points = num * freq.get(num);
        int prev = deleteAndEarn1(i - 1, list, dp, freq);
        int prev2 = deleteAndEarn1(i - 2, list, dp, freq);
        // if nums[i-1] == num-1
        // then we have 2 choices either to take the prev2 + current point or take the prev point
        if (i > 0 && (list.get(i - 1) == num - 1)) {
            dp[i] = Math.max(points + prev2, prev);
        } else {
            // else we can include the prev with the current point
            dp[i] = points + prev;
        }
        return dp[i];
    }
}
