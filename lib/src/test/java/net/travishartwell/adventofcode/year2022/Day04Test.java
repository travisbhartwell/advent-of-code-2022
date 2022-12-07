package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day04Test {
    @Test
    void solvePart1Sample() throws IOException {
        Long containsCount = Day04.solvePart1(AdventOfCodeUtils.getSampleFileContents(4));
        assertEquals(2, containsCount);
    }

    @Test
    void solvePart1() throws IOException {
        Long containsCount = Day04.solvePart1(AdventOfCodeUtils.getInputFileContents(4));
        assertEquals(487, containsCount);
    }

    @Test
    void solvePart2Sample() throws IOException {
        Long containsCount = Day04.solvePart2(AdventOfCodeUtils.getSampleFileContents(4));
        assertEquals(4, containsCount);
    }

    @Test
    void solvePart2() throws IOException {
        Long containsCount = Day04.solvePart2(AdventOfCodeUtils.getInputFileContents(4));
        assertEquals(849, containsCount);
    }
}