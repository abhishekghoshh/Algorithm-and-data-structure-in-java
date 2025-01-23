package com.problems.recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/combination-sum-iii/description/
 * https://www.naukri.com/code360/problems/combination-sum-iii_5038357
 *
 * Solution link
 *
 * */
public class CombinationSum3 {
    public static void main(String[] args) {
        type1();
    }

    // all the numbers are unique,
    // and also we don't have to think about an array
    private static void type1() {
        int k = 3;
        int n = 9;
        List<List<Integer>> answer = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        combinationSum1(1, k, n, list, answer);
        System.out.println(answer);
    }

    private static void combinationSum1(int num, int k, int remaining,
                                        LinkedList<Integer> list, List<List<Integer>> answer) {
        // if the k is 0 and remaining means the list can be one of the answers
        if (k == 0 && remaining == 0) {
            answer.add(new ArrayList<>(list));
            return;
        }
        // if the num reaches to 10 or all k digits consumed, then we will return directly
        // we do not have to check the remaining variable because we are only starting the recursion call.
        // if num is less than or equal to remaining, so num can never be less than 0
        if (num == 10 || k <= 0) return;
        if (num <= remaining) {
            // we are considering the num to be a part of the answer. so we will update all the variables
            list.add(num);
            combinationSum1(num + 1, k - 1, remaining - num, list, answer);
            list.removeLast();

            // we will be skipping the current num
            combinationSum1(num + 1, k, remaining, list, answer);
        }
    }


}
