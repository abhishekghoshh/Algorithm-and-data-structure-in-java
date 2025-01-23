package com.problems.hashing;

/*
 *
 * problem links :
 * https://leetcode.com/problems/path-crossing/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=VWRJBNP7uH8
 *
 * https://neetcode.io/solutions/path-crossing
 * */

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// Tags : Array, Hashing
public class PathCrossing {
    public static void main(String[] args) {
        type1();
        type2();
    }


    // this is an optimized approach
    // same as previous but here we will not use the custom class for the hashCode
    // here we will calculate the hash code for (x,y) and store it in the Set of integer
    // abd check if the hash is already present in the set or not
    private static void type2() {
        String path = "NES";
        boolean ans = isPathCrossing2(path);
        System.out.println(ans);
    }

    static boolean isPathCrossing2(String path) {
        Set<Integer> set = new HashSet<>();
        // we will start (0,0)
        int x = 0, y = 0;
        set.add(hash(x, y));
        for (char d : path.toCharArray()) {
            // and depending on the direction we will increment or decrement the value of x or y
            if (d == 'E') {
                x++;
            } else if (d == 'W') {
                x--;
            } else if (d == 'N') {
                y++;
            } else {
                y--;
            }
            int hash = hash(x, y);
            if (!set.add(hash)) return true;
        }
        return false;
    }

    static int hash(int x, int y) {
        int prime1 = 31;
        int prime2 = 37;
        return prime1 * x + prime2 * y;
    }

    // todo this should come at first to your mind
    // we will start with (0,0) and then we go to each direction one by one
    // increment and decrement x and y coordinate accordingly
    // we will add the coordinates into a set of Coordinates
    private static void type1() {
        String path = "NES";
        boolean ans = isPathCrossing1(path);
        System.out.println(ans);
    }

    private static boolean isPathCrossing1(String path) {
        Set<Pair> set = new HashSet<>();
        // (0,0) is the starting point
        Pair curr = new Pair(0, 0);
        set.add(curr);
        for (char d : path.toCharArray()) {
            // based on the direction we are choosing dx and dy
            int dx = 0, dy = 0;
            if (d == 'E') {
                dx++;
            } else if (d == 'W') {
                dx--;
            } else if (d == 'N') {
                dy++;
            } else {
                dy--;
            }
            curr = curr.clone(dx, dy);
            if (!set.add(curr)) return true;
        }
        return false;
    }

    static class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair clone(int dx, int dy) {
            return new Pair(x + dx, y + dy);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
