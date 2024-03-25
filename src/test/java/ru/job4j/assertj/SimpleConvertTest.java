package ru.job4j.assertj;

import org.assertj.core.data.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

public class SimpleConvertTest {

    @Test
    public void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    public void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("one", "two", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("three")
                .containsExactlyInAnyOrder("three", "one", "five", "two", "four")
                .startsWith("one")
                .containsSequence("three", "four");
    }

    @Test
    public void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("one", "one", "three", "four", "four");
        assertThat(set).hasSize(3)
                .contains("one")
                .containsExactlyInAnyOrder("three", "one", "four")
                .doesNotContain("five")
                .containsAnyOf("five", "four", "zero");
    }

    @Test
    public void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("one", "two", "three", "four", "five");
        assertThat(map).hasSize(5)
                .containsKeys("two", "three", "four")
                .containsValues(0, 2, 3)
                .doesNotContainKey("six")
                .containsEntry("five", 4);
    }
}