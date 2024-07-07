package ru.job4j.regex;

import java.util.regex.*;

public class RegexExampleTwo {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("11");
        String text = "111111";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }
    }
}
