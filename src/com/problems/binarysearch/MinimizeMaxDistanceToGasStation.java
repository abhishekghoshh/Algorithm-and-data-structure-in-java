package com.problems.binarysearch;

import java.util.PriorityQueue;

/*
 * Problem link :
 * https://www.codingninjas.com/studio/problems/minimise-max-distance_7541449
 * https://leetcode.com/problems/minimize-max-distance-to-gas-station/description/
 *
 * Solution link :
 * https://www.youtube.com/watch?v=kMSBvlZ-_HA
 *
 * https://takeuforward.org/arrays/minimise-maximum-distance-between-gas-stations/
 * */
public class MinimizeMaxDistanceToGasStation {
    public static void main(String[] args) {
        type1();
        type2();
        type3();
        type4();
    }

    // TODO best solution
    // uses binary search on answer method
    // TODO check it one more time
    private static void type4() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 6;
        int n = arr.length;
        double low = 0;
        double high = 0;

        //Find the maximum distance:
        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, (double) (arr[i + 1] - arr[i]));
        }

        //Apply Binary search:
        double diff = 1e-6;
        while (high - low > diff) {
            double mid = (low + high) / (2.0);
            int cnt = numberOfGasStationsRequired(mid, arr);
            if (cnt > k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        double answer = high;
        System.out.println(answer);
    }

    public static int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length; // size of the array
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            int numberInBetween = (int) ((arr[i] - arr[i - 1]) / dist);
            if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                numberInBetween--;
            }
            cnt += numberInBetween;
        }
        return cnt;
    }

    // using same heap approach
    // just like the previous one
    private static void type3() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 6;
        int n = arr.length;
        int[] count = new int[n - 1];
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((p1, p2) -> Double.compare(p2.first, p1.first));

        // insert the first n-1 elements into pq
        // with respective distance values:
        for (int i = 0; i < n - 1; i++) maxHeap.add(new Pair(arr[i + 1] - arr[i], i));

        // Pick and place k gas stations:
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            // Find the maximum section
            // and insert the gas station:
            Pair pair = maxHeap.poll();
            int secInd = pair.second;

            // insert the current gas station:
            count[secInd]++;

            double inidiff = arr[secInd + 1] - arr[secInd];
            double newSecLen = inidiff / (double) (count[secInd] + 1);
            maxHeap.add(new Pair(newSecLen, secInd));
        }

        double answer = maxHeap.peek().first;
        System.out.println(answer);
    }

    private static class Pair {
        double first;
        int second;

        Pair(double first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    // using heap
    // time complexity is O(n*log(n))
    private static void type2() {
        int[] arr = {1, 2, 3, 6, 10};
        int k = 4;
        int n = arr.length;
        // we will save the distance between two points
        // and the gas station at that point
        // comparator function will act upon the density between the two points
        // for the start it will be 1
        PriorityQueue<double[]> heap = new PriorityQueue<>((p1, p2) -> Double.compare(p2[0] / p2[1], p1[0] / p1[1]));
        // before allocating anything between ith and i+1 th gas station there will be will only one gas station
        for (int i = 0; i < n - 1; i++) heap.offer(new double[]{arr[i + 1] - arr[i], 1});
        // but we will poll the most distant point
        // then add a point
        // then again we will push it back
        // heap will get us the max distant point again
        while (k > 0) {
            double[] maxDistance = heap.poll();
            maxDistance[1]++;
            heap.offer(maxDistance);
            k--;
        }
        double[] maxDistance = heap.poll();
        double answer = maxDistance[0] / maxDistance[1];
        System.out.println(answer);
    }

    // brute force approach
    // time complexity is O(n^2)
    private static void type1() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int k = 6;
        int n = arr.length;
        int[] count = new int[n - 1];
        //Pick and place k gas stations:
        for (int gasStations = 1; gasStations <= k; gasStations++) {
            //Find the maximum section
            //and insert the gas station:
            double maxSection = -1;
            int max = -1;
            for (int i = 0; i < n - 1; i++) {
                double diff = arr[i + 1] - arr[i];
                double section = diff / (double) (count[i] + 1);
                if (section > maxSection) {
                    maxSection = section;
                    max = i;
                }
            }
            //insert the current gas station:
            count[max]++;
        }
        //Find the maximum distance i.e. the answer:
        double maxAns = -1;
        for (int i = 0; i < n - 1; i++) {
            double diff = arr[i + 1] - arr[i];
            double sectionLength =
                    diff / (double) (count[i] + 1);
            maxAns = Math.max(maxAns, sectionLength);
        }
        System.out.println(maxAns);
    }
}
