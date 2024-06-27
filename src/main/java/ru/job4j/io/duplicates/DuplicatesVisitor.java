package ru.job4j.io.duplicates;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(files.size(), file.getFileName().toString());
        files.computeIfAbsent(fileProperty, f -> new ArrayList<>()).add(file);
        return super.visitFile(file, attributes);
    }

    public void getDuplicates() {
        files.forEach((key, value) -> {
            if (value.size() > 1) {
                System.out.println("Имя файла: " + key.getName()
                        + ", размер файла: "  + key.getSize());
                System.out.println("Файл расположен в директориях:");
                value.forEach(System.out::println);
            }
        });
    }
}
