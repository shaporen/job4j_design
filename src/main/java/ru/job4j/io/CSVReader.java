package ru.job4j.io;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

/**
 * Класс для чтения данных из CSV файла и вывода/записи их
 * в указанной последовательности
 * @author Sergey Shaporenko
 * @version 1.0
 */
public class CSVReader {
    /**
     * Метод принимает аргументы, указанные в параметрах программы,
     * определяет требуемую последовательность искомой информации и
     * собирает ее из исходного файла.
     * @param argsName - параметры программы
     * @throws Exception -
     */
    public static void handle(ArgsName argsName) throws Exception {
        String delimiter = argsName.get("delimiter");
        File file = new File(argsName.get("path"));
        boolean printToConsole = "stdout".equals(argsName.get("out"));
        try (Scanner scanner = new Scanner(file);
             BufferedWriter output = new BufferedWriter(
                     new FileWriter(argsName.get("out"), StandardCharsets.UTF_8, false))) {
            StringJoiner joiner = new StringJoiner(delimiter);
            List<String> columns = List.of(scanner.nextLine().split(delimiter));
            List<String> columnsRequired = List.of(argsName.get("filter").split(","));
            List<Integer> indexesRequired = new ArrayList<>();

            for (String col : columnsRequired) {
                if (columns.contains(col)) {
                    indexesRequired.add(columns.indexOf(col));
                    joiner.add(col);
                }
            }

            if (printToConsole) {
                System.out.println(joiner + System.lineSeparator());
            } else {
                output.write(joiner + System.lineSeparator());
            }

            while (scanner.hasNext()) {
                columns = List.of(scanner.nextLine().split(delimiter));
                joiner = new StringJoiner(delimiter);
                for (Integer i : indexesRequired) {
                    joiner.add(columns.get(i));
                }

                if (printToConsole) {
                    System.out.println(joiner + System.lineSeparator());
                } else {
                    output.write(joiner + System.lineSeparator());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проводит проверку на полноту переданных параметров,
     * на наличие исходного файла и его расширения, на верное указание параметров
     * вывода/записи полученных данных
     * @param args - параметры программы
     */
    private static void validateArgs(String[] args) {
        ArgsName values = ArgsName.of(args);
        if (args.length < 4) {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        if (!Files.exists(Path.of(values.get("path")))) {
            throw new IllegalArgumentException("The source data file is missing");
        }
        if (!values.get("out").endsWith(".csv") || !"stdout".equals(values.get("out"))) {
            throw new IllegalArgumentException("The path for writing data is not specified");
        }
    }

    public static void main(String[] args) throws Exception {
        validateArgs(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}

//-path=file.csv -delimiter=;  -out=stdout -filter=name,age
