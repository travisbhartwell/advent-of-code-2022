package net.travishartwell.adventofcode.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public final class AdventOfCodeUtils {
    static String getInputFileName(final int dayNumber) {
        return String.format("day%02d-input.txt", dayNumber);
    }

    static String getSampleFileName(final int dayNumber) {
        return String.format("day%02d-sample.txt", dayNumber);
    }

    private static List<String> getFileContents(final String filename) throws IOException {
        String filePath = Objects.requireNonNull(AdventOfCodeUtils.class.getClassLoader().getResource(filename).getPath());
        return Files.readAllLines(Paths.get(filePath));
    }

    static List<String> getInputFileContents(final int dayNumber) throws IOException {
        return getFileContents(getInputFileName(dayNumber));
    }

    static List<String> getSampleFileContents(final int dayNumber) throws IOException {
        return getFileContents(getSampleFileName(dayNumber));
    }
}
