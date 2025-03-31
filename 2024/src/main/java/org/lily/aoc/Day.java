package org.lily.aoc;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public abstract class Day {
    abstract protected String partOne() throws Exception;

    abstract protected String partTwo() throws Exception;

    protected void solve() throws Exception {
        System.out.println("---- " + this.getClass().getSimpleName() + " ----");
        System.out.println("part 1: " + this.partOne());
        System.out.println("part 2: " + this.partTwo());
    }

    protected List<String[]> getSplitLines(int day) throws Exception {
        return readFile(day)
                .map(l -> l.split("\\s+"))
                .toList();
    }

    protected Stream<String> readFile(int day) throws Exception {
        return Files.lines(Path.of("src/main/resources/day" + day + ".txt"));
    }
}
