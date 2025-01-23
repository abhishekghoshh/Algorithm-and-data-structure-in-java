package com.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ArrayUtil {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void swap(int[] nums1, int[] nums2, int i1, int i2) {
        int temp = nums1[i1];
        nums1[i1] = nums2[i2];
        nums2[i2] = temp;
    }

    public static int maxN(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int item : arr) if (item > max) max = item;
        return max;
    }
    public static int max(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int item : arr) max = Math.max(item, max);
        return max;
    }

    public static int minN(int... arr) {
        int min = Integer.MAX_VALUE;
        for (int item : arr) if (item < min) min = item;
        return min;
    }

    public static int min(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int item : arr) min = Math.min(item, min);
        return min;
    }

    public static void copy(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length && i < array2.length; i++) array1[i] = array2[i];
    }

    public static int[] copy(int[] array) {
        int[] copy = new int[array.length];
        copy(copy, array);
        return copy;
    }

    public static Stack<Integer> stack(int... arr) {
        Stack<Integer> stack = new Stack<>();
        for (int item : arr) stack.push(item);
        return stack;
    }

    @SafeVarargs
    public static <T> List<T> list(T... arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }

}
