package com.problems.array;

import java.util.HashMap;
import java.util.Map;

/*
 * Problem link:
 * https://leetcode.com/problems/design-underground-system/description/
 *
 * Solution link:
 * https://www.youtube.com/watch?v=W5QOLqXskZM
 *
 * https://neetcode.io/solutions/design-underground-system
 */
public class DesignUndergroundSystem {
    public static void main(String[] args) {
        type1();
    }

    // optimized solution
    private static void type1() {
        UndergroundSystem undergroundSystem = new UndergroundSystem();
        undergroundSystem.checkIn(45, "Leyton", 3);
        undergroundSystem.checkIn(32, "Paradise", 8);
        undergroundSystem.checkIn(27, "Leyton", 10);
        undergroundSystem.checkOut(45, "Waterloo", 15);  // Customer 45 "Leyton" → "Waterloo" in 15-3 = 12
        undergroundSystem.checkOut(27, "Waterloo", 20);  // Customer 27 "Leyton" → "Waterloo" in 20-10 = 10
        undergroundSystem.checkOut(32, "Cambridge", 22); // Customer 32 "Paradise" → "Cambridge" in 22-8 = 14
        undergroundSystem.getAverageTime("Paradise", "Cambridge");          // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");             // Return 11.00000. Two trips "Leyton" → "Waterloo", (10 + 12) / 2 = 11
        undergroundSystem.checkIn(10, "Leyton", 24);
        undergroundSystem.getAverageTime("Leyton", "Waterloo");             // return 11.00000
        undergroundSystem.checkOut(10, "Waterloo", 38); // Customer 10 "Leyton" → "Waterloo" in 38-24 = 14
        undergroundSystem.getAverageTime("Leyton", "Waterloo");             // Return 12.00000. Three trips "Leyton" → "Waterloo", (10 + 12 + 14) / 3 = 12
    }

    static class UndergroundSystem {
        // we will use two maps
        // one for storing users check in from which station he had onboarded
        Map<Integer, Pair<String, Integer>> checkIn = new HashMap<>();
        // another map for [source-destination] → total time and total number of passengers
        Map<String, Pair<Double, Double>> avgTime = new HashMap<>();

        public UndergroundSystem() {

        }

        public void checkIn(int id, String stationName, int t) {
            // updating check in for the passenger
            checkIn.put(id, new Pair<>(stationName, t));
        }

        public void checkOut(int id, String stationName, int t) {
            // while check out we will remove the entry from 'check in' map and update avgTime map
            Pair<String, Integer> checkInTime = checkIn.remove(id);
            String key = checkInTime.first + "::" + stationName;
            double time = t - checkInTime.second;
            if (!avgTime.containsKey(key)) {
                avgTime.put(key, new Pair<>(time, 1.0));
            } else {
                Pair<Double, Double> pair = avgTime.get(key);
                pair.first += time;
                pair.second++;
            }
        }

        public double getAverageTime(String startStation, String endStation) {
            String key = startStation + "::" + endStation;
            Pair<Double, Double> avgTimePair = avgTime.get(key);
            return (avgTimePair.first / avgTimePair.second);
        }

        static class Pair<T, R> {
            T first;
            R second;

            Pair(T first, R second) {
                this.first = first;
                this.second = second;
            }
        }
    }

}
