package com.problems.array;

import static com.util.PrintUtl.print;
/*
 *
 * problem links :
 * https://www.codingninjas.com/studio/problems/left-rotate-an-array-by-one_5026278
 *
 * Solution link :
 * https://www.youtube.com/watch?v=wvcQg43_V8U&t=61s
 *
 * https://takeuforward.org/data-structure/left-rotate-the-array-by-one/
 * */
public class LeftRotateByOne {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] arr = {1, 2, 3, 4, 5};
        int first = arr[0];
        for (int i = 1; i < arr.length; i++)
            arr[i - 1] = arr[i];
        arr[arr.length - 1] = first;
        print(arr);
    }
}
