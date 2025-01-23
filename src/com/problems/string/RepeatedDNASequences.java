package com.problems.string;

import java.util.*;

/*
 * Problem link :
 * https://leetcode.com/problems/repeated-dna-sequences/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=FzTYfsmtOso
 *
 * https://neetcode.io/solutions/repeated-dna-sequences
 */
public class RepeatedDNASequences {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
    }

    // most optimized approach
    // using 2 hashsets and with a char to int conversation
    private static void type3() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> ans = findRepeatedDnaSequences3(s);
        System.out.println(ans);
    }

    private static List<String> findRepeatedDnaSequences3(String s) {
        int n = s.length();
        if (n < 10) return List.of();

        Set<Long> seen = new HashSet<>();
        Set<Long> res = new HashSet<>();

        long sum = 0;
        long base = (long) 1e9;
        char[] arr = s.toCharArray();
        for (int i = 0; i < 10; i++)
            sum = (sum * 10) + convert(arr[i]);
        seen.add(sum);
        for (int i = 10; i < n; i++) {
            sum = sum - convert(arr[i - 10]) * base;
            sum = (sum * 10) + convert(arr[i]);
            if (!seen.add(sum)) {
                res.add(sum);
            }
        }
        List<String> ans = new ArrayList<>();
        for (long num : res) {
            char[] item = new char[10];
            for (int i = 9; i >= 0; i--) {
                item[i] = convert(num % 10);
                num = num / 10;
            }
            ans.add(new String(item));
        }
        return ans;
    }

    private static long convert(char c) {
        return switch (c) {
            case 'A' -> 1;
            case 'C' -> 2;
            case 'G' -> 3;
            default -> 4;
        };
    }

    private static char convert(long i) {
        return switch ((int) i) {
            case 1 -> 'A';
            case 2 -> 'C';
            case 3 -> 'G';
            default -> 'T';
        };
    }

    // optimized approach
    // using 2 hashsets
    private static void type2() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> ans = findRepeatedDnaSequences2(s);
        System.out.println(ans);
    }

    public static List<String> findRepeatedDnaSequences2(String s) {
        int n = s.length();
        if (n < 10) return List.of();

        Set<String> seen = new HashSet<>();
        Set<String> res = new HashSet<>();

        for (int i = 0; i + 9 < n; i++) {
            String cur = s.substring(i, i + 10);
            if (seen.contains(cur)) {
                res.add(cur);
            }
            seen.add(cur);
        }
        return new ArrayList<>(res);
    }

    // brute force using a hashmap
    private static void type1() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> ans = findRepeatedDnaSequences1(s);
        System.out.println(ans);
    }

    public static List<String> findRepeatedDnaSequences1(String s) {
        int n = s.length();
        if (n < 10) return List.of();
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i + 9 < n; i++) {
            String key = s.substring(i, i + 10);
            freq.put(key, 1 + freq.getOrDefault(key, 0));
        }
        List<String> ans = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            if (entry.getValue() >= 2) ans.add(entry.getKey());
        }
        return ans;
    }
}
