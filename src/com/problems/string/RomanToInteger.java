package com.problems.string;

/*
 * Problem link : 
 * https://leetcode.com/problems/roman-to-integer/
 * https://www.codingninjas.com/codestudio/problems/981308
 * 
 * Solution link:
 * https://www.youtube.com/watch?v=dlATMslQ6Uc
 * https://www.youtube.com/watch?v=3jdxYj3DD98
 * */
public class RomanToInteger {

	public static void main(String[] args) {
		type1();
		type2();
		type3();
		type4();
		type5();
	}

	private static void type5() {
		String s = "MCMXCIV";
		char[] c = s.toCharArray();
		int[] n = new int[c.length];
		for (int i = 0; i < n.length; i++)
			n[i] = get(c[i]);
		int sum = 0;
		for (int i = 0; i < n.length; i++)
			sum = i == c.length - 1 || n[i] >= n[i + 1] ? sum + n[i] : sum - n[i];
		System.out.println(sum);
	}

	private static void type4() {
		String s = "MCMXCIV";
		int ans = 0, num = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			num = get(s.charAt(i));
			if (4 * num < ans) ans -= num;
			else ans += num;
		}
		System.out.println(ans);
	}

	private static void type3() {
		String s = "MCMXCIV";
		int n = s.length();
		int ans = 0;
		int prev = 0;
		int sum = 0;
		for (int i = n - 1; i >= 0; i--) {
			ans = get(s.charAt(i));
			if (ans < prev) sum = sum - ans;
			else sum = sum + ans;
			prev = ans;
		}
		System.out.println(sum);
	}

	private static void type2() {
		String s = "MCMXCIV";
		char[] arr = s.toCharArray();
		int n = arr.length;
		int result = get(arr[n - 1]);
		for (int i = n - 2; i >= 0; i--) {
			// if the number is IV
			// so result = V - I
			// if the number is VI
			// so result = V + I
			int current = get(arr[i]);
			int previous = get(arr[i + 1]);
			if (current < previous) {
				result -= current;
			} else {
				// greater than equal to
				result += current;
			}
		}
		System.out.println(result);
	}


	private static void type1() {
		String s = "MCMXCIV";
		char[] arr = s.toCharArray();
		int n = arr.length;
		int seed = get(arr[n - 1]);
		for (int i = n - 2; i >= 0; i--) {
			// if the number is IV
			// so result = V - I
			if (get(arr[i]) < get(arr[i + 1]))
				seed -= get(arr[i]);
			else
				seed += get(arr[i]);
		}
		System.out.println(seed);
	}

	private static int get(char ch) {
		return switch (ch) {
			case 'I' -> 1;
			case 'V' -> 5;
			case 'X' -> 10;
			case 'L' -> 50;
			case 'C' -> 100;
			case 'D' -> 500;
			case 'M' -> 1000;
			default -> 0;
		};
	}
}
