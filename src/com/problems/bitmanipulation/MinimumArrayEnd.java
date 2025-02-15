package com.problems.bitmanipulation;

/*
 * Problem link :
 * https://leetcode.com/problems/minimum-array-end/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=4pP-0UpEok4
 * https://neetcode.io/solutions/minimum-array-end
 *
 * https://leetcode.com/problems/minimum-array-end/solutions/6007257/minimum-array-end/
 */
public class MinimumArrayEnd {


    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }


    // todo same as previous with little optimization
    private static void type4() {
        int n = 3, x = 4;
        long ans = minEnd4(n, x);
        System.out.println(ans);
    }

    private static long minEnd4(int n, int x) {
        long result = x;
        long mask;
        n--; // Reducing n by 1 to exclude x from the iteration

        // Step 1: Iterate over each bit position with mask starting at 1 and shifting left
        for (mask = 1; n > 0; mask <<= 1) {
            // Step 2: If the corresponding bit in x is 0
            if ((mask & x) == 0) {
                // Set the bit in the result based on the least significant bit of n
                result |= (n & 1) * mask;
                // Shift n to the right by 1 to process the next bit
                n >>= 1;
            }
        }

        return result;
    }

    //  todo please check the previous approach
    //   one possible optimization we could do is we can club all the loop in one loop
    //   so we could reduce the time complexity and remove the array
    private static void type3() {
        int n = 3, x = 4;
        long ans = minEnd3(n, x);
        System.out.println(ans);
    }

    private static long minEnd3(int n, int x) {
        long ans = 0L;
        int last = n - 1;
        for (int i = 0; x != 0 || last != 0; i++) {
            int bit = (x & 1);
            x = x >> 1;
            if (bit == 1) {
                ans += (1L << i);
            } else {
                long bit1 = last & 1;
                ans += (bit1 << i);
                last = last >> 1;
            }
        }
        return ans;
    }

    // todo optimized approach
    //  it
    private static void type2() {
        int n = 3, x = 4;
        long ans = minEnd2(n, x);
        System.out.println(ans);
    }

    public static long minEnd2(int n, int x) {
        int N = 63;
        int[] num = new int[N];
        for (int n1 = x, i = 0; n1 != 0; i++) {
            num[N - 1 - i] = (n1 & 1);
            n1 = n1 >> 1;
        }
        for (int last = n - 1, i = N - 1; i >= 0 && last != 0; i--) {
            if (num[i] == 1) continue;
            int bit = last & 1;
            num[i] = bit;
            last = last >> 1;
        }
        long ans = 0L;
        for (int i = N - 1, j = 0; i >= 0; i--, j++) {
            int bit = num[i];
            ans += ((long) bit << j);
        }
        return ans;
    }

    private static void type1() {
    }


}
