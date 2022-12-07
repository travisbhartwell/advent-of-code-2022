package net.travishartwell.adventofcode.year2022;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Character.isUpperCase;

public class Day03 {
    static Integer charToPriority(Character character) {
        if (isUpperCase(character)) {
            return (character - 'A') + 27;
        } else {
            return (character - 'a') + 1;
        }
    }

    static Set<Character> charsToCharSet(IntStream intStream) {
        return intStream.mapToObj(chr -> (char) chr).collect(Collectors.toUnmodifiableSet());
    }

    public static Integer solvePart1(List<String> input) {
        int totalPriority = 0;

        for (String line : input) {
            final int compartmentSize = line.length() / 2;

            IntStream limit = line.chars().limit(5);
            final Set<Character> firstCompartment = charsToCharSet(line.chars().limit(compartmentSize));
            final Set<Character> secondCompartment = charsToCharSet(line.chars().skip(compartmentSize));

            Set<Character> commonChars = new HashSet<>(firstCompartment);
            commonChars.retainAll(secondCompartment);

            totalPriority += commonChars.stream().map(Day03::charToPriority).reduce(0, Integer::sum);
        }

        return totalPriority;
    }

    static Integer priorityOfGroup(List<String> groupInput) {
        final Set<Character> elf1 = charsToCharSet(groupInput.get(0).chars());
        final Set<Character> elf2 = charsToCharSet(groupInput.get(1).chars());
        final Set<Character> elf3 = charsToCharSet(groupInput.get(2).chars());

        Set<Character> commonItems = new HashSet<>(elf1);
        commonItems.retainAll(elf2);
        commonItems.retainAll(elf3);

        if (commonItems.size() != 1) {
            throw new IllegalStateException("Unexpected common size: " + commonItems.size());
        }

        return charToPriority(commonItems.iterator().next());
    }

    public static Integer solvePart2(List<String> input) {
        final AtomicInteger counter = new AtomicInteger();
        return input.stream()
                .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / 3))
                .values().stream()
                .map(Day03::priorityOfGroup)
                .reduce(0, Integer::sum);
    }
}
