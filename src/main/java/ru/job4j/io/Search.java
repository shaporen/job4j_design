package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class Search {
    public static void main(String[] args) throws IOException {
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
