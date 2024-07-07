package ru.job4j.regex;

import java.util.regex.*;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern1 = Pattern.compile("Я учусь на Job4j");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern1.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern1.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern2 = Pattern.compile("Job4j");

        String text3 = "Job4j";
        Matcher matcher3 = pattern2.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        String text4 = "job4j";
        Matcher matcher4 = pattern2.matcher(text4);
        boolean isPresent4 = matcher4.matches();
        System.out.println(isPresent4);

        Pattern pattern = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);

        String textOne = "Job4j";
        Matcher matcherOne = pattern.matcher(textOne);
        boolean isPresentOne = matcherOne.matches();
        System.out.println(isPresentOne);

        String textTwo = "joB4J";
        Matcher matcherTwo = pattern.matcher(textTwo);
        boolean isPresentTwo = matcherTwo.matches();
        System.out.println(isPresentTwo);
    }
}
