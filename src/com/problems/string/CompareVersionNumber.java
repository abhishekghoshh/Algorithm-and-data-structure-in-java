package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/compare-version-numbers/
 * https://www.codingninjas.com/codestudio/problems/1062582
 * 
 * Solution link :
 * 
 * 
 */

public class CompareVersionNumber {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// same as the previous one
	// just here we are using character array instead of string
	private static void type2() {
		System.out.println(compareVersion2("1.01", "1.001"));
	}

	private static int compareVersion2(String version1, String version2) {
		char[] arr1 = version1.toCharArray();
		char[] arr2 = version2.toCharArray();
		int i1 = 0, i2 = 0;
		int n1 = arr1.length, n2 = arr2.length;
		int v1, v2;
		while (i1 < n1 || i2 < n2) {
			v1 = v2 = 0;
			// calculating the first number
			while (i1 < n1 && arr1[i1] != '.') v1 = v1 * 10 + (arr1[i1++] - '0');
			// skipping the dot(.) character if it does not hit the length
			if (i1 < n1) i1++;
			// calculating the second number
			while (i2 < n2 && arr2[i2] != '.') v2 = v2 * 10 + (arr2[i2++] - '0');
			// skipping the dot(.) character if it does not hit the length
			if (i2 < n2) i2++;
			// checking the conditions
			if (v1 > v2) return 1;
			if (v1 < v2) return -1;
		}
		return 0;
	}

	// optimized approach
	// splitting using regex with built method
	private static void type1() {
		System.out.println(compareVersion1("1.01", "1.001"));
	}

	public static int compareVersion1(String version1, String version2) {
		String[] arr1 = version1.split("[.]"), arr2 = version2.split("[.]");
		int i = 0, n1 = arr1.length, n2 = arr2.length;
		int v1, v2;
		while (i < n1 || i < n2) {
			v1 = i < n1 ? Integer.parseInt(arr1[i]) : 0;
			v2 = i < n2 ? Integer.parseInt(arr2[i]) : 0;
			if (v1 > v2) return 1;
			else if (v1 < v2) return -1;
			else i++;
		}
		return 0;
	}
}
