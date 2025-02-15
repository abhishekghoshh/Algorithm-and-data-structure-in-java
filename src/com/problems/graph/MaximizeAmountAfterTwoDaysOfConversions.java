package com.problems.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Problem link :
 * https://leetcode.com/problems/maximize-amount-after-two-days-of-conversions/description/
 *
 * Solution link:
 *
 *
 * */
public class MaximizeAmountAfterTwoDaysOfConversions {
    public static void main(String[] args) {
        type1();
    }

    // using recursion and dfs
    private static void type1() {
        String initialCurrency = "EUR";
        List<List<String>> pairs1 = List.of(
                List.of("EUR", "USD"),
                List.of("USD", "JPY")
        );
        double[] rates1 = {2.0, 3.0};
        List<List<String>> pairs2 = List.of(
                List.of("JPY", "USD"),
                List.of("USD", "CHF"),
                List.of("CHF", "EUR")
        );
        double[] rates2 = {4.0, 5.0, 6.0};

        double ans = maxAmount1(initialCurrency, pairs1, rates1, pairs2, rates2);
        System.out.println(ans);
    }

    static double max = 1;
    static String finalCurrency = null;

    public static double maxAmount1(String initialCurrency,
                                    List<List<String>> pairs1, double[] rates1,
                                    List<List<String>> pairs2, double[] rates2) {
        finalCurrency = initialCurrency;
        int n1 = rates1.length, n2 = rates2.length;
        boolean[] visited1 = new boolean[n1], visited2 = new boolean[n2];
        // We will create a map of initial currency and final currency.
        // However, we can also convert final currency to initial currency with (1/rate).
        // However, if we do this, then we will have an endless loop
        // so to prevent that we will use a visited array and along with storing the currency,
        // we will also store the index. We could also store the rates, but we can derive it from the 'rates' array easily
        Map<String, List<Data>> m1 = loadMap(n1, pairs1, rates1);
        Map<String, List<Data>> m2 = loadMap(n2, pairs2, rates2);
        convert(initialCurrency, 1.0, 0, visited1, m1, visited2, m2);
        return max;
    }

    static void convert(String curr, double amount, int day,
                        boolean[] visited1, Map<String, List<Data>> m1,
                        boolean[] visited2, Map<String, List<Data>> m2) {
        if (finalCurrency.equals(curr)) {
            max = Math.max(max, amount);
        }
        // we can use the conversion of the first day if (day == 0)
        if (day == 0) {
            List<Data> day1Rates = m1.get(curr);
            if (null != day1Rates) {
                for (Data data : day1Rates) {
                    if (!visited1[data.i]) {
                        visited1[data.i] = true;
                        convert(data.curr, (amount * data.rate), 0, visited1, m1, visited2, m2);
                        visited1[data.i] = false;
                    }
                }
            }
        }
        // we can use the conversion of the second day for both of the days
        List<Data> day2Rates = m2.get(curr);
        if (null != day2Rates) {
            for (Data data : day2Rates) {
                if (!visited2[data.i]) {
                    visited2[data.i] = true;
                    convert(data.curr, (amount * data.rate), 1, visited1, m1, visited2, m2);
                    visited2[data.i] = false;
                }
            }
        }

    }

    static Map<String, List<Data>> loadMap(int n, List<List<String>> pairs, double[] rates) {
        Map<String, List<Data>> m = new HashMap<>();
        for (int i = 0; i < n; i++) {
            List<String> pair = pairs.get(i);
            String curr1 = pair.get(0), curr2 = pair.get(1);
            // storing initial -> final
            if (!m.containsKey(curr1)) m.put(curr1, new ArrayList<>());
            m.get(curr1).add(new Data(curr2, i, rates[i]));
            // storing final -> initial
            if (!m.containsKey(curr2)) m.put(curr2, new ArrayList<>());
            m.get(curr2).add(new Data(curr1, i, 1 / rates[i]));
        }
        return m;
    }

    static class Data {
        String curr;
        int i;
        double rate;

        Data(String curr, int i, double rate) {
            this.curr = curr;
            this.i = i;
            this.rate = rate;
        }
    }
}
