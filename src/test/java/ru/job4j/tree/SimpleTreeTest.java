package ru.job4j.tree;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.*;

public class SimpleTreeTest {

    private Tree<Integer> tree;

    @BeforeEach
    void setTree() {
        tree = new SimpleTree<>(1);
    }

    @Test
    void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.findBy(6)).isPresent();
    }

    @Test
    void whenElFindNotExistThenOptionEmpty() {
        tree.add(1, 2);
        assertThat(tree.findBy(7)).isEmpty();
    }

    @Test
    void whenChildExistOnLeafThenNotAdd() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(tree.add(2, 6)).isFalse();
    }

    @Test
    void whenBinary() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(3, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenNotBinary() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(3, 5);
        tree.add(4, 6);
        tree.add(3, 7);
        assertThat(tree.isBinary()).isFalse();
    }
}