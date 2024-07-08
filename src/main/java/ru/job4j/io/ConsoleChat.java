package ru.job4j.io;

import java.io.*;
import java.nio.charset.*;
import java.util.*;

public class ConsoleChat {
    private static final String OUT = "Закончить";
    private static final String STOP = "Стоп";
    private static final String CONTINUE = "Продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean botStatus = true;
        Scanner scanner = new Scanner(System.in);
        String userAnswer;
        List<String> botPhrases = readPhrases();
        List<String> log = new ArrayList<>();
        do {
            userAnswer = scanner.nextLine();
            log.add("Пользователь сказал: " + userAnswer);
            if (STOP.equals(userAnswer) || OUT.equals(userAnswer)) {
                botStatus = false;
            }
            if (CONTINUE.equals(userAnswer)) {
                botStatus = true;
            }
            if (botStatus) {
                Random randomAnswer = new Random();
                String botAnswer = botPhrases.get(randomAnswer.nextInt(botPhrases.size()));
                System.out.println(botAnswer);
                log.add("Бот сказал: " + botAnswer);
            }
        } while (!OUT.equals(userAnswer));
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> botPhrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(botPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data\\chatLog.txt",
                "data\\botAnswers.txt");
        consoleChat.run();
    }
}
