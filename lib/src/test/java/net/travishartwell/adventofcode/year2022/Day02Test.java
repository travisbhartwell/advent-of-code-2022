package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class Day02Test {
    @Test
    void solvePart1Sample() throws IOException {
        Integer totalScore = Day02.solvePart1(AdventOfCodeUtils.getSampleFileContents(2));
        assertEquals(15, totalScore);
    }

    @Test
    void solvePart1() throws IOException {
        Integer totalScore = Day02.solvePart1(AdventOfCodeUtils.getInputFileContents(2));
        assertEquals(10994, totalScore);
    }

    @Test
    void solvePart2Sample() throws IOException {
        Integer totalScore = Day02.solvePart2(AdventOfCodeUtils.getSampleFileContents(2));
        assertEquals(12, totalScore);
    }

    @Test
    void solvePart2() throws IOException {
        Integer totalScore = Day02.solvePart2(AdventOfCodeUtils.getInputFileContents(2));
        assertEquals(12526, totalScore);
    }
}