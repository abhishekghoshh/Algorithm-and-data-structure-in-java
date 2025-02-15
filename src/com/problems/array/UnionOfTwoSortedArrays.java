package com.problems.array;

import com.util.PrintUtl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/sorted-array_6613259
 *
 * Solution is :
 * https://www.youtube.com/watch?v=wvcQg43_V8U&t=2584s
 *
 * https://takeuforward.org/data-structure/union-of-two-sorted-arrays/
 * */
public class UnionOfTwoSortedArrays {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // time complexity O(2(m+n))
    private static void type2() {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 7};
        List<Integer> answer = new ArrayList<>();
        int n1 = arr1.length, n2 = arr2.length, i1 = 0, i2 = 0;
        while (i1 < n1 && i2 < n2) {
            int item = arr1[i1] < arr2[i2] ? arr1[i1++] : arr2[i2++];
            if (answer.isEmpty() || answer.get(answer.size() - 1) != item)
                answer.add(item);
        }
        while (i1 < n1) {
            int item = arr1[i1++];
            if (answer.isEmpty() || answer.get(answer.size() - 1) != item)
                answer.add(item);
        }
        while (i2 < n2) {
            int item = arr2[i2++];
            if (answer.isEmpty() || answer.get(answer.size() - 1) != item)
                answer.add(item);
        }
        PrintUtl.print(answer);
    }

    // time complexity O((m+n)log(m+n)) + (m+n))
    private static void type1() {
        int[] arr1 = {1, 3, 4, 5};
        int[] arr2 = {2, 4, 7};
        Set<Integer> set = new TreeSet<>();
        for (int item : arr1)
            set.add(item);
        for (int item : arr2)
            set.add(item);
        List<Integer> answer = new ArrayList<>(set);
        PrintUtl.print(answer);
    }
}
