package net.travishartwell.adventofcode.year2022;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Day01 {
    private static List<Integer> getCaloriesPerElf(List<String> input) {
        List<Integer> caloriesPerElf = new ArrayList<>();
        int elfCalorieSum = 0;

        for (String line : input) {
            if (line.isBlank()) {
                caloriesPerElf.add(elfCalorieSum);
                elfCalorieSum = 0;
            } else {
                elfCalorieSum += Integer.parseInt(line);
            }
        }

        caloriesPerElf.add(elfCalorieSum);

        caloriesPerElf.sort(Collections.reverseOrder());

        return caloriesPerElf;
    }

    public static Integer solvePart1(List<String> input) {
        List<Integer> caloriesPerElf = getCaloriesPerElf(input);
        return caloriesPerElf.get(0);
    }

    public static Integer solvePart2(List<String> input) {
        List<Integer> caloriesPerElf = getCaloriesPerElf(input);
        return caloriesPerElf.stream().limit(3).reduce(0, Integer::sum);
    }
}
