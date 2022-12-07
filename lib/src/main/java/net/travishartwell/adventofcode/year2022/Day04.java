package net.travishartwell.adventofcode.year2022;

import java.util.List;
import java.util.Objects;

public final class Day04 {
    record Range(int start, int end) {
        public static Range parseFromString(final String rangeString) {
            Objects.requireNonNull(rangeString);

            String[] parts = rangeString.split("-");

            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid range format: '" + rangeString + "'.");
            }

            int start = Integer.parseInt(parts[0]);
            int end = Integer.parseInt(parts[1]);

            return new Range(start, end);
        }

        public Range {
            if (start < 1) {
                throw new IllegalArgumentException("start must be 1 or greater.");
            }

            if (end < start) {
                throw new IllegalArgumentException("end must be greater than or equal to start.");
            }
        }

        public boolean fullyContains(final Range other) {
            Objects.requireNonNull(other);

            return (this.start <= other.start) && (this.end >= other.end);
        }

        public boolean overlaps(final Range other) {
            Objects.requireNonNull(other);

            return (other.start <= end && other.end >= start);
        }
    }

    static boolean rangeFullyContainsOther(final String line) {
        final String[] parts = line.split(",");
        final Range range1 = Range.parseFromString(parts[0]);
        final Range range2 = Range.parseFromString(parts[1]);

        return (range1.fullyContains(range2) || range2.fullyContains(range1));
    }

    public static Long solvePart1(final List<String> input) {
        return input.stream().filter(Day04::rangeFullyContainsOther).count();
    }

    static boolean rangeOverlapsOther(final String line) {
        final String[] parts = line.split(",");
        final Range range1 = Range.parseFromString(parts[0]);
        final Range range2 = Range.parseFromString(parts[1]);

        return (range1.overlaps(range2) || range2.overlaps(range1));
    }

    public static Long solvePart2(final List<String> input) {
        return input.stream().filter(Day04::rangeOverlapsOther).count();
    }
}
