package ru.job4j.io.duplicates;

import java.io.*;
import java.nio.file.*;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), new DuplicatesVisitor());
        duplicatesVisitor.getDuplicates();
    }
}
