package com.problems.logicbuilding;

/*
 * Problem link :
 * https://leetcode.com/problems/check-if-two-chessboard-squares-have-the-same-color/
 *
 * Solution link :
 *
 */
public class CheckTwoChessboards {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        String coordinate1 = "a1", coordinate2 = "c3";
        boolean ans = checkTwoChessboards(coordinate1, coordinate2);
        System.out.println(ans);
    }

    // seeing the image in the question, we can see one thing that the longest diagonal is black.
    // and for all parallel cells, one is white, then again the next one is black.
    // if somehow, we can find the absolute distance from that axis, then our work is finished
    // if we transform 'a' to 0 and '1' to 0, then
    // the axis will be on (0,0), (1,1), (2,2) ....
    // let's say the point is (3,8) so we have to take the distance from (3,3)
    // for (9,5) we have to take the distance from (5,5)
    // so ultimately we have to take either x-y or y-x or Math.abs(x-y)
    // if they are both either even or edd distance cell from the axis then they have the same color
    public static boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        // transforming
        int x1 = coordinate1.charAt(0) - 'a';
        int y1 = coordinate1.charAt(1) - '1';
        int x2 = coordinate2.charAt(0) - 'a';
        int y2 = coordinate2.charAt(1) - '1';
        // checking absolute distance is even distance or odd
        return Math.abs((x1 - y1) % 2) == Math.abs((x2 - y2) % 2);
    }
}
