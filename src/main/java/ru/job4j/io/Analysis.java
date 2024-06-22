package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            boolean isWork = true;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] parts = line.split(" ");
                if (isWork == validError(parts[0])) {
                    isWork = !isWork;
                    writer.write(String.format("%s%s", parts[1],
                            !isWork ? ";" : System.lineSeparator()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean validError(String code) {
        return "400".equals(code) || "500".equals(code);
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
