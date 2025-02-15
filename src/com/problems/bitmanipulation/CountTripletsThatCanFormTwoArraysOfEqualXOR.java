package com.problems.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=e4Yx9KjqzQ8
 * https://neetcode.io/solutions/count-triplets-that-can-form-two-arrays-of-equal-xor
 *
 * https://www.youtube.com/watch?v=IAcO4Wyr2ak
 */
public class CountTripletsThatCanFormTwoArraysOfEqualXOR {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // todo most optimized approach
    //  time complexity O(n * log(n))
    //  log(n) for the map operations
    //  heavily inspired by the previous approach
    //  if we see how we are calculating the ans => count += (k - i);
    //  so for any k there can be can be some i at i1 or i2 or i3
    //  which will be contributing to the ans like 3k -i1 -i2 -i3
    //  so we need to find the count of i1, i2, i3 and total index count
    private static void type4() {
        int[] arr = {2, 3, 1, 6, 7};
        int ans = countTriplets4(arr);
        System.out.println(ans);
    }

    private static int countTriplets4(int[] arr) {
        int n = arr.length;
        int count = 0;
        Map<Integer, Integer> indexSumMap = new HashMap<>();
        Map<Integer, Integer> xorCountMap = new HashMap<>();
        xorCountMap.put(0, 1);
        indexSumMap.put(0, 0);
        int xor = 0;
        for (int k = 0; k < n; k++) {
            xor = xor ^ arr[k];
            if (xorCountMap.containsKey(xor)) {
                count += (k * xorCountMap.get(xor) - indexSumMap.get(xor));
            }
            indexSumMap.put(xor, indexSumMap.getOrDefault(xor, 0) + k + 1);
            xorCountMap.put(xor, xorCountMap.getOrDefault(xor, 0) + 1);
        }
        return count;
    }

    // todo let's make it more optimized
    //  time complexity O(n^2)
    //  we could precompute the xor
    //  we could use a property of xor here
    //  for any x, y if x ^ y = 0 then x = y
    //  if x ^ y ^ x = 0 then x ^ y = z and x = y ^ z
    //  we can generalize then if the total xor is 0 and the length is n
    //  so we can say there can be n pairs or equal xor
    private static void type3() {
        int[] arr = {2, 3, 1, 6, 7};
        int ans = countTriplets3(arr);
        System.out.println(ans);
    }

    private static int countTriplets3(int[] arr) {
        int n = arr.length;
        int count = 0;
        // we are taking n+1 because in the iteration we will use prefix[i-1]
        // so for removing that if i > 0 logic we are using n+1 elements
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }
        for (int i = 0; i < n; i++) {
            // from 0 to k xor will be prefix[k] and from 0 to i, xor will be prefix[i]
            // so from i to k xor will be prefix[k] ^ prefix[i-1]
            for (int k = i + 1; k < n; k++) {
                // prefix[i] ^ prefix[k + 1]  == 0 means prefix[i] == prefix[k + 1]
                if (prefix[i] == prefix[k + 1]) {
                    count += (k - i);
                }
            }
        }
        return count;
    }

    // todo little optimized from the brute force approach 
    //  still time complexity is O(n^3)
    //  we need 3 loops atleast to find all the possible triplets and along the way we will calculate the xor
    private static void type2() {
        int[] arr = {2, 3, 1, 6, 7};
        int ans = countTriplets2(arr);
        System.out.println(ans);
    }

    private static int countTriplets2(int[] arr) {
        int n = arr.length;
        int count = 0;
        // 'i' will be from 0 to n-2
        for (int i = 0; i < n - 1; i++) {
            int xor1 = 0;
            // j will be i+1 to n
            for (int j = i + 1; j < n; j++) {
                xor1 ^= arr[j - 1];
                int xor2 = 0;
                // k will be j to n
                for (int k = j; k < n; k++) {
                    xor2 ^= arr[k];
                    if (xor1 == xor2) count++;
                }
            }
        }
        return count;
    }

    // brute force approach
    // time complexity O(n^4)
    private static void type1() {
        int[] arr = {2, 3, 1, 6, 7};
        int ans = countTriplets1(arr);
        System.out.println(ans);
    }

    private static int countTriplets1(int[] arr) {
        int n = arr.length;
        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    int a = 0, b = 0;
                    // finding xor of the elements from i to j
                    for (int idx = i; idx < j; idx++) {
                        a ^= arr[idx];
                    }
                    // finding xor of the elements from j to k
                    for (int idx = j; idx <= k; idx++) {
                        b ^= arr[idx];
                    }
                    // if the xor is the same, then we are increasing the count
                    if (a == b) count++;
                }
            }
        }
        return count;
    }
}
