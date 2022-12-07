package net.travishartwell.adventofcode.year2022;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Day05 {
    @FunctionalInterface
    interface InstructionExecutor {
        void executeInstruction(final int count, final Stack<Character> srcStack, final Stack<Character> destStack);
    }

    private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("move (?<count>\\d+) from (?<src>\\d) to (?<dest>\\d)");
    private static final String COUNT_GROUP = "count";
    private static final String SRC_GROUP = "src";
    private static final String DEST_GROUP = "dest";

    private static Map<Integer, Stack<Character>> parseStartingState(final List<String> startingStateLines) {
        final Map<Integer, Stack<Character>> stacks = initializeStacks(startingStateLines);
        final int stackCount = stacks.size();

        ListIterator<String> iterator = startingStateLines.listIterator(startingStateLines.size() - 1);
        while (iterator.hasPrevious()) {
            String line = iterator.previous();

            for (int stackNumber = 0; stackNumber < stackCount; stackNumber++) {
                int index = (stackNumber * 4) + 1;
                Character c = line.charAt(index);

                if (! c.equals(' ')) {
                    stacks.get(stackNumber + 1).push(c);
                }
            }
        }

        return stacks;
    }

    private static Map<Integer, Stack<Character>> initializeStacks(List<String> startingStateString) {
        final String lastLine = startingStateString.get(startingStateString.size() - 1);
        List<Integer> stackNumbers = Arrays.stream(lastLine.strip().split("([ ]+)")).map(Integer::parseInt).toList();

        Map<Integer, Stack<Character>> stacks = new HashMap<>();
        stackNumbers.forEach(number -> stacks.put(number, new Stack<>()));
        return stacks;
    }

    public static String solvePart1(final List<String> input) {
        return solve(input, Day05::performInstructionPart1);
    }

    public static String solvePart2(final List<String> input) {
        return solve(input, Day05::performInstructionPart2);
    }

    private static String solve(final List<String> input, final InstructionExecutor instructionExecutor) {
        final int sectionBreak = input.indexOf("");
        final Map<Integer, Stack<Character>> stacks = parseStartingState(input.subList(0, sectionBreak));
        final ListIterator<String> instructionIterator = input.listIterator(sectionBreak + 1);

        while (instructionIterator.hasNext()) {
            final String instruction = instructionIterator.next();
            Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);

            if (matcher.find()) {
                int count = Integer.parseInt(matcher.group(COUNT_GROUP));
                int src = Integer.parseInt(matcher.group(SRC_GROUP));
                int dest = Integer.parseInt(matcher.group(DEST_GROUP));

                Stack<Character> srcStack = stacks.get(src);
                Stack<Character> destStack = stacks.get(dest);
                instructionExecutor.executeInstruction(count, srcStack, destStack);
            }
        }

        return IntStream.range(1, stacks.size() + 1)
                .mapToObj(i -> stacks.get(i).pop())
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private static void performInstructionPart1(final int count, final Stack<Character> srcStack, final Stack<Character> destStack) {
        if (srcStack.size() < count) {
            throw new IllegalStateException("Cannot pop " + count + "from stack with " + srcStack.size());
        }

        for (int i = 0; i < count; i++) {
            destStack.push(srcStack.pop());
        }
    }

    private static void performInstructionPart2(final int count, final Stack<Character> srcStack, final Stack<Character> destStack) {
        if (srcStack.size() < count) {
            throw new IllegalStateException("Cannot pop " + count + "from stack with " + srcStack.size());
        }

        final LinkedList<Character> temp = new LinkedList<>();

        for (int i=0; i < count; i++) {
            temp.addFirst(srcStack.pop());
        }

        temp.forEach(destStack::push);
    }
}
