package net.travishartwell.adventofcode.year2022;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class Day06Test {
    @DisplayName("Should find the location of the start of packet marker.")
    @ParameterizedTest(name = "{index} => input={0}, expectedNumberOfCharacters={1}")
    @CsvSource({
           "mjqjpqmgbljsphdztnvjfqwrcgsmlb,7",
           "bvwbjplbgvbhsrlpgdmjqwftvncz,5",
           "nppdvjthqldpwncqszvftbrmjlhg,6",
           "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,10",
           "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,11"
    })
    void solvePart1Samples(final String input, final int expectedNumberOfCharacters) {
        Integer numberOfCharacters = Day06.solvePart1(input);
        assertEquals(expectedNumberOfCharacters, numberOfCharacters);
    }

    @DisplayName("Should find the start of message marker.")
    @ParameterizedTest(name = "{index} => input={0}, expectedNumberOfCharacters={1}")
    @CsvSource({
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb,19",
            "bvwbjplbgvbhsrlpgdmjqwftvncz,23",
            "nppdvjthqldpwncqszvftbrmjlhg,23",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg,29",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw,26"
    })
    void solvePart2Samples(final String input, final int expectedNumberOfCharacters) {
        Integer numberOfCharacters = Day06.solvePart2(input);
        assertEquals(expectedNumberOfCharacters, numberOfCharacters);
    }

    @Test
    void solvePart1() throws IOException {
        Integer numberOfCharacters = Day06.solvePart1(AdventOfCodeUtils.getInputFileContents(6).get(0));
        assertEquals(1953, numberOfCharacters);
    }

    @Test
    void solvePart2() throws IOException {
        Integer numberOfCharacters = Day06.solvePart2(AdventOfCodeUtils.getInputFileContents(6).get(0));
        assertEquals(2301, numberOfCharacters);
    }
}