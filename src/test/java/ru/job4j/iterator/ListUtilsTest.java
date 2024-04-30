package ru.job4j.iterator;

import org.junit.jupiter.api.*;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfMoreThanThree() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addBefore(input, 3, 4);
        ListUtils.removeIf(input, el -> el > 3);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenReplaceIfMoreThanThree() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addBefore(input, 3, 4);
        ListUtils.replaceIf(input, el -> el > 3, 2);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 2, 2);
    }

    @Test
    void whenRemoveAllContains() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addBefore(input, 3, 4);
        List<Integer> elements = new ArrayList<>(Arrays.asList(2, 4));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }
}