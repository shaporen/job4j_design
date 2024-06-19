package ru.job4j.io;

import java.io.*;

public class EvenNumberFile {
    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] lines = text.toString().split(System.lineSeparator());
        for (String line: lines) {
            int number = Integer.parseInt(line);
            System.out.println(line + " - "
                    + (number % 2 == 0 ? "четное число" : "нечетное число"));
        }
    }
}
