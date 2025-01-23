package com.problems.array;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
 * Problem link:
 * https://leetcode.com/problems/find-polygon-with-the-largest-perimeter/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=Yk9Mor-Y488
 *
 * https://neetcode.io/solutions/find-polygon-with-the-largest-perimeter
 */
// Tags: Arrays, Prefix Sum, Priority Queue, Greedy, Sorting
public class FindPolygonWithTheLargestPerimeter {

    public static void main(String[] args) {
        type1();
        type2();
    }


    // using priority queue
    // here we are doing it in a reverse way
    // here we will calculate the total sum and using a max heap we are saving all the num
    // now we are polling sides from heap one by one
    // if we subtract side from the sum we will get sum[a1..ak-1]
    // now we will check if sum[a1..ak-1] > ak or not
    // if yes then return the sum else remove ak from the sum
    // continue this till the side is atleast 3
    private static void type2() {
        int[] nums = {1, 12, 1, 2, 5, 50, 3};
        long ans = largestPerimeter2(nums);
        System.out.println(ans);
    }

    private static long largestPerimeter2(int[] nums) {
        long sum = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        for (int num : nums) {
            sum += num;
            heap.offer(num);
        }
        while (heap.size() >= 3) {
            int side = heap.poll();
            long remSum = sum - side;
            if (remSum > side) {
                return sum;
            }
            sum = remSum;
        }
        return -1;
    }

    // todo explain this in the interview
    // this is a greedy approach
    // so there are 2 conditions
    // A polygon is a closed plane figure that has at least 3 sides
    // a1 + a2 + a3 + ... + ak-1 > ak
    // here we will sort the array first, so lower sides will come first
    // so if we just use a loop and iterate over the array and get the side total
    // then we can just check sum(a1..ak-1) > a[k] or not
    // if yes we can just include the a[k] to our polygon
    // and we don't need to check for other combination as a[k] is highest among others
    // if sum(a1..ak-1) > a[k] satisfies then sum(a1..ak) > a[i] will be satisfied
    // so if total is greater than num then we will think that [a1..ak] will be our current largest polygon
    // we will update the total in every iteration
    private static void type1() {
        int[] nums = {1, 12, 1, 2, 5, 50, 3};
        long ans = largestPerimeter1(nums);
        System.out.println(ans);
    }

    public static long largestPerimeter1(int[] nums) {
        Arrays.sort(nums);
        long perimeter = -1;
        long total = 0;
        for (int num : nums) {
            if (total > num) {
                perimeter = total + num;
            }
            total += num;
        }
        return perimeter;
    }
}
