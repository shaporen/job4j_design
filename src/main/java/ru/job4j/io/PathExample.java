package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;

public class PathExample {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("Attributes.txt");
        Files.createFile(file);
        BasicFileAttributeView attrView = Files.getFileAttributeView(file,
                BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();
        System.out.println("Это обычный файл? " + attributes.isRegularFile());
        System.out.println("Это директория? " + attributes.isDirectory());
        System.out.println("Это символическая ссылка? " + attributes.isSymbolicLink());
        System.out.println("Это не файл, директория или символическая ссылка? "
                + attributes.isOther());
        System.out.println("Дата создания файла: " + attributes.creationTime());
        System.out.println("Размер файла: " + attributes.size());
        System.out.println("Время последнего доступа: " + attributes.lastAccessTime());
        System.out.println("Время последнего изменения: " + attributes.lastModifiedTime());
    }
}
