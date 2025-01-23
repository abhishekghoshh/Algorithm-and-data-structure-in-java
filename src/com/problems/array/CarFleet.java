package com.problems.array;

import java.util.Arrays;

/*
 *
 * problem links :
 * https://leetcode.com/problems/car-fleet/description/
 * https://neetcode.io/problems/car-fleet
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Pr6T-3yB9RM
 *
 * https://neetcode.io/solutions/car-fleet
 * */

// Tags: Array, Greedy, Stack
public class CarFleet {

    public static void main(String[] args) {
        type1();
        type2();
    }


    // this is also greedy but here we will do it differently
    // we will try to place the cars in a number line
    // this will take less time than the prev approach, but the space complexity is little higher
    // we will first get the max position, max means it is closest to the target
    // we will place the cars to their position
    // now we will iterate over the cars from the last to first
    // now we will apply the same logic as prev type

    // if the current car then less time than the prev then it will catch the prev car sometime, and go alongside the prev car
    // no matter what the speed is the current car will take the same time as the prev car,
    // if prev car took less time than the current car
    // then current car will never join the previous car, it will create a separate fleet,
    // so we will increase the counter and now update the prev car time

    // this is somewhat similar to previous type, just to replace the sort we are iterating from (max...0)
    // which reminds me of the counting sort

    // rather computing the max pos we can also create the array of size target
    private static void type2() {
        int target = 10;
        int[] position = {0, 4, 2};
        int[] speed = {2, 1, 3};
        int fleet = carFleet2(position, target, speed);
        System.out.println(fleet);
    }

    private static int carFleet2(int[] position, int target, int[] speed) {
        int n = position.length;
        int fleet = 0;
        // calculating the max
        int max = 0;
        for (int pos : position) {
            max = Math.max(max, pos);
        }
        // we will create an array where we will place the cars and mark there is a car by giving the time
        float[] times = new float[max + 1];
        for (int i = 0; i < n; i++) {
            int pos = position[i];
            // time to reach the target
            times[pos] = (float) (target - pos) / speed[i];
        }
        float prevTime = times[max];
        fleet++;
        for (int i = max - 1; i >= 0; i--) {
            float currTime = times[i];
            // there is no car so we can skip
            if (currTime == 0) continue;
            // if current car takes more time than the previous then they will not become a fleet, it will create another fleet
            if (times[i] > prevTime) {
                prevTime = times[i];
                fleet++;
            }
            // if the car takes less time than the prev car then it will come along with the prev car
        }
        return fleet;
    }

    // todo this is a greedy approach
    // whatever the speed is, the first car will always go at first,
    // so we will create an array of (n*2) of (pos,time to reach target)
    // and sort the cars as per the position, closest car will come first
    // now we will go one by one to the timings,
    // if the current car then less time than the prev then it will catch the prev car sometime, and go alongside the prev car
    // no matter what the speed is the current car will take the same time as the prev car,
    // if prev car took less time than the current car
    // then current car will never join the previous car, it will create a separate fleet,
    // so we will increase the counter and now update the prev car time

    // todo we can also use a stack and apply the same logic where we will get the prev time from the top of the stack
    //  and at last we will return the stack size at last
    private static void type1() {
        int target = 10;
        int[] position = {0, 4, 2};
        int[] speed = {2, 1, 3};
        int fleet = carFleet1(position, speed, target);
        System.out.println(fleet);
    }

    private static int carFleet1(int[] position, int[] speed, int target) {
        int fleet = 0;
        int n = position.length;
        float[][] timings = new float[n][2];
        for (int i = 0; i < n; i++) {
            timings[i][0] = position[i];
            // time to reach the target
            timings[i][1] = (float) (target - position[i]) / speed[i];
        }
        // whatever the speed is the first car will go at first
        Arrays.sort(timings, (p1, p2) -> Float.compare(p2[0], p1[0]));
        float prevTime = Float.MIN_VALUE;
        // we are going from the closest car to the farthest car from the target
        for (float[] timing : timings) {
            // time to reach the target for the current
            float currTime = timing[1];
            // if current car takes more time than the previous then they will not become a fleet, it will create another fleet
            if (currTime > prevTime) {
                prevTime = currTime;
                fleet++;
            }
            // if the car takes less time than the prev car then it will come along with the prev car
        }
        return fleet;
    }

}
