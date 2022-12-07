package net.travishartwell.adventofcode.year2022;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;

public final class Day07 {

    public static abstract class PathNode {
        private final String name;
        private final String fullPath;
        private final PathNode parent;

        public PathNode(String name, String fullPath, PathNode parent) {
            this.name = name;
            this.fullPath = fullPath;
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public String getFullPath() {
            return fullPath;
        }

        public abstract Integer getSize();

        public PathNode getParent() {
            return parent;
        }
    }

    public static class FileNode extends PathNode {
        private final Integer size;

        public Integer getSize() {
            return size;
        }

        public FileNode(String name, String fullPath, PathNode parent, Integer size) {
            super(name, fullPath, parent);
            this.size = size;
        }
    }

    public static class DirectoryNode extends PathNode {
        private final Map<String, PathNode> children;

        public DirectoryNode(String name, String fullPath, PathNode parent) {
            super(name, fullPath, parent);
            this.children = new HashMap<>();
        }

        public PathNode getOrCreateChildDir(String childName) {
            return children.computeIfAbsent(
                    childName,
                    name -> new DirectoryNode(name, getFullPath() + "/" + name, this));
        }

        public void addFileWithSize(final String fileName, final Integer size) {
            FileNode fileNode = new FileNode(fileName, getFullPath() + "/" + fileName, this, size);
            children.put(fileName, fileNode);
        }

        public Integer getSize() {
            return children.values()
                    .stream()
                    .map(PathNode::getSize)
                    .reduce(0, Integer::sum);
        }

        public List<DirectoryNode> getThisAndSubDirectoriesRecursively() {
            List<DirectoryNode> allDirectories = new ArrayList<>();

            children.values().stream()
                    .filter(c -> c instanceof DirectoryNode)
                    .forEach(c ->
                            allDirectories.addAll(((DirectoryNode) c).getThisAndSubDirectoriesRecursively()));
            allDirectories.add(this);

            return allDirectories;
        }
    }

    public static Integer solvePart1(List<String> input) {
        final DirectoryNode root = parseDirectoriesFromInput(input);

        return root.getThisAndSubDirectoriesRecursively().stream()
                .filter(d -> d.getSize() < 100000)
                .map(PathNode::getSize)
                .reduce(0, Integer::sum);
    }

    public static Integer solvePart2(List<String> input) {
        final DirectoryNode root = parseDirectoriesFromInput(input);
        final int totalSpace = 70000000;
        final int freeSpaceNeeded = 30000000;

        final int currentSpaceUsed = root.getSize();
        final int spaceToFree = freeSpaceNeeded - (totalSpace - currentSpaceUsed);

        return root.getThisAndSubDirectoriesRecursively().stream()
                .filter(d -> d.getSize() >= spaceToFree)
                .sorted(Comparator.comparing(PathNode::getSize))
                .map(PathNode::getSize)
                .findFirst()
                .get();
    }

    private static DirectoryNode parseDirectoriesFromInput(List<String> input) {
        final DirectoryNode root = new DirectoryNode("/", "/", null);

        ListIterator<String> iterator = input.listIterator();

        DirectoryNode currentDirectory = root;

        while(iterator.hasNext()) {
            final String line = iterator.next();

            if (Objects.equals(line, "$ cd /")) {
                currentDirectory = root;
            } else if (Objects.equals(line, "$ cd ..")) {
                currentDirectory = (DirectoryNode) currentDirectory.getParent();
                Objects.requireNonNull(currentDirectory);
            } else if (line.startsWith("$ cd ")) {
                final String newDirectoryName = line.substring(5);
                currentDirectory = (DirectoryNode) currentDirectory.getOrCreateChildDir(newDirectoryName);
            } else if (line.startsWith("$ ls")) {
                addDirectoryContents(iterator, currentDirectory);
            } else {
                throw new IllegalStateException("Unexpected line: '" + line + "'.");
            }
        }
        return root;
    }

    private static void addDirectoryContents(ListIterator<String> iterator, DirectoryNode currentDirectory) {
        boolean done = false;

        while (iterator.hasNext() && !done) {
            final String maybeFileLine = iterator.next();

            if (maybeFileLine.startsWith("$")) {
                done = true;
                iterator.previous();
            } else {
                final String[] parts = maybeFileLine.split(" ");

                if (Objects.equals(parts[0], "dir")) {
                    currentDirectory.getOrCreateChildDir(parts[1]);
                } else {
                    final Integer size = Integer.parseInt(parts[0]);
                    currentDirectory.addFileWithSize(parts[1], size);
                }
            }
        }
    }
}
