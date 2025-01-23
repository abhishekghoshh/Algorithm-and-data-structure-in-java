package com.problems.string;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *
 * problem links :
 * https://leetcode.com/problems/destination-city/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=Hi8vMnnTZHE
 *
 * https://neetcode.io/solutions/destination-city
 * */

// Tags : String, Hashing
public class DestinationCity {
    public static void main(String[] args) {
        type1();
    }

    // this is a very simple problem of hashing
    // first put all the paths in a map
    // then for every destination city check if it is a key for the map or not
    // if not then return the city
    private static void type1() {
        List<List<String>> paths = List.of();
        String ans = destCity(paths);
        System.out.println(ans);
    }

    static String destCity(List<List<String>> paths) {
        Map<String, String> edges = new HashMap<>();
        // add in the map
        for (List<String> path : paths) {
            String src = path.get(0);
            String dest = path.get(1);
            edges.put(src, dest);
        }
        // check if the city is not a key to the map
        for (List<String> path : paths) {
            String dest = path.get(1);
            if (!edges.containsKey(dest)) return dest;
        }
        return null;
    }
}
