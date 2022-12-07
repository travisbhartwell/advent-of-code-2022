package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day01Test {
    @Test
    void solvePart1Sample() throws IOException {
        Integer result = Day01.solvePart1(AdventOfCodeUtils.getSampleFileContents(1));
        assertEquals(24000, result);
    }

    @Test
    void solvePart1() throws IOException {
        Integer result = Day01.solvePart1(AdventOfCodeUtils.getInputFileContents(1));
        assertEquals(69883, result);
    }

    @Test
    void solvePart2() throws IOException {
        Integer result = Day01.solvePart2(AdventOfCodeUtils.getInputFileContents(1));
        assertEquals(207576, result);
    }
}