package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        for (Path source : sources) {
            try (ZipOutputStream zip = new ZipOutputStream(
                    new BufferedOutputStream(new FileOutputStream(target)))) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream output = new BufferedInputStream(
                        new FileInputStream(source.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(
                    new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args) {
        ArgsName values = ArgsName.of(args);
        if (args.length < 3) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        if (!Files.isDirectory(Path.of(values.get("d")))) {
            throw new IllegalArgumentException("The specified directory does not exist");
        }
        if (!values.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Invalid exclusion extension specified");
        }
        if (!values.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Invalid archive extension specified");
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        String source = ArgsName.of(args).get("d");
        String exclude = ArgsName.of(args).get("e");
        String target = ArgsName.of(args).get("o");
        List<Path> sources = Search.search(Path.of(source),
                path -> !path.toFile().getName().endsWith(exclude));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(target));
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}
