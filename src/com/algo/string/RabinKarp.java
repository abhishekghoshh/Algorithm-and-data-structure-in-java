package com.algo.string;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link :
 * https://practice.geeksforgeeks.org/problems/31272eef104840f7430ad9fd1d43b434a4b9596b/1
 * https://leetcode.com/problems/repeated-string-match/
 * https://www.codingninjas.com/codestudio/problems/1115738
 * 
 * Solution link :
 * https://www.youtube.com/watch?v=qQ8vS2btsxI
 * https://www.youtube.com/watch?v=BQ9E-2umSWc
 * https://www.youtube.com/watch?v=oxd_Z1osgCk
 * 
 * https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
 * https://leetcode.com/problems/repeated-string-match/solutions/416144/Rabin-Karp-algorithm-C++-implementation/
 * 
 */
public class RabinKarp {

	public static void main(String[] args) {
		type1();
		type2();
	}

	// Rabin karp algorithm
	// used hashing
	private static void type2() {
		// size of the all characters
		final int size = 256;
		// A prime number
		final int prime = 101;

		String string = "AABAACAADAABAABA";
		String pattern = "AABA";
		char[] sArr = string.toCharArray();
		char[] pArr = pattern.toCharArray();
		int n1 = sArr.length;
		int n2 = pArr.length;

		int patternHash = 0; // hash value for the pattern
		int textHash = 0; // hash value for txt
		int highestPower = 1;
		List<Integer> answer = new ArrayList<>();
		// The value of h would be "pow(d, M-1)%q"
		for (int i = 0; i < n2 - 1; i++) highestPower = (highestPower * size) % prime;

		// Calculate the hash value of the pattern and first window of the string
		for (int i = 0; i < n2; i++) {
			textHash = (size * textHash + sArr[i]) % prime;
			patternHash = (size * patternHash + pArr[i]) % prime;
		}
		// Slide the pattern over text one by one
		for (int i = 0; i <= n1 - n2; i++) {
			// Check the hash values of the current window of the text and pattern. If the hash
			// values match, then only check for characters one by one
			if (patternHash == textHash) {
				// Check for characters one by one if hash values are same
				int j = 0;
				while (j < n2) {
					if (sArr[i + j] != pArr[j]) break;
					j++;
				}
				if (j == n2) answer.add(i);
			}
			// Calculate hash value for the next window of the text:
			if (i < n1 - n2) {
				// size* prevHash - previous character hash + new character hash
				textHash = (size * textHash - size * highestPower * sArr[i] + sArr[i + n2]) % prime;
				// We might get negative value of t, converting it to positive
				textHash = textHash >= 0 ? textHash : (textHash + prime);
			}
		}
		if (answer.isEmpty()) answer.add(-1);
		System.out.println(answer);
	}

	// Brute force of string matching
	private static void type1() {
		String str = "AABAACAADAABAABA";
		String pat = "AABA";
		char[] s = str.toCharArray();
		char[] p = pat.toCharArray();
		int n1 = s.length, n2 = p.length;
		List<Integer> answer = new ArrayList<>();
		for (int i = 0; i < n1 - n2 + 1; i++) {
			int j = 0;
			while (j < n2) {
				if (s[i + j] != p[j]) break;
				j++;
			}
			if (j == n2) answer.add(i);
		}
		System.out.println(answer);
	}

}
