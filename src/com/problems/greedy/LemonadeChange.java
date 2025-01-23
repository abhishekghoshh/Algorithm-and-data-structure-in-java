package com.problems.greedy;

/*
 *
 * problem links :
 * https://leetcode.com/problems/lemonade-change/description/
 * https://www.codingninjas.com/studio/problems/lemonade-change_8224112
 *
 * Solution video :
 *
 * */
public class LemonadeChange {
    public static void main(String[] args) {
        type1();
    }

    private static void type1() {
        int[] bills = {5, 5, 10, 10, 20};
        boolean lemonadeChange = lemonadeChange(bills);
        System.out.println(lemonadeChange);
    }

    // the price is 5
    // we will get 5, 10, 20
    // so, we have to give the return in terms of 5 and 10
    // because the change can be 0, 5, 15
    // if the customer is giving 5 then we dont have to do anything
    // if the customer is giving 10 then we have to return back 5
    // we have to check that do we have at least one 5 or not to give the 5 return
    // if the customer is giving us 20 then we have to give 15
    // 15 can be made either with 5+10 or 5+5+5
    // so we have to give the return accordingly
    public static boolean lemonadeChange(int[] bills) {
        int fives = 0, tens = 0;
        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                if (fives == 0) return false;
                tens++;
                fives--;
            } else {
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else return false;
            }
        }
        return true;
    }


}
