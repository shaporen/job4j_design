package ru.job4j.io;

import java.io.*;

public class ResultFile {
    public static void main(String[] args) {
        try (PrintWriter output = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream("data/dataresult.txt")))) {
            output.println("1 * 2 = 2");
            output.println("1 * 3 = 3");
            output.println("1 * 4 = 4");
            output.println("1 * 5 = 5");
            output.println("1 * 6 = 6");
            output.println("1 * 7 = 7");
            output.println("1 * 8 = 8");
            output.println("1 * 9 = 9");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
