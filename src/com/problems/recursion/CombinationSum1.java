package com.problems.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/combination-sum/description/
 * https://neetcode.io/problems/combination-target-sum
 * https://www.naukri.com/code360/problems/759331
 * https://www.naukri.com/code360/problems/combination-sum_981296
 *
 * Solution link
 * https://www.youtube.com/watch?v=OyZFFqQtu98
 *
 * https://takeuforward.org/data-structure/combination-sum-1/
 *
 * https://www.youtube.com/watch?v=GBKI9VSKdGg
 * https://neetcode.io/solutions/combination-sum
 * */
public class CombinationSum1 {
    /*
     * Given an array of distinct integers candidates and a target integer target,
     * return a list of all unique combinations of candidates where the chosen
     * numbers sum to target. You may return the combinations in any order.
     *
     * The same number may be chosen from candidates an unlimited number of times.
     * Two combinations are unique if the frequency of at least one of the chosen
     * numbers is different.
     *
     * 1 <= candidates.length <= 30. 2 <= candidates[i] <= 40. All elements of
     * candidates are distinct.
     */
    public static void main(String[] args) {
        type1();
        type2();
    }

    // This is the same as the previous
    // here just we have sorted the input array.
    // So in the previous type we were going to the next element,
    // as we were not sure that the next element will be bigger or smaller.
    // So we will sort the input array in the first place, so that we will know
    // that if we cannot accommodate the current item, then the next item also
    // cannot be accommodated
    private static void type2() {
        int[] nums = {3, 1, 4, 2};
        int target = 7;
        List<List<Integer>> answer = combinationSum2(nums, target);
        System.out.println(answer);
    }

    private static List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> bucket = new ArrayList<>();
        Arrays.sort(nums);
        combinationSum2(nums, 0, target, bucket, answer);
        return answer;
    }

    // we can also use a linked list in place of an array list
    private static void combinationSum2(int[] nums, int n, int remaining, List<Integer> bucket, List<List<Integer>> answer) {
        if (n == nums.length) return;
        if (remaining == 0) {
            answer.add(new ArrayList<>(bucket));
            return;
        }
        // if we can accommodate the element, then we have two options either to include it or not
        if (nums[n] <= remaining) {
            // we will not include the element to be a part of the answer
            combinationSum2(nums, n + 1, remaining, bucket, answer);

            // we will accommodate the element,
            // but as we can use the same element, so we will start again from index
            bucket.add(nums[n]);
            combinationSum2(nums, n, remaining - nums[n], bucket, answer);
            bucket.remove(bucket.size() - 1);
        }
    }

    // Using recursion
    // for every index, we will check that we can accommodate the current index element or not.
    // If yes, we will have two possibilities, either to take it and not take it
    // if not, then we will skip the index and check for the next index.
    // As per the question, we can use any number multiple times,
    // So if I am considering the number, then for the next recursion call I can start from the same index
    private static void type1() {
        int[] nums = {3, 6, 2, 7};
        int target = 7;
        List<List<Integer>> answer = combinationSum1(nums, target);
        System.out.println(answer);
    }

    private static List<List<Integer>> combinationSum1(int[] nums, int target) {
        List<List<Integer>> answer = new ArrayList<>();
        List<Integer> bucket = new ArrayList<>();
        combinationSum1(nums, 0, target, bucket, answer);
        return answer;
    }

    private static void combinationSum1(int[] nums, int n, int remaining, List<Integer> bucket, List<List<Integer>> answer) {
        if (n == nums.length) {
            if (remaining == 0) answer.add(new ArrayList<>(bucket));
            return;
        }
        // if we can accommodate the element, then we have two options either to include it or not
        if (nums[n] <= remaining) {
            // we will not include the element to be a part of the answer
            combinationSum1(nums, n + 1, remaining, bucket, answer);

            // we will accommodate the element,
            // but as we can use the same element, so we will start again from index
            bucket.add(nums[n]);
            combinationSum1(nums, n, remaining - nums[n], bucket, answer);
            bucket.remove(bucket.size() - 1);
        } else {
            // we cannot accommodate that element we will just go to the next element
            combinationSum1(nums, n + 1, remaining, bucket, answer);
        }
    }

}
