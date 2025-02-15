package com.problems.binarysearch;

/*
 *
 * problem links :
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/description/
 * https://www.codingninjas.com/studio/problems/capacity-to-ship-packages-within-d-days_1229379
 *
 * Solution link :
 * https://www.youtube.com/watch?v=MG-Ac4TAvTY
 *
 * https://takeuforward.org/arrays/capacity-to-ship-packages-within-d-days/
 * */
public class CapacityToShipPackagesWithinDDays {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // same as previous just we are just modifying the code little bit
    private static void type3() {
        int[] weights = {1, 2, 3, 1, 1};
        int days = 4;
        int high = 0, low = 0;
        for (int weight : weights) {
            high += weight;
            if (low < weight) low = weight;
        }
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            int curDays = 0;
            int curSum = 0;
            for (int weight : weights) {
                if (curSum + weight > mid) {
                    curDays++;
                    curSum = 0;
                }
                curSum += weight;
            }
            if (curDays >= days) low = mid + 1;
            else high = mid;
        }
        System.out.println(low);
    }

    // binary search on method approach
    private static void type2() {
        int[] weights = {1, 2, 3, 1, 1};
        int days = 4;
        int answer = Integer.MAX_VALUE, sum = 0;
        for (int weight : weights) sum += weight;
        int low = 1, high = sum, mid;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (isPossible(weights, mid, days)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean isPossible(int[] weights, int mid, int days) {
        int sum = 0, count = 0;
        for (int weight : weights) {
            if (weight > mid) return false;
            sum += weight;
            if (sum == mid) {
                sum = 0;
                count++;
            } else if (sum > mid) {
                sum = weight;
                count++;
            }
        }
        if (sum > 0) count++;
        return count <= days;
    }

    // brute force approach
    private static void type1() {
    }
}
