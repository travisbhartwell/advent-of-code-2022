package net.travishartwell.adventofcode.year2022;

public final class Day06 {
    public static Integer solvePart1(final String input) {
        return solve(input, 4);
    }

    public static Integer solvePart2(final String input) {
        return solve(input, 14);
    }

    public static Integer solve(final String input, final int uniqueCharacterCount) {
        for (int startIndex=0; startIndex < (input.length() - uniqueCharacterCount); startIndex++) {
            int endIndex = startIndex + uniqueCharacterCount;
            String substring = input.substring(startIndex, endIndex);
            if (substring.chars().distinct().count() == uniqueCharacterCount) {
                return endIndex;
            }
        }

        return input.length();
    }
}
