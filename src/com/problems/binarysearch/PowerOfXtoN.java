package com.problems.binarysearch;

/*
 * Problem link :
 * https://leetcode.com/problems/powx-n/
 * https://www.codingninjas.com/codestudio/problems/1082146
 * https://www.codingninjas.com/studio/problems/power-of-numbers_8157729
 * https://www.codingninjas.com/studio/problems/find-x-raised-to-power-n-_626560
 *
 * Solution link :
 * https://www.youtube.com/watch?v=l0YC3876qxg&list=PLgUwDviBIf0rPG3Ictpu74YWBQ1CaBkm2&index=16
 *
 * https://takeuforward.org/data-structure/implement-powxn-x-raised-to-the-power-n/
 * */
public class PowerOfXtoN {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
	}

	// using recursion and binary search
	// exactly the same as previous
	private static void type3() {
		double x = 2;
		int n = -2147483648;
		double answer = myPow3(x, n);
		System.out.printf("pow(%f,%d) is %f%n", x, n, answer);
	}


	public static double myPow3(double x, int n) {
		if (x == 1) return 1;
		else if (x == 0) return 0;
		else if (n == 0) return 1;
		else if (n == 1) return x;
		// we have to convert it to long because 2147483648 is greater that INT_MAX
		long pow = n;
		boolean isPositivePower = pow > 0;
		pow = pow < 0 ? -pow : pow;
		double result = pow3(1, x, pow);
		return isPositivePower ? result : (1.0 / result);
	}

	private static double pow3(double result, double x, long n) {
		System.out.println(n);
		if (n == 0) return result;
		if (n % 2 == 0) return pow3(result, (x * x), n / 2);
		else return pow3((result * x), x, n - 1);
	}

	// binary search approach
	// we are dividing the power by 2 if its even and multiplying the number by
	// itself at the same time, given that 3^6 is 9^3
	// if the power is 15. we will divide the power by 2 everytime
	// we get even power else we will make it even by subtracting 1
	// 15 will be 14, see the solution, and you will find it very easy
	private static void type2() {
		double x = 2;
		int n = -2147483648;
		double answer = pow(x, n);
		System.out.printf("pow(%f,%d) is %f%n", x, n, answer);
	}

	private static double pow(double x, int n) {
		if (x == 1) return 1;
		else if (x == 0) return 0;
		else if (n == 0) return 1;
		else if (n == 1) return x;
		else {
			long pow = n;
			double answer = 1.0;
			pow = n < 0 ? ((-1) * pow) : pow;
			while (pow > 0) {
				if (pow % 2 == 0) {
					pow = pow / 2;
					x = x * x;
				} else {
					answer = answer * x;
					pow--;
				}
			}
			return n > 0 ? answer : (1.0 / answer);
		}
	}

	// brute force approach
	// multiplies x to itself n times
	private static void type1() {
		double x = 2;
		int n = 10;
		double answer = 1;
		for (int i = 0; i < n; i++) answer = answer * x;
		System.out.printf("pow(%f,%d) is %f%n", x, n, answer);
	}

}
