package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.assertj.core.api.Assertions.*;

public class NameLoadTest {
    @Test
    void whenIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void whenIsNotEmptyAndAllRigth() {
        String[] names = {"key=value", "key1=value1"};
        NameLoad nameLoad = new NameLoad();
        nameLoad.parse(names);
        Map<String, String> result = nameLoad.getMap();
        assertThat(result).isNotEmpty().containsOnly(entry("key", "value"), entry("key1", "value1"));
    }

    @Test
    void whenArrayIsEmpty() {
        String[] names = {};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenIsNotContainTheEqualSymbol() {
        String[] names = {"key:value", "key1:value1"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain the symbol '='");
    }

    @Test
    void whenIsNotContainKey() {
        String[] names = {"=key=value", "key1=value1"};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a key");
    }

    @Test
    void whenIsNotContainValue() {
        String[] names = {"keyvalue="};
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("not contain a value");
    }
}