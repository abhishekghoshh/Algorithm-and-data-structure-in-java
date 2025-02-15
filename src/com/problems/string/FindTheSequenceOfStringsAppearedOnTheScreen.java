package com.problems.string;

import java.util.ArrayList;
import java.util.List;

/*
 * Problem link:
 * https://leetcode.com/problems/find-the-sequence-of-strings-appeared-on-the-screen/description/
 *
 *
 * */
public class FindTheSequenceOfStringsAppearedOnTheScreen {
    public static void main(String[] args) {
        type1();
    }

    // optimized approach
    private static void type1() {
        String target = "abc";
        List<String> ans = stringSequence1(target);
        System.out.println(ans);
    }

    public static List<String> stringSequence1(String target) {
        List<String> answer = new ArrayList<>();
        StringBuilder bucket = new StringBuilder();
        for (char ch : target.toCharArray()) {
            // we will start with 'a' and go till 'ch'
            for (char c = 'a'; c <= ch; c++) {
                answer.add(bucket.toString() + c);
            }
            // once it is reached 'ch' we will go to the next letter
            bucket.append(ch);
        }
        return answer;
    }
}
