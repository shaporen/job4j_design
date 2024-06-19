package ru.job4j.assertj;

import java.util.*;
import java.util.stream.*;

public class Example {
    public static void main(String[] args) {
        List<Integer> nums = Arrays.asList(1, 4, 9, 16, 25);
        Stream<Double> square = nums.stream()
                .map(Math::sqrt);
        square.forEach(System.out::println);
    }
}
