package com.problems.recursion;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem links:
 * https://leetcode.com/problems/letter-case-permutation/
 * 
 * Solution link
 * https://www.youtube.com/watch?v=J2Er5XceU_I&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=15
 * https://www.youtube.com/watch?v=4eOPYDOiwFo&list=PL_z_8CaSLPWeT1ffjiImo0sYTcnLzo-wY&index=16
 * */
public class LetterCasePermutation {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// If it is a letter, in place changes
	// Here, we have two choices either to use a lower case or upper
	// here we are directly changing the character case
	private static void type2() {
		String s = "a1b2";
		List<String> answer = new ArrayList<>();
		// we are just making the string to char array
		// so the char array will have all the elements
		// we don't have to add anything later
		permutation2(0, s.toCharArray(), answer);
		System.out.println(answer);
	}

	private static void permutation2(int i, char[] bucket, List<String> answer) {
		if (i == bucket.length) {
			answer.add(new String(bucket));
			return;
		}
		if (Character.isLetter(bucket[i])) {
			// first, we will consider the lower case
			// so changing the case to lower
			// whatever the case was previously
			bucket[i] = Character.toLowerCase(bucket[i]);
			permutation2(i + 1, bucket, answer);
			// now will change the case to upper
			bucket[i] = Character.toUpperCase(bucket[i]);
			permutation2(i + 1, bucket, answer);
		} else {
			// as this is a digit, so we don't have to add anything
			permutation2(i + 1, bucket, answer);
		}
	}

	// If it is a letter
	// Here, we have two choices either to use a lower case or upper
	// we are using a bucket to store the previous results
	private static void type1() {
		String s = "a1b2";
		List<String> answer = new ArrayList<>();
		StringBuilder bucket = new StringBuilder();
		permutation1(s, 0, bucket, answer);
		System.out.println(answer);
	}

	private static void permutation1(String s, int i, StringBuilder bucket, List<String> answer) {
		if (i == s.length()) {
			answer.add(bucket.toString());
			return;
		}
		char ch = s.charAt(i);
		// if the character is a letter, then only we can change its case
		if (Character.isLetter(ch)) {
			// first, we will consider the lower case, and after the computation, we will remove it
			bucket.append(Character.toLowerCase(ch));
			permutation1(s, i + 1, bucket, answer);
			bucket.deleteCharAt(bucket.length() - 1);

			// now will use the upper case, and after the computation, we will remove it
			bucket.append(Character.toUpperCase(ch));
			permutation1(s, i + 1, bucket, answer);
			bucket.deleteCharAt(bucket.length() - 1);

		} else {
			// if its index then we don't have anything to do, just add it and increase the index
			bucket.append(ch);
			permutation1(s, i + 1, bucket, answer);
			bucket.deleteCharAt(bucket.length() - 1);
		}
	}

}
