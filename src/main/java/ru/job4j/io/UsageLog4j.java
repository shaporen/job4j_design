package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        char gender = 'm';
        float height = 1.78f;
        double weight = 75.58D;
        boolean developer = true;
        long experience = 12L;
        byte a = 1;
        short b = 2;
        LOG.debug("User info : {}, age : {}, gender : {}, height : {}, weight : {},"
                        + " developer : {}, experience : {}, a : {}, b : {}",
                name, age, gender, height, weight, developer, experience, a, b);
    }
}
