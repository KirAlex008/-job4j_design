package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisNothing() {
        Box box = new Box(1, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void hasItEight() {
        Box box = new Box(8, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(8);
    }

    @Test
    void ifSixThenMinusOne() {
        Box box = new Box(6, 10);
        int number = box.getNumberOfVertices();
        assertThat(number).isEqualTo(-1);
    }

    @Test
    void ifEightThenCubExist() {
        Box box = new Box(8, 10);
        boolean flag = box.isExist();
        assertThat(flag).isTrue();
    }

    @Test
    void ifSixThenCubNotExist() {
        Box box = new Box(6, 10);
        boolean flag = box.isExist();
        assertThat(flag).isFalse();
    }

    @Test
    void ifCubThenArea24withPercentage() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isCloseTo(24d, Percentage.withPercentage(1));
    }

    @Test
    void ifCubThenArea24WithPrecision() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isCloseTo(24d, withPrecision(0.01));
    }
}