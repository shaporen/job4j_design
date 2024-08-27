package ru.job4j.io;

import java.util.*;
import static java.lang.System.*;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String parameter : args) {
            int index = parameter.indexOf("=");
            values.put(parameter.substring(1, index), parameter.substring(index + 1));
        }
    }

    public static ArgsName of(String[] args) {
        validateData(args);
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void validateData(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        for (String parameter : args) {
            if (parameter.startsWith("-=")) {
                throw new IllegalArgumentException(String.format("Error: This argument "
                        + "'%s' does not contain a key", parameter));
            }
            if (!parameter.startsWith("-")) {
                throw new IllegalArgumentException(String.format("Error: This argument '%s' "
                        + "does not start with a '-' character", parameter));
            }
            if (parameter.matches("^-[A-Za-z0-9\\\\.]+=$")) {
                throw new IllegalArgumentException(String.format("Error: This argument "
                        + "'%s' does not contain a value", parameter));
            }
            if (!parameter.contains("=")) {
                throw new IllegalArgumentException(String.format("Error: This argument "
                        + "'%s' does not contain an equal sign", parameter));
            }
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        out.println(zip.get("out"));
        err.print("Error");
    }
}
