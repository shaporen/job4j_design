package ru.job4j.regex;

import java.util.regex.*;

public class RegexExampleThree {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{2}\\.\\d{2}\\.\\d{4}");
        String text = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }

        Pattern pattern1 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text1 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher1 = pattern1.matcher(text1);
        while (matcher1.find()) {
            System.out.println("Найдено совпадение: " + matcher1.group());
        }
    }
}
