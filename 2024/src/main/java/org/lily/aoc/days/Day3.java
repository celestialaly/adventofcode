package org.lily.aoc.days;

import org.lily.aoc.Day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 extends Day {
    public static void main(String[] args) throws Exception {
        Day3 instance = new Day3();
        instance.solve();
    }

    @Override
    protected String partOne() throws Exception {
        String instruction = this.readFileAsString(3);
        String regex = "mul\\((?<n1>\\d+),(?<n2>\\d+)\\)";

        Matcher matcher = Pattern.compile(regex).matcher(instruction);
        int result = 0;

        while (matcher.find()) {
            result += Integer.parseInt(matcher.group("n1")) * Integer.parseInt(matcher.group("n2"));
        }

        return String.valueOf(result);
    }

    @Override
    protected String partTwo() throws Exception {
        String instruction = this.readFileAsString(3);
        String regex = "(mul\\((?<n1>\\d+),(?<n2>\\d+)\\))|(do\\(\\))|(don't\\(\\))";

        Matcher matcher = Pattern.compile(regex).matcher(instruction);
        int result = 0;
        boolean doCalculation = true;

        while (matcher.find()) {
            if (matcher.group(0).equals("do()")) {
                doCalculation = true;
            } else if (matcher.group(0).equals("don't()")) {
                doCalculation = false;
            } else if (doCalculation) {
                result += Integer.parseInt(matcher.group("n1")) * Integer.parseInt(matcher.group("n2"));
            }
        }

        return String.valueOf(result);
    }
}
