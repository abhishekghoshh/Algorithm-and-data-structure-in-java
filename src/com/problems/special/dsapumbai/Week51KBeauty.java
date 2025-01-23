package com.problems.special.dsapumbai;

public class Week51KBeauty {
    /*
     * The k-beauty of an integer num is defined as the number of substrings of num
     * when it is read as a string that meets the following conditions:
     * It has a length of k.
     * It is a divisor of num.
     * Given integers num and k, return the k-beauty of num.
     * Leading zeros are allowed.
     * 0 is not a divisor of any value.
     * */
    public static void main(String[] args) {
        int num = 43043430;
        int k = 3;

        int kBeauty = divisorSubStrings(num, k);

        System.out.println("Kbeauty of given number " + kBeauty);
    }

    public static int divisorSubStrings(int num, int k) {
        char[] arr = toCharArray(num);
        int n = arr.length;
        if (n < k) return 0;
        int sum = 0, power = 1, ans = 0;
        // initializing the first window
        for (int i = 0; i < k; i++) {
            power *= 10;
            sum = sum * 10 + (arr[i] - '0');
        }
        // adjusting the power
        power = power / 10;
        // checking for the first window
        if (sum != 0 && num % sum == 0) ans++;
        // sliding window and checking for the remaining digits
        for (int i = k; i < n; i++) {
            // adjusting the window
            sum -= (arr[i - k] - '0') * power;
            sum = sum * 10 + (arr[i] - '0');
            // checking the condition for the current window
            if (sum != 0 && num % sum == 0) ans++;
        }
        return ans;
    }

    private static char[] toCharArray(int num) {
        int n = 0;
        int dummy = num;
        while (dummy > 0) {
            dummy = dummy / 10;
            n++;
        }
        char[] arr = new char[n];
        while (n-- > 0) {
            arr[n] = (char) ('0' + num % 10);
            num = num / 10;
        }
        return arr;
    }
}
