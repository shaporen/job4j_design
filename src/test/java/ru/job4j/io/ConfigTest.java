package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev==");
    }

    @Test
    void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Sergey Shaporenko");
    }

    @Test
    void whenOnlyEqualSign() {
        String path = "./data/only_equal_sign.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenDoesntContainKey() {
        String path = "./data/only_key.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenDoesntContainValue() {
        String path = "./data/only_value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}