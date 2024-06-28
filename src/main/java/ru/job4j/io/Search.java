package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validateArgs(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Your arguments isn't valid");
        }
        if (!Files.isDirectory(Path.of(args[0]))) {
            throw new IllegalArgumentException(args[0] + " isn't directory");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Еhe extension of "
                    + "the file you are searching for should start with \".\"");
        }
    }
}
