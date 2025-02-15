package com.problems.binarysearch;
/*
 * Problem link :
 * 
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=W3-KgsCVH1U&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=9
 * 
 * */
public class SearchingInNearlySortedArray {
    public static void main(String[] args) {
        type2();
    }

	private static void type2() {
        int[] arr = {10, 3, 40, 20, 50, 80, 70};
        int key = 3;
        int index = search(arr, key);
        System.out.println(index);
	}

    private static int search(int[] arr, int key) {
        int n = arr.length;
        int low = 0, high = n - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == key) return mid;
            else if (mid - 1 >= 0 && arr[mid - 1] == key) return mid - 1;
            else if (mid + 1 <= n - 1 && arr[mid + 1] == key) return mid + 1;
            else if (key < arr[mid]) high = mid - 2;
            else low = mid + 2;
        }
        return -1;
    }
}
