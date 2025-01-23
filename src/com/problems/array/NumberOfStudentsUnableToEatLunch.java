package com.problems.array;

/*
 *
 * problem links :
 * https://leetcode.com/problems/number-of-students-unable-to-eat-lunch/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=d_cvtFwnOZg
 *
 * https://neetcode.io/solutions/number-of-students-unable-to-eat-lunch
 * */
public class NumberOfStudentsUnableToEatLunch {
    public static void main(String[] args) {
        type1();
        type2();
    }

    // optimized approach
    // todo try to come up with this in interview directly
    // we will count student count for 0th and 1th sandwiches
    // after counting we will go to the sandwiches array and try to give the sandwiches to each student
    // we will iterate till, there is 0th sandwich at top and and there no student0 or if 1th sandwich at the top
    // and there is no 1th student
    private static void type2() {
        int[] students = {1, 1, 0, 0};
        int[] sandwiches = {0, 1, 0, 1};
        int ans = countStudents(students, sandwiches);
        System.out.println(ans);
    }

    public static int countStudents(int[] students, int[] sandwiches) {
        int n = students.length;
        int s1 = 0;
        // as student array is either 0 or 1
        // so if we sum the student array then we will find
        // the number of students choosing for 1st sandwich
        for (int student : students) {
            s1 += student;
        }
        // to find student count with 0th sandwich, we will just subtract s1 from n
        int s0 = n - s1;
        // now we will use a top variable to go to all the sandwiches one by one
        // find if there is any student available or not
        int top = 0;
        while (top < n) {
            int sandwich = sandwiches[top];
            if (sandwich == 0) {
                // the current sandwich is 0 but there is no student to take that so we will break
                if (s0 == 0) break;
                s0--;
            } else {
                // the current sandwich is 1 but there is no student to take that so we will break
                if (s1 == 0) break;
                s1--;
            }
            top++;
        }
        // (n-top) will be the count of the remaining sandwiches which is same as the remaining students
        return (n - top);
    }

    // brute force approach
    private static void type1() {
    }
}
