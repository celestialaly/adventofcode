package org.lily.aoc.days;

import org.lily.aoc.Day;

import java.util.List;
import java.util.stream.IntStream;

public class Day1 extends Day {
    public static void main(String[] args) throws Exception {
        Day1 instance = new Day1();
        instance.solve();
    }

    @Override
    protected String partOne() throws Exception {
        List<String[]> list = getSplitLines(1);

        List<Integer> firstList = list.stream().map(l -> l[0]).map(Integer::valueOf).sorted().toList();
        List<Integer> secondList = list.stream().map(l -> l[1]).map(Integer::valueOf).sorted().toList();

        return String.valueOf(
                IntStream.range(0, firstList.size())
                        .mapToObj(i -> Math.abs(firstList.get(i) - secondList.get(i)))
                        .mapToInt(Integer::intValue)
                        .sum()
        );
    }

    @Override
    protected String partTwo() throws Exception {
        List<String[]> list = getSplitLines(1);

        List<Integer> firstList = list.stream().map(l -> l[0]).map(Integer::valueOf).sorted().toList();
        List<Integer> secondList = list.stream().map(l -> l[1]).map(Integer::valueOf).sorted().toList();

        return String.valueOf(
                firstList.stream()
                        .map(l -> l * Math.toIntExact(secondList.stream().filter(i -> i.equals(l)).count()))
                        .mapToInt(Integer::intValue)
                        .sum()
        );
    }
}
