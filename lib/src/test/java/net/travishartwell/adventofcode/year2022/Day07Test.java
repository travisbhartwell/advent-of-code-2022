package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {
    @Test
    void solvePart1Sample() throws IOException {
        final Integer totalSize = Day07.solvePart1(AdventOfCodeUtils.getSampleFileContents(7));
        assertEquals(95437, totalSize);
    }

    @Test
    void solvePart1() throws IOException {
        final Integer totalSize = Day07.solvePart1(AdventOfCodeUtils.getInputFileContents(7));
        assertEquals(1743217, totalSize);
    }

    @Test
    void solvePart2Sample() throws IOException {
        final Integer totalSize = Day07.solvePart2(AdventOfCodeUtils.getSampleFileContents(7));
        assertEquals(24933642, totalSize);
    }

    @Test
    void solvePart2() throws IOException {
        final Integer totalSize = Day07.solvePart2(AdventOfCodeUtils.getInputFileContents(7));
        assertEquals(8319096, totalSize);
    }
}