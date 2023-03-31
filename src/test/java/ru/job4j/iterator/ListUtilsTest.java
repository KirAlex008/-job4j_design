package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6, 7));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(7).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 8, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(7).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.removeIf(input, e -> e < 3 || e > 5);
        assertThat(input).hasSize(3).containsSequence(3, 4, 5);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.replaceIf(input, e -> e < 3 || e > 4, 100);
        assertThat(input).hasSize(6).containsSequence(100, 3, 4, 100, 100, 100);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.removeAll(input, List.of(1, 2, 6));
        assertThat(input).hasSize(4).containsSequence(3, 4, 5, 7);
    }
}