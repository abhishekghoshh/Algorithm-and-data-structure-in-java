package com.problems.heap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
 *
 * problem links :
 * https://leetcode.com/problems/hand-of-straights/description/
 * https://www.codingninjas.com/studio/problems/groups-in-ninja-land._1459215
 *
 * Solution link :
 * https://www.youtube.com/watch?v=amnrMCVd2YI
 * https://www.youtube.com/watch?v=K7n_BQihPCM
 *
 * */
public class HandsOfStraights {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
        type5();
    }

    // TODO we can do the previous type using linkedList then it will not get any memory error
    // TODO we can also use TreeMap instead of priority queue and hashMap, check nick white youtube video
    // same as previous but here we are giving multiple persons at a time
    // so it is slight optimized from the last type
    private static void type5() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        boolean isPossible = isNStraightHand5(hand, groupSize);
        System.out.println(isPossible);
    }

    private static boolean isNStraightHand5(int[] hand, int groupSize) {
        if (hand.length == 1) return true;
        // it means there cannot be any even distribution
        if (hand.length % groupSize != 0) return false;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int card : hand) {
            if (card > max) max = card;
            if (card < min) min = card;
        }
        int range = max - min + 1;
        // the next line only for leetcode memory error
        if (range > 852884) return false;
        // first, we will count all the card frequency
        int[] freq = new int[range];
        for (int card : hand) freq[card - min]++;
        // we will also count the persons
        int personCount = hand.length / groupSize;

        int start = 0, count;
        // every time we will give one group deck to one person,
        // we will basically decrease the card count
        for (int persons = 0; persons < personCount; ) {
            if (start >= range) return false;
            // initializing the count with frequency with the first card
            count = freq[start];
            // we will give cards to k persons at a time
            persons += count;
            for (int j = 0; j < groupSize; j++) {
                // if the card is out of range or the card frequency is 0
                // then we can say that we cannot give the card
                if (start + j >= range || freq[start + j] < count) return false;
                freq[start + j] -= count;
            }
            while (start < range && freq[start] == 0) start++;
        }
        return true;
    }

    // best approach in leetcode
    // here we will distribute the cards to persons one by one.
    // we will keep track of the frequency of the card
    // then remove the cards from the frequency
    // We can also use linked-list for this approach
    private static void type4() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        boolean isPossible = isNStraightHand(hand, groupSize);
        System.out.println(isPossible);
    }

    public static boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length == 1) return true;
        // it means there cannot be any even distribution
        if (hand.length % groupSize != 0) return false;
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int card : hand) {
            if (card > max) max = card;
            if (card < min) min = card;
        }
        int range = max - min + 1;
        // the next line only for leetcode
        if (range > 852884) return false;
        // first, we will count all the card frequency
        int[] freq = new int[range];
        for (int card : hand) freq[card - min]++;
        // we will also count the persons
        int personCount = hand.length / groupSize;

        int start = 0;
        // every time we will give one group deck to one person,
        // we will basically decrease the card count
        for (int i = 0; i < personCount; i++) {
            for (int j = 0; j < groupSize; j++) {
                // if the card is out of range or the card frequency is 0
                // then we can say that we cannot give the card
                if (start + j >= range || freq[start + j] == 0) return false;
                freq[start + j]--;
            }
            while (start < range && freq[start] == 0) start++;
        }
        return true;
    }

    // TODO check it later
    public static void type3() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        boolean isPossible = isNStraightHand3(hand, groupSize);
        System.out.println(isPossible);
    }

    public static boolean isNStraightHand3(int[] hand, int groupSize) {
        if (hand.length == 1) return true;
        if (hand.length % groupSize != 0) return false;
        Arrays.sort(hand);
        boolean[] visited = new boolean[hand.length];
        int nv = 0;
        for (int i = 0; i < hand.length / groupSize; i++) {
            int start = hand[nv];
            int p = nv;
            for (int j = start; j < start + groupSize; j++) {
                while (p < hand.length && (visited[p] == true || hand[p] != j)) {
                    p++;
                }
                if (p == hand.length) return false;
                visited[p] = true;
            }
            while (nv < hand.length && visited[nv] == true) {
                nv++;
            }
        }

        return nv == hand.length;
    }

    // optimized approach
    // slightly better than previous
    // TODO check it later
    private static void type2() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        boolean isPossible = isNStraightHand2(hand, groupSize);
        System.out.println(isPossible);
    }

    public static boolean isNStraightHand2(int[] hand, int groupSize) {
        if (hand.length == 1) return true;
        // it means there cannot be any even distribution
        if (hand.length % groupSize != 0) return false;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int card : hand)
            countMap.put(card, countMap.getOrDefault(card, 0) + 1);
        Arrays.sort(hand);

        for (int card : hand) {
            if (countMap.get(card) == 0) continue;
            for (int j = 0; j < groupSize; j++) {
                int currCard = card + j;
                if (countMap.getOrDefault(currCard, 0) == 0)
                    return false;
                countMap.put(currCard, countMap.get(currCard) - 1);
            }
        }
        return true;
    }


    // Using heap
    private static void type1() {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        boolean isPossible = isNStraightHand1(hand, groupSize);
        System.out.println(isPossible);
    }

    public static boolean isNStraightHand1(int[] hand, int groupSize) {
        if (hand.length == 1) return true;
        // it means there cannot be any even distribution
        if (hand.length % groupSize != 0) return false;

        Map<Integer, Integer> freq = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int card : hand)
            freq.put(card, 1 + freq.getOrDefault(card, 0));
        // adding all the unique cards
        minHeap.addAll(freq.keySet());
        while (!minHeap.isEmpty()) {
            int first = minHeap.peek();
            // TODO here we are giving one card at one instant
            // but wer can give k cards at a glance
            // where k is the number of the first card
            for (int i = first; i < first + groupSize; i++) {
                // card in the series in not present
                if (!freq.containsKey(i)) return false;
                // every time we take a card we decrement the card number by one
                freq.put(i, freq.get(i) - 1);
                // freq is 0 meaning it should be the lowest card in the series
                // while distributing increasingly the lowest card will be exhausted at first
                if (freq.get(i) == 0) {
                    if (minHeap.isEmpty() || i != minHeap.peek()) return false;
                    minHeap.poll();
                }
            }
        }
        return true;
    }
}
