package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day03Test {
    @Test
    void solvePart1Sample() throws IOException {
        Integer totalPriority = Day03.solvePart1(AdventOfCodeUtils.getSampleFileContents(3));
        assertEquals(157, totalPriority);
    }

    @Test
    void solvePart1() throws IOException {
        Integer totalPriority = Day03.solvePart1(AdventOfCodeUtils.getInputFileContents(3));
        assertEquals(7691, totalPriority);
    }

    @Test
    void solvePart2Sample() throws IOException {
        Integer totalPriority = Day03.solvePart2(AdventOfCodeUtils.getSampleFileContents(3));
        assertEquals(70, totalPriority);
    }

    @Test
    void solvePart2() throws IOException {
        Integer totalPriority = Day03.solvePart2(AdventOfCodeUtils.getInputFileContents(3));
        assertEquals(2508, totalPriority);
    }
}