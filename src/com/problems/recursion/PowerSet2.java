package com.problems.recursion;

import java.util.*;

/*
 * Problem links:
 * https://leetcode.com/problems/subsets-ii/description/
 * https://neetcode.io/problems/subsets-ii
 * https://www.naukri.com/code360/problems/subsequences-of-string_985087
 * https://www.naukri.com/code360/problems/get-all-unique-subsets_624393
 *
 * Solution link
 * https://www.youtube.com/watch?v=b7AYbpM5YrE
 * https://www.youtube.com/watch?v=RIn3gOkbhQE&list=PLgUwDviBIf0p4ozDR_kJJkONnb1wdx2Ma&index=54
 *
 * https://www.youtube.com/watch?v=Yg5a2FxU4Fo&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=12
 * https://www.youtube.com/watch?v=lfFqW1DTsqM&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=13
 *
 *
 * https://takeuforward.org/data-structure/subset-ii-print-all-the-unique-subsets/
 * https://takeuforward.org/data-structure/subset-sum-sum-of-all-subsets/
 *
 *
 * https://www.youtube.com/watch?v=Vn2v6ajA7U0
 * https://neetcode.io/solutions/subsets-ii
 * */
public class PowerSet2 {

    // the algorithms used for no unique elements wil also work on the unique elements
    public static void main(String[] args) {
        type1();
        type2(); // todo this solution will not work
        type3();
    }

    // todo best solution, explain this in the interview
    //  Given array has duplicate characters
    //  here, our intuition is that we will pick one unique item a time from the remaining list
    //  first we will make 0 item lists then 1 item then 2 then n items
    private static void type3() {
        int[] nums = {1, 2, 2};
        List<List<Integer>> answer = powerSet3(nums);
        System.out.println(answer);
    }

    private static List<List<Integer>> powerSet3(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(nums);
        List<Integer> bucket = new ArrayList<>();
        powerSet3(nums, 0, bucket, answer);
        return answer;
    }

    // TODO check the method one more time if it still confuses you
    //  let's say we have a list 1,2,2,3
    //  we will start from every index and go till last
    private static void powerSet3(int[] arr, int start, List<Integer> list, List<List<Integer>> answer) {
        int n = arr.length;
        // one every recursion we will add the current list (till this recursion call) the answer
        answer.add(new ArrayList<>(list));
        for (int i = start; i < n; i++) {
            // we will check if the current item is the same as the previous item or not
            // if it is the same, then we will skip the loop
            // we have to add a condition that i is not
            if (i != start && arr[i - 1] == arr[i]) continue;
            // we are choosing arr[i] to be part of the bucket
            list.add(arr[i]);
            // computing the remaining
            powerSet3(arr, i + 1, list, answer);
            // after computing again removing it
            list.remove(list.size() - 1);
        }
    }

    // TODO this solution will not work (X)
    //  it will contain duplicate sets also
    private static void type2() {
        int[] nums = {1, 2, 1, 3, 2, 4};
        List<List<Integer>> answer = new ArrayList<>();
        powerSet2(answer, new ArrayList<>(), nums, 0);
        System.out.println(answer);
    }

    private static void powerSet2(List<List<Integer>> answer, List<Integer> list, int[] nums, int n) {
        if (nums.length == n) {
            answer.add(new ArrayList<>(list));
            return;
        }
        // here we are not choosing it to be a part of the answer
        powerSet2(answer, list, nums, n + 1);

        // here we are choosing the number to be an answer
        list.add(nums[n]);
        powerSet2(answer, list, nums, n + 1);
        list.remove(list.size() - 1);

    }

    // todo using recursion with a set
    //  duplicate elements with a set, extra computation needed
    private static void type1() {
        String str = "aaa";
        Set<String> answer = new HashSet<>();
        powerSet1(new StringBuilder(), 0, str, answer);
        System.out.println(answer);
    }

    private static void powerSet1(StringBuilder sb, int i, String str, Set<String> answer) {
        if (i == str.length()) {
            answer.add(sb.toString());
            return;
        }
        // here we are not choosing it to be a part of the answer
        powerSet1(sb, i + 1, str, answer);
        // here we are choosing the element to a part of the answer
        sb.append(str.charAt(i));
        powerSet1(sb, i + 1, str, answer);
        // as previous is a StringBuilder so we are changing the actual object so we
        // need to delete the last character which we have added previously
        sb.deleteCharAt(sb.length() - 1);
    }
}
