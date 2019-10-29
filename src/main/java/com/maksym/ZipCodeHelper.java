package com.maksym;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ZipCodeHelper {
    public static List<List<Integer>> solveZip(List<List<Integer>> intervals) throws IllegalArgumentException {
        if (intervals == null || intervals.size() == 0) {return null;}
        List<Interval> listIntervals = intervals.stream()
                .map(interval -> new Interval(interval.get(0), interval.get(1))).sorted().collect(Collectors.toList());

        mergeIntervals(listIntervals);
        List<List<Integer>> result = new ArrayList<>();
        listIntervals.forEach(interval -> result.add(new ArrayList<Integer>(Arrays.asList(interval.getMin(), interval.getMax()))));
        return result;
    }

    private static void mergeIntervals(List<Interval> intervals) {
        int i = 1;
        while (i <= intervals.size() - 1) {
            if (intervals.get(i - 1).intersects(intervals.get(i)) || intervals.get(i - 1).includeInterval(intervals.get(i))) {
                intervals.get(i - 1).merge(intervals.get(i));
                intervals.remove(i);
                i = 0;

            }
            i++;
        }
    }
}