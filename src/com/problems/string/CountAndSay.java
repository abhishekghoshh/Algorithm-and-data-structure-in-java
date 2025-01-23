package com.problems.string;
/*
 * Problem link :
 * https://leetcode.com/problems/count-and-say/
 * https://www.codingninjas.com/codestudio/problems/1090543
 * https://www.codingninjas.com/studio/problems/look-and-say-sequence_668478
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=9fI_26Dl1IA
 * 
 */

public class CountAndSay {

	// TODO check the solution
	public static void main(String[] args) {
		type1();
		type2();
	}

	private static void type2() {
		int n = 30;
		String s = "1";
//		if(n==1) return s;
		for (int i = 1; i < n; i++) {
			s = nextAnswer(s);
		}
		System.out.println(s);
	}

	private static String nextAnswer(String answer) {
		StringBuilder sb = new StringBuilder();
		char prev = answer.charAt(0);
		int count = 1;
		for (int i = 1; i < answer.length(); i++) {
			if (prev == answer.charAt(i)) {
				count++;
			} else {
				sb.append(count);
				sb.append(prev);

				prev = answer.charAt(i);
				count = 1;
			}
		}
		sb.append(count);
		sb.append(prev);

		return sb.toString();
	}
	private static void type1() {
		int n = 30;
		String answer = countAndSay(n);
		System.out.println(answer);
	}

	private static String countAndSay(int n) {
		if (n == 1) return "1";
		String prev = countAndSay(n - 1);
		char[] arr = prev.toCharArray();
		StringBuilder sb = new StringBuilder();
		int currentNum = toInt(arr, 0);
		int count = 1;
		for (int i = 1; i < arr.length; i++) {
			if (toInt(arr, i) != currentNum) {
				sb.append(count).append(currentNum);
				currentNum = toInt(arr, i);
				count = 1;
			} else {
				count++;
			}
		}
		// for the last iteration
		sb.append(count).append(currentNum);
		return sb.toString();
	}

	private static int toInt(char[] arr, int i) {
		return arr[i] - '0';
	}

}
