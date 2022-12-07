package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day05Test {
    @Test
    void solvePart1Sample() throws IOException {
        String message = Day05.solvePart1(AdventOfCodeUtils.getSampleFileContents(5));
        assertEquals("CMZ", message);
    }

    @Test
    void solvePart1() throws IOException {
        String message = Day05.solvePart1(AdventOfCodeUtils.getInputFileContents(5));
        assertEquals("TLNGFGMFN", message);
    }

    @Test
    void solvePart2Sample() throws IOException {
        String message = Day05.solvePart2(AdventOfCodeUtils.getSampleFileContents(5));
        assertEquals("MCD", message);
    }

    @Test
    void solvePart2() throws IOException {
        String message = Day05.solvePart2(AdventOfCodeUtils.getInputFileContents(5));
        assertEquals("FGLQJCMBD", message);
    }
}