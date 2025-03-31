package org.lily.aoc.days;

import org.lily.aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 extends Day {
    public static void main(String[] args) throws Exception {
        Day2 instance = new Day2();
        instance.solve();
    }

    @Override
    protected String partOne() throws Exception {
        List<List<Integer>> list = getSplitLines(2).stream()
                .map(l -> Arrays.stream(l).mapToInt(Integer::valueOf).boxed().toList())
                .toList();

        return String.valueOf(list.stream()
                .filter(this::isListSafe)
                .count());
    }

    @Override
    protected String partTwo() throws Exception {
        List<List<Integer>> list = getSplitLines(2).stream()
                .map(l -> Arrays.stream(l).mapToInt(Integer::valueOf).boxed().toList())
                .toList();

        return String.valueOf(list.stream()
                .filter(numbers -> {
                    int size = numbers.size();

                    if (this.isListSafe(numbers)) {
                        return true;
                    }

                    for (int i = 0; i < size; i++) {
                        List<Integer> splicedList = new ArrayList<>(numbers);
                        splicedList.remove(i);

                        if (this.isListSafe(splicedList)) {
                            return true;
                        }
                    }

                    return false;
                })
                .count());
    }

    private boolean isListSafe(List<Integer> list) {
        boolean up = list.get(0) < list.get(1);

        for (int i = 1; i < list.size(); i++) {
            if (breakIntervalCondition(up, list.get(i), list.get(i - 1))) {
                return false;
            }
        }

        return true;
    }

    private boolean breakIntervalCondition(boolean up, int current, int prev) {
        if (Math.abs(current - prev) > 3) return true;

        return (!up || current <= prev) && (up || current >= prev);
    }
}
