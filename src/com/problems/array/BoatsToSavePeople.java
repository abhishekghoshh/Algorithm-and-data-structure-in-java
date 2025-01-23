package com.problems.array;
/*
 * Problem link :
 * https://leetcode.com/problems/boats-to-save-people/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=XbaxWuHIWUs
 *
 * https://neetcode.io/solutions/boats-to-save-people
 * */

import java.util.Arrays;

// Tags: Array, Two pointer, Sorting, Greedy
public class BoatsToSavePeople {
    // 1. there are infinite number of boats
    // 2. Each boat carries at most two people at the same time
    // 3. there is a max weight a boat can carry
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo instead of sort we could just find min and max and place them in a number line
    //  only useful for leetcode but not for interview and real world scenario
    private static void type2() {
    }

    // todo we will be little greedy over here
    //  we will first try to fit the persons who can not adjust with other in a boat
    //  so we will sort the array so that people could be arranged by their weight
    //  then we will use 2 pointer to allot them a boat
    private static void type1() {
        int[] people = {3, 2, 2, 1};
        int limit = 3;
        int ans = numRescueBoats2(people, limit);
        System.out.println(ans);
    }

    public static int numRescueBoats2(int[] people, int limit) {
        Arrays.sort(people);
        int n = people.length;
        int count = 0;
        int left = 0, right = n - 1;
        while (left < right) {
            // right most person is the most weighted person and left most person is the lightest person currently,
            // if the right most person can make it then we have fit the person in a single boat
            if (people[left] + people[right] > limit) {
                right--; // we could only fit a single
            } else {
                // fitting both the people
                left++;
                right--;
            }
            count++;
        }
        // if number of people is odd and only one person is left
        if(left == right) count++;
        return count;
    }
}
