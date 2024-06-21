package ru.job4j.io;

import java.io.*;
import java.util.*;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines()
                    .filter(str -> !str.isEmpty())
                    .filter(str -> !str.startsWith("#"))
                    .forEach(str -> {
                        String[] string = str.split("=", 2);
                        if (string.length < 2 || string[0].isEmpty() || string[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Строка не содержит пары \"ключ-значение\"");
                        }
                        values.put(string[0], string[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
