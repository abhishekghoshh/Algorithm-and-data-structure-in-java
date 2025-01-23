package com.problems.array;

/*
 * Problem link :
 * https://www.naukri.com/code360/problems/linear-search_6922070
 *
 * Solution is :
 * https://www.youtube.com/watch?v=wvcQg43_V8U&t=1614s
 *
 * https://takeuforward.org/data-structure/linear-search-in-c/
 * */
public class LinearSearch {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] arr = {1, 2, 3, 4, 5};
        int num = 4;
        int n = arr.length;
        int val = search(arr, n, num);
        System.out.println(val);
    }

    private static int search(int[] arr, int n, int num) {
        for (int i = 0; i < n; i++)
            if (arr[i] == num)
                return i;
        return -1;
    }
}
