package com.problems.stack;

import com.util.PrintUtl;

import java.util.Stack;

/*
 * Problem link :
 * https://leetcode.com/problems/asteroid-collision/description/
 * https://www.naukri.com/code360/problems/asteroid-collision_977232
 *
 * Solution link :
 * https://www.youtube.com/watch?v=LN7KjRszjk4
 *
 * https://neetcode.io/solutions/asteroid-collision
 * */
public class AsteroidCollision {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // todo optimized approach as previous using stack,
    //  here we are using int array as stack
    private static void type2() {
        int[] asteroids = {10, 2, -5};
        int[] answer = asteroidCollision2(asteroids);
        PrintUtl.print(answer);
    }

    private static int[] asteroidCollision2(int[] asteroids) {
        int n = asteroids.length;
        int[] st = new int[n];
        int top = -1;
        for (int asteroid : asteroids) {
            // current asteroid is less than 0 and there is a positive asteroid in the stack
            while (top != -1 && st[top] > 0 && asteroid < 0) {
                // polling from the stack to test it with the current asteroid
                int last = st[top--];
                // the last asteroid is same as the current, so we will destroy both and set the current asteroid as 0
                if (last == (-asteroid)) {
                    asteroid = 0;
                } else if (last > (-asteroid)) {
                    // the last is bigger than the current then we will destroy the current and assign the last to current
                    asteroid = last;
                }
            }
            // not equal to 0 means it's a valid asteroid
            if (asteroid != 0)
                st[++top] = asteroid;
        }
        // adding to the ans
        int[] ans = new int[top + 1];
        while (top != -1) ans[top] = st[top--];
        return ans;
    }

    // todo optimized approach using stack,
    //  we will only check the condition if current asteroid is less than 0 and there is a positive asteroid in the stack
    //  then we will poll from the stack to test it with the current asteroid
    //  if the last asteroid is same as the current, so we will destroy both and set the current asteroid as 0
    //  if the last is bigger than the current then we will destroy the current and assign the last to current
    //  if current asteroid is bigger then we do not need to do anything, as the last is already popped
    private static void type1() {
        int[] asteroids = {10, 2, -5};
        int[] ans = asteroidCollision1(asteroids);
        PrintUtl.print(ans);
    }

    private static int[] asteroidCollision1(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int asteroid : asteroids) {
            // current asteroid is less than 0 and there is a positive asteroid in the stack
            while (!st.isEmpty() && st.peek() > 0 && asteroid < 0) {
                // polling from the stack to test it with the current asteroid
                int last = st.pop();
                // the last asteroid is same as the current, so we will destroy both and set the current asteroid as 0
                if (last == (-asteroid)) {
                    asteroid = 0;
                } else if (last > (-asteroid)) {
                    // the last is bigger than the current then we will destroy the current and assign the last to current
                    asteroid = last;
                }
            }
            // not equal to 0 means it's a valid asteroid
            if (asteroid != 0)
                st.push(asteroid);
        }
        // adding to the ans
        int n = st.size();
        int[] ans = new int[n];
        while (!st.isEmpty()) ans[--n] = st.pop();
        return ans;
    }
}
